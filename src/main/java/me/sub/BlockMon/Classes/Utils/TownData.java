package me.sub.BlockMon.Classes.Utils;

import me.sub.BlockMon.Classes.Files.Town;

public class TownData {

    private Town town;
    private boolean claiming;

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    private boolean isSafe;

    public TownData(Town town) {
        this.town = town;
        isSafe = false;
        claiming = false;
    }

    public Town getTown() {
        return town;
    }

    public boolean isClaiming() {
        return claiming;
    }

    public void setClaiming(boolean claiming) {
        this.claiming = claiming;
    }
}
