package fr.loxuh.items.items.hammer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import org.bukkit.enchantments.Enchantment;

public class HammerItem {
    public static ItemStack createHammer() {
        ItemStack hammer = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = hammer.getItemMeta();
        meta.setDisplayName("§f§l▸ §6§l§nHammer");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§c ");
        lore.add("§6§l§nINFORMATIONS");
        lore.add(" §c");
        lore.add("§f▸ En Minant avec cette §epioche§f");
        lore.add("§fvous casserez les §ablock dans un rayon de §b3x3§f.");
        lore.add("§c ");
        meta.setLore(lore);

        // Ajoutez un enchantement factice pour masquer l'enchantement
        meta.addEnchant(Enchantment.DIG_SPEED, 10, true);
        meta.addEnchant(Enchantment.DURABILITY, 20, true);
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        hammer.setItemMeta(meta);
        return hammer;
    }




    public static boolean isHammer(ItemStack itemStack) {
        if (itemStack != null && itemStack.getType() == Material.DIAMOND_PICKAXE) {
            ItemMeta meta = itemStack.getItemMeta();
            if (meta != null && meta.hasDisplayName() && meta.getDisplayName().equals("§f§l▸ §6§l§nHammer")) {
                return true;
            }
        }
        return false;
    }







}