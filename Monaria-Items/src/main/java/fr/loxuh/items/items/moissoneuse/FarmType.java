package fr.loxuh.items.items.moissoneuse;

import org.bukkit.*;

public enum FarmType
{
    CARROTS(Material.CARROT, Material.CARROT_ITEM, 7),
    WHEAT(Material.CROPS, Material.WHEAT, 7),
    POTATOES(Material.POTATO, Material.POTATO_ITEM, 7),
    NETHER_WARTS(Material.NETHER_WARTS, Material.NETHER_STALK, 3);

    private final Material material;
    private final Material drops;
    private final int data;

    private FarmType(final Material material, final Material drops, final int data) {
        this.material = material;
        this.drops = drops;
        this.data = data;
    }

    public Material getMaterial() {
        return this.material;
    }

    public Material getDrops() {
        return this.drops;
    }

    public int getData() {
        return this.data;
    }
}

