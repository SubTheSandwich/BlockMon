package me.sub.BlockMon.Classes.Files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Town {
    File file;
    FileConfiguration customFile;
    UUID id;

    public Town(UUID uuid) {
        id = uuid;
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("BlockMon").getDataFolder().getPath() + "/data/towns/", id + ".yml");
        customFile = YamlConfiguration.loadConfiguration(file); // yaml file
    }

    public Boolean exists() {
        return file.exists();
    }
    public void setup() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {

            }
        }
    }

    public FileConfiguration get() {
        return customFile;
    }

    public void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println("Couldn't save file");
        }
    }

    public void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static Town getByName(String name) {
        File[] factions = new File(Bukkit.getServer().getPluginManager().getPlugin("BlockMon").getDataFolder().getPath() + "/data/towns").listFiles();
        if (factions != null) {
            for (File f : factions) {
                YamlConfiguration file = YamlConfiguration.loadConfiguration(f);
                if (file.getString("name").equalsIgnoreCase(name.toLowerCase())) {
                    return new Town(UUID.fromString(file.getString("uuid")));
                }
            }
        }
        return null;
    }

    public String getName() {
        return customFile.getString("name");
    }

    public UUID getUUID() {
        return id;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void delete() {
        file.delete();
    }
}