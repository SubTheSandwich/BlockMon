package me.sub.BlockMon.Events.Players.Chat;

import me.sub.BlockMon.Classes.Files.User;
import me.sub.BlockMon.Classes.Utils.UserData;
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
        if (data.isSettingUp()) {
            e.setCancelled(true);

        }
    }
}
