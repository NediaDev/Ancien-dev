package fr.loxuh.items.manager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TransformUtil {

    public static boolean isDiamondTool(ItemStack item) {
        if (item.getType() == Material.DIAMOND_PICKAXE && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            return meta.hasDisplayName() && meta.getDisplayName().equals("§f§l▸ §6§l§nMegaTools");
        } else if (item.getType() == Material.DIAMOND_SPADE && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            return meta.hasDisplayName() && meta.getDisplayName().equals("§f§l▸ §6§l§nMegaTools");
        } else if (item.getType() == Material.DIAMOND_AXE && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            return meta.hasDisplayName() && meta.getDisplayName().equals("§f§l▸ §6§l§nMegaTools");
        }
        return false;
    }

    public static Material getTransformedMaterial(Material originalMaterial) {
        switch (originalMaterial) {
            // Pioche
            case STONE:
            case COBBLESTONE:
            case COAL_ORE:
            case IRON_ORE:
            case GOLD_ORE:
            case LAPIS_ORE:
            case REDSTONE_ORE:
            case EMERALD_ORE:
            case QUARTZ_ORE:
            case NETHERRACK:
            case ENDER_STONE:
                return Material.DIAMOND_PICKAXE;

            // Pelle
            case DIRT:
            case GRASS:
            case MYCEL:
            case SAND:
            case GRAVEL:
            case SNOW:
            case SNOW_BLOCK:
            case CLAY:
            case SOUL_SAND:
                return Material.DIAMOND_SPADE;

            // Hache
            case WOOD:
            case LOG:
            case LOG_2:
            case WOOD_STEP:
                return Material.DIAMOND_AXE;

            default:
                return null;
        }
    }
}