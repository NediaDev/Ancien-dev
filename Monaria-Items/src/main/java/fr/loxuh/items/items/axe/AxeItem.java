package fr.loxuh.items.items.axe;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AxeItem {
    public static ItemStack createAxe() {
        ItemStack AxeItem = new ItemStack(Material.DIAMOND_AXE); // Vous pouvez personnaliser le matériau si nécessaire

        ItemMeta meta = AxeItem.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§f§l▸ §5§l§nFarming Axe");
            meta.addEnchant(Enchantment.DIG_SPEED, 10, true);
            meta.addEnchant(Enchantment.DURABILITY, 50, true);
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§c ");
            lore.add("§6§l§nINFORMATIONS");
            lore.add("§c ");
            lore.add("§f▸ §dAugmentez §fvotre récolte de §dmelons §fet");
            lore.add("§fVous aurez 10% de chance de remportez entre");
            lore.add("§d10 000 et 20 000 $ §fen les cassant avec cette hache");
            lore.add("§c ");
            meta.setLore(lore);
            meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

            AxeItem.setItemMeta(meta);
        }

        return AxeItem;
    }
}