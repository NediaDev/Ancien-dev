package fr.loxuh.items.items.diamant;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DiamantPrecieuxItem {
    public static ItemStack createDiamantPrecieux() {
        ItemStack diamantPrecieux = new ItemStack(Material.DIAMOND); // Vous pouvez personnaliser le matériau si nécessaire

        ItemMeta meta = diamantPrecieux.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§c§lDiamant Précieux");
            // Vous pouvez ajouter d'autres attributs, enchantements, etc. ici si nécessaire
            diamantPrecieux.setItemMeta(meta);
        }

        return diamantPrecieux;
    }
}