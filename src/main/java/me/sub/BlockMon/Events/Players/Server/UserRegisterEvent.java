package me.sub.BlockMon.Events.Players.Server;

import me.sub.BlockMon.Classes.Files.User;
import me.sub.BlockMon.Classes.Utils.UserData;
import me.sub.BlockMon.Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UserRegisterEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        User user = new User(p.getUniqueId());
        UserData userData = new UserData();
        if (!user.exists()) {
            user.setup();
            user.get().set("uuid", p.getUniqueId().toString());
            user.get().set("name", p.getName());
            user.save();
            userData.setSetup(true);
            // to be implemented
        }
        if (user.get().getBoolean("data.setup")) {
            userData.setSetup(true);
            user.get().set("data.setup", null);
            user.save();
        }
        if (!user.getName().equals(p.getName())) {
            user.get().set("name", p.getName());
            user.save();
        }
        Main.getInstance().data.put(p.getUniqueId(), userData);
    }
}
