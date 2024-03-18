package fr.loxuh.trone.event;

import fr.loxuh.trone.utils.Cuboid;
import org.bukkit.entity.*;
import org.bukkit.*;

public class Trone
{
    private final int y;
    private final Cuboid cuboid;
    private final TroneTask troneTask;
    private Material material;
    private final int money;
    private double boost;
    private Player player;

    public Trone(final EventTrone eventTrone, final Cuboid cuboid, final int y, final int money) {
        this.boost = 1.0;
        this.player = null;
        this.cuboid = cuboid;
        this.money = money;
        this.y = y;
        this.troneTask = new TroneTask(eventTrone);
    }

    public void resetBlocks(final Material material) {
        if (this.material != material) {
            this.setBlocks(material);
        }
    }

    public void setBlocks(final Material material) {
        this.material = material;
        final Location[] loc = new Location[1];
        this.cuboid.getLocations().forEach(location -> {
            loc[0] = location.clone();
            loc[0].setY((double)this.y);
            loc[0].getBlock().setType(material);
        });
    }

    public int getMoney() {
        return (int)(this.money * this.boost);
    }

    public int getY() {
        return this.y;
    }

    public Cuboid getCuboid() {
        return this.cuboid;
    }

    public TroneTask getTroneTask() {
        return this.troneTask;
    }

    public Material getMaterial() {
        return this.material;
    }

    public double getBoost() {
        return this.boost;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setBoost(final double boost) {
        this.boost = boost;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }
}

