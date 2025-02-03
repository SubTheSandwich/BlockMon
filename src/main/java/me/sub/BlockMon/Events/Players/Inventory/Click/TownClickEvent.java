package me.sub.BlockMon.Events.Players.Inventory.Click;

import me.sub.BlockMon.Classes.Files.GUI;
import me.sub.BlockMon.Classes.Files.Locale;
import me.sub.BlockMon.Classes.Utils.C;
import me.sub.BlockMon.Classes.Utils.TownData;
import me.sub.BlockMon.Classes.Utils.UserData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TownClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        UserData data = UserData.get(p.getUniqueId());
        if (data.getTownData() == null) return;
        TownData townData = data.getTownData();
        if (townData.getTown() == null) return;
        if (!townData.getTown().exists()) return;
        if (e.getClickedInventory() == null) return;
        if (e.getCurrentItem() == null) return;
        if (!e.getView().getTitle().equalsIgnoreCase(C.chat(GUI.get().getString("gui.modify.start.name")))) return;
        e.setCancelled(true);
        if (GUI.matches(e.getCurrentItem(), "modify.start", "claim")) {
            if (townData.isClaiming()) {
                p.sendMessage(C.chat(Locale.get().getString("events.town.modify.claim.already")));
                return;
            }
            if (p.getInventory().firstEmpty() == -1) {
                p.sendMessage(C.chat(Locale.get().getString("primary.full")));
                return;
            }
            return;
        }
        if (GUI.matches(e.getCurrentItem(), "modify.start", "select")) {

        }
    }
}
