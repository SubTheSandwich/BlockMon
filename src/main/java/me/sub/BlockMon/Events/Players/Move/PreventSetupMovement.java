package me.sub.BlockMon.Events.Players.Move;

import me.sub.BlockMon.Classes.Files.User;
import me.sub.BlockMon.Classes.Utils.UserData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PreventSetupMovement implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        UserData data = UserData.get(p.getUniqueId());
        if (!data.isSettingUp()) return;
        if (e.getTo() == null) return;
        if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) {
            e.setCancelled(true);
        }
    }
}
