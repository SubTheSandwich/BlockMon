package me.sub.BlockMon.Events.Players.Server;

import me.sub.BlockMon.Classes.Files.User;
import me.sub.BlockMon.Classes.Utils.UserData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserSaveDataEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        User user = new User(p.getUniqueId());
        UserData data = UserData.get(p.getUniqueId());
        if (data.isSettingUp()) {
            user.get().set("data.setup", true);
            user.save();
            // to be implemented
        }
    }
}
