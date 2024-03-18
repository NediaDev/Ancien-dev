package fr.loxuh.items.items.megatools;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MegaToolItem {
    public static ItemStack createMega() {
        ItemStack Megatools = new ItemStack(Material.DIAMOND_PICKAXE); // Vous pouvez personnaliser le matériau si nécessaire

        ItemMeta meta = Megatools.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§f§l▸ §6§l§nMegaTools");
            ArrayList<String> lore = new ArrayList<>();
            lore.add("§c ");
            lore.add("§6§l§nINFORMATIONS");
            lore.add(" §c");
            lore.add("§f▸ §fCette pioche a la §ecapacitée §fde");
            lore.add("§fse §etransformer §fen pelle, hache ...");
            lore.add(" §c");
            meta.setLore(lore);

            Megatools.setItemMeta(meta);
        }

        return Megatools;
    }
}