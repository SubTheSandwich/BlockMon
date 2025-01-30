package me.sub.BlockMon.Main;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.sub.BlockMon.Classes.Files.User;
import me.sub.BlockMon.Classes.Utils.UserData;
import me.sub.BlockMon.Events.Players.Chat.UserEnterInfoEvent;
import me.sub.BlockMon.Events.Players.Server.UserRegisterEvent;
import me.sub.BlockMon.Events.Players.Server.UserSaveDataEvent;
import org.bukkit.entity.Player;
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

    }

    private void events() {
        PluginManager pm = getServer().getPluginManager();

        // Player

        pm.registerEvents(new UserRegisterEvent(), this);
        pm.registerEvents(new UserEnterInfoEvent(), this);
        pm.registerEvents(new UserSaveDataEvent(), this);
    }

    private void files() {

    }

    private void packets() {

    }
}
