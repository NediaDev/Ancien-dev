package fr.loxuh.items.items.moissoneuse;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class MoissoneuseItem {
    public static final String MOISSONEUSE_DISPLAY_NAME = "§f§l▸ §2§l§nMoissoneuse";
    public static final List<String> MOISSONEUSE_LORE = new ArrayList<>();

    static {
        MOISSONEUSE_LORE.add("§c ");
        MOISSONEUSE_LORE.add("§6§l§nINFORMATIONS");
        MOISSONEUSE_LORE.add("§c ");
        MOISSONEUSE_LORE.add("§f▸ §fEn utilisant cette §bMoissoneuse§f");
        MOISSONEUSE_LORE.add("§fvous aurez §c2% §fde récolter un diamant précieux.");
        MOISSONEUSE_LORE.add("§c ");

    }

    public static ItemStack createMoissoneuse() {
        ItemStack moissoneuse = new ItemStack(Material.DIAMOND_HOE);

        ItemMeta meta = moissoneuse.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(MOISSONEUSE_DISPLAY_NAME);
            meta.setLore(MOISSONEUSE_LORE);
            moissoneuse.setItemMeta(meta);
        }

        return moissoneuse;
    }

    public static boolean isMoissoneuse(ItemStack itemStack) {
        if (itemStack != null && itemStack.getType() == Material.DIAMOND_HOE
                && itemStack.hasItemMeta()
                && itemStack.getItemMeta().hasDisplayName()
                && itemStack.getItemMeta().getDisplayName().equals(MOISSONEUSE_DISPLAY_NAME))
        {
            return true;
        }
        return false;
    }
}