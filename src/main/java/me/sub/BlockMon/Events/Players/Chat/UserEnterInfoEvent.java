package me.sub.BlockMon.Events.Players.Chat;

import me.sub.BlockMon.Classes.Files.Locale;
import me.sub.BlockMon.Classes.Files.User;
import me.sub.BlockMon.Classes.Utils.C;
import me.sub.BlockMon.Classes.Utils.UserData;
import me.sub.BlockMon.Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class UserEnterInfoEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        User user = new User(p.getUniqueId());
        UserData data = UserData.get(p.getUniqueId());
        for (Player player : new ArrayList<>(e.getRecipients())) {
            UserData userData = UserData.get(player.getUniqueId());
            if (userData.isSettingUp()) {
                e.getRecipients().remove(player);
            }
        }
        String message = e.getMessage();
        message = C.strip(message);
        if (!data.isSettingUp()) return;
        e.setCancelled(true);
        if (data.getPreferredName() == null) {
            if (!message.matches("^[A-Za-z]+$")) {
                p.sendMessage(C.chat(Locale.get().getString("primary.not-letters")));
                return;
            }
            data.setPreferredName(message);
            String genders = String.join("&e, &d", Main.getInstance().getConfig().getStringList("player.valid-genders"));
            p.sendMessage(C.chat(Locale.get().getString("events.setup.next").replace("%name%", message).replace("%gender-list%", genders)));
            return;
        }
        if (data.getGender() == null) {
            boolean valid = false;
            for (String g : Main.getInstance().getConfig().getStringList("player.valid-genders")) {
                if (message.equalsIgnoreCase(g)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                String genders = String.join(", ", Main.getInstance().getConfig().getStringList("player.valid-genders"));
                p.sendMessage(C.chat(Locale.get().getString("events.setup.invalid-gender").replace("%gender-list%", genders)));
                return;
            }
            data.setGender(message.toUpperCase());
            String gender = message.substring(0, 1).toUpperCase() + message.substring(1).toLowerCase();
            p.sendMessage(C.chat(Locale.get().getString("events.setup.finished").replace("%gender%", gender)));
            data.setSetup(false);
            user.get().set("data.gender", gender);
            user.get().set("data.preferred-name", data.getPreferredName());
            if (user.get().getBoolean("data.setup")) {
                user.get().set("data.setup", false);
            }
            user.save();
            return;
        }
        data.setSetup(false);
        if (user.get().getBoolean("data.setup")) {
            user.get().set("data.setup", false);
            user.save();
        }
    }
}
