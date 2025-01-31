package me.sub.BlockMon.Classes.Utils;

import me.sub.BlockMon.Main.Main;

import java.util.UUID;

public class UserData {

    private boolean setup;
    private String preferredName;
    private String gender;

    public UserData() {
        setup = false;
        preferredName = null;
        gender = null;
    }

    public void setSetup(boolean setup) {
        this.setup = setup;
    }

    public boolean isSettingUp() {
        // returns if user is setting up account
        return setup;
    }

    public static UserData get(UUID uuid) {
        return Main.getInstance().data.get(uuid);
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
