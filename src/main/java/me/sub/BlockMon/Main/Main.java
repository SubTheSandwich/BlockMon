package me.sub.BlockMon.Main;

import me.sub.BlockMon.Classes.Utils.UserData;
import me.sub.BlockMon.Commands.Admin.BMDebugCommand;
import me.sub.BlockMon.Commands.Admin.TownCommand;
import me.sub.BlockMon.Events.Players.Chat.UserEnterInfoEvent;
import me.sub.BlockMon.Events.Players.Inventory.Close.TownCloseEvents;
import me.sub.BlockMon.Events.Players.Move.PreventSetupMovement;
import me.sub.BlockMon.Events.Players.Server.UserRegisterEvent;
import me.sub.BlockMon.Events.Players.Server.UserSaveDataEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;

    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public HashMap<UUID, UserData> data = new HashMap<>();

    @Override
    public void onEnable() {
        files();
        events();
        packets();
        commands();
    }

    @Override
    public void onDisable() {

    }

    private void commands() {
        getCommand("town").setExecutor(new TownCommand()); getCommand("town").setTabCompleter(new TownCommand());
        getCommand("bmdebug").setExecutor(new BMDebugCommand()); getCommand("bmdebug").setTabCompleter(new BMDebugCommand());
    }

    private void events() {
        PluginManager pm = getServer().getPluginManager();

        // Player

        pm.registerEvents(new UserRegisterEvent(), this);
        pm.registerEvents(new UserEnterInfoEvent(), this);
        pm.registerEvents(new UserSaveDataEvent(), this);

        pm.registerEvents(new PreventSetupMovement(), this);

        pm.registerEvents(new TownCloseEvents(), this);
    }

    private void files() {
        saveResource("locale.yml", false);
        saveResource("config.yml", false);
        saveResource("gui.yml", false);
    }

    private void packets() {

    }
}
