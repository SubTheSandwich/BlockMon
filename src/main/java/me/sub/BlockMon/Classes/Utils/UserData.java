package me.sub.BlockMon.Classes.Utils;

import me.sub.BlockMon.Main.Main;

import java.util.UUID;

public class UserData {

    private boolean setup;

    public UserData() {
        setup = false;
    }

    public void setSetup(boolean setup) {
        this.setup = setup;
    }

    public boolean isSetup() {
        return setup;
    }

    public static UserData get(UUID uuid) {
        return Main.getInstance().data.get(uuid);
    }
}
