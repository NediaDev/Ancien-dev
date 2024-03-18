package fr.loxuh.trone.event;

import fr.loxuh.trone.Main;
import fr.loxuh.trone.utils.Cuboid;
import fr.loxuh.trone.utils.LocationUtils;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

public class EventTrone
{
    private Trone trone;

    public EventTrone(final FileConfiguration fileConfiguration) {
        this.applyConfig(fileConfiguration);
        final TroneTask troneTask = this.trone.getTroneTask();
        troneTask.runTaskTimer((Plugin) Main.getInstance(), 0L, 20L);
    }

    public void applyConfig(final FileConfiguration fileConfiguration) {
        final String id = "CaptureTheTrone.";
        final Cuboid cuboid = new Cuboid(LocationUtils.locationFromString(fileConfiguration.getString(id + "loc1")), LocationUtils.locationFromString(fileConfiguration.getString(id + "loc2")));
        final int y = fileConfiguration.getInt(id + "y");
        final int money = fileConfiguration.getInt(id + "money");
        this.trone = new Trone(this, cuboid, y, money);
    }

    public Trone getTrone() {
        return this.trone;
    }
}

