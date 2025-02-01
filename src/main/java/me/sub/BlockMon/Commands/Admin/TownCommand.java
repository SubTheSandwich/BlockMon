package me.sub.BlockMon.Commands.Admin;

import me.sub.BlockMon.Classes.Files.GUI;
import me.sub.BlockMon.Classes.Files.Locale;
import me.sub.BlockMon.Classes.Files.Town;
import me.sub.BlockMon.Classes.Utils.C;
import me.sub.BlockMon.Classes.Utils.Perm;
import me.sub.BlockMon.Classes.Utils.TownData;
import me.sub.BlockMon.Classes.Utils.UserData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TownCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(C.chat(Locale.get().getString("primary.not-player")));
            return true;
        }
        if (!Perm.has(p, "town", "admin")) {
            p.sendMessage(C.chat(Locale.get().getString("primary.no-permission")));
            return true;
        }
        if (args.length != 2) {
            p.sendMessage(C.chat(Locale.get().getString("commands.town.usage")));
            return true;
        }
        UserData data = UserData.get(p.getUniqueId());
        if (args[0].equalsIgnoreCase("create")) {
            Town town = Town.getByName(args[1]);
            if (town != null) {
                p.sendMessage(C.chat(Locale.get().getString("commands.town.create.already")));
                return true;
            }
            UUID uuid = UUID.randomUUID();
            town = new Town(uuid);
            town.get().set("uuid", uuid.toString());
            town.get().set("name", args[1]);
            town.save();
            p.sendMessage(C.chat(Locale.get().getString("commands.town.create.created").replace("%town%", args[1])));
            return true;
        }
        if (args[0].equalsIgnoreCase("delete")) {
            Town town = Town.getByName(args[1]);
            if (town == null) {
                p.sendMessage(C.chat(Locale.get().getString("commands.town.not-exist")));
                return true;
            }
            town.delete();
            p.sendMessage(C.chat(Locale.get().getString("commands.town.delete.deleted").replace("%town%", args[1])));
            return true;
        }
        if (args[0].equalsIgnoreCase("modify")) {
            Town town = Town.getByName(args[1]);
            if (town == null) {
                p.sendMessage(C.chat(Locale.get().getString("commands.town.not-exist")));
                return true;
            }
            if (data.getTownData() != null) {
                p.sendMessage(C.chat(Locale.get().getString("commands.town.modify.already")));
                return true;
            }
            data.setTownData(new TownData(town));
            p.sendMessage(C.chat(Locale.get().getString("commands.town.modify.message").replace("%town%", town.getName())));
            GUI.open(p, "modify.start");
            return true;
        }
        p.sendMessage(C.chat(Locale.get().getString("commands.town.usage")));
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            return new ArrayList<>();
        }
        if (!Perm.has(p, "town", "admin")) {
            return new ArrayList<>();
        }
        if (args.length == 1) return Arrays.asList("create", "delete", "modify");
        return new ArrayList<>();
    }
}
