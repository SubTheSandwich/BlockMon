package me.sub.BlockMon.Events.Players.Inventory.Close;

import me.sub.BlockMon.Classes.Utils.TownData;
import me.sub.BlockMon.Classes.Utils.UserData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class TownCloseEvents implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        UserData data = UserData.get(p);
        if (data.getTownData() == null) return;
        TownData townData = data.getTownData();
        if (!townData.isSafe()) data.setTownData(null);
    }
}
