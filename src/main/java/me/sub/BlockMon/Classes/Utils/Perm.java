package me.sub.BlockMon.Classes.Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Perm {

    public static boolean has(CommandSender p, String command, String type) {
        return p.isOp() || p.hasPermission("*") || p.hasPermission("*") || p.hasPermission("blockmon.*") || p.hasPermission("blockmon." + type) || p.hasPermission("blockmon.command." + command);
    }
    public static boolean has(Player p, String command, String type) {
        return p.isOp() || p.hasPermission("*") || p.hasPermission("*") || p.hasPermission("blockmon.*") || p.hasPermission("blockmon." + type) || p.hasPermission("blockmon.command." + command);
    }
}
