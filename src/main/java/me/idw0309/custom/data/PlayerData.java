package me.idw0309.custom.data;

import com.google.gson.annotations.SerializedName;
import me.idw0309.custom.Custom;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private String uuid;
    private String username;
    private int strength;
    private int resistance;

    public PlayerData(String uuid, String username, int strength, int resistance) {
        this.uuid = uuid;
        this.username = username;
        this.strength = strength;
        this.resistance = resistance;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public int getStrength() { return strength; }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void addStrength() {
        strength++;
    }

    public void removeStrength() {
        strength--;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void addResistance() {
        resistance++;
    }

    public void removeResistance() {
        resistance--;
    }

}
