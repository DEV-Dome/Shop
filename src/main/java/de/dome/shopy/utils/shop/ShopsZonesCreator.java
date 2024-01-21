package de.dome.shopy.utils.shop;

import org.bukkit.Location;

import java.util.UUID;

public class ShopsZonesCreator {

    UUID owner;
    Location zone1 = null;
    Location zone2 = null;

    public ShopsZonesCreator(UUID owner, Location zone1){
        this.owner = owner;
        this.zone1 = zone1;
    }

    public UUID getOwner() {
        return owner;
    }

    public Location getZone1() {
        return zone1;
    }

    public void setZone1(Location zone1) {
        this.zone1 = zone1;
    }

    public Location getZone2() {
        return zone2;
    }

    public void setZone2(Location zone2) {
        this.zone2 = zone2;
    }
}
