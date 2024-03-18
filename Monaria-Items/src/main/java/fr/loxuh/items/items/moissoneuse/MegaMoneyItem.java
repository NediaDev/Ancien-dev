package fr.loxuh.items.items.moissoneuse;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MegaMoneyItem {
    public static ItemStack createMegaMoney() {
        ItemStack megaMoney = new ItemStack(Material.INK_SACK, 1, (short) 9);
        ItemMeta meta = megaMoney.getItemMeta();
        meta.setDisplayName("§f§l▸ §5§l§nMegaMoney");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§c");
        lore.add("§6§l§nINFORMATIONS");
        lore.add("  §fEn utilisant ce §5§l§nMegaMoney§f, vous aurez");
        lore.add("  §fUne chance d'avoir entre §a2 §fà §a5 §fmillions $ !");
        lore.add("§c");
        meta.setLore(lore);
        megaMoney.setItemMeta(meta);
        return megaMoney;

    }

    public static boolean isMegaMoney(ItemStack itemStack) {
        if (itemStack != null && itemStack.getType() == Material.INK_SACK
                && itemStack.getDurability() == (short) 9
                && itemStack.hasItemMeta()
                && itemStack.getItemMeta().hasDisplayName()
                && itemStack.getItemMeta().getDisplayName().equals("§f§l▸ §5§l§nMegaMoney"))
                {
            return true;
        }
        return false;
    }
}