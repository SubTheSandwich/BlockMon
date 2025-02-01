package me.sub.BlockMon.Events.Players.Move;

import me.sub.BlockMon.Classes.Utils.UserData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ProcessTallGrassMovement implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        UserData data = UserData.get(p.getUniqueId());
        if (data.isSettingUp()) return;
        // First, checks if player's new location is not null, and is of grass type (e.g. short grass / tall grass)
        if (e.getTo() == null) return;
        
        // Use block X and Z to do so
        // If grass is not in a region, return
        // Randomizer to determine if a BlockMon spawns
        // References region file to determine which BlockMon's can spawn in the region

    }
}
