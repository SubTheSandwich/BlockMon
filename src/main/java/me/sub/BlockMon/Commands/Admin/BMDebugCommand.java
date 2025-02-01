package me.sub.BlockMon.Commands.Admin;

import me.sub.BlockMon.Classes.Files.Locale;
import me.sub.BlockMon.Classes.Utils.C;
import me.sub.BlockMon.Classes.Utils.Perm;
import me.sub.BlockMon.Classes.Utils.Time;
import me.sub.BlockMon.Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BMDebugCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!Perm.has(sender, "bmdebug", "admin")) {
            sender.sendMessage(C.chat(Locale.get().getString("primary.no-permission")));
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(C.chat(Locale.get().getString("commands.bmdebug.usage")));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(C.chat(Locale.get().getString("commands.bmdebug.reload.start")));
            long startTime = System.nanoTime();
            Main.getInstance().reloadConfig();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            sender.sendMessage(C.chat(Locale.get().getString("commands.bmdebug.reload.success").replace("%time%", Time.format(duration))));
            return true;
        }
        sender.sendMessage(C.chat(Locale.get().getString("commands.bmdebug.usage")));
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!Perm.has(sender, "bmdebug", "admin")) {
            return new ArrayList<>();
        }
        if (args.length == 1) return List.of("reload");
        return new ArrayList<>();
    }
}
