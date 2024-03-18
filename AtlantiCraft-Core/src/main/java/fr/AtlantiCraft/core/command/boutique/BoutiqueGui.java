package fr.AtlantiCraft.core.command.boutique;

import fr.AtlantiCraft.core.utils.ItemBuilder;
import org.bukkit.inventory.*;
import org.bukkit.*;

public final class BoutiqueGui
{
    private final Inventory inv;
    private final ItemStack v;
    private final ItemStack close;
    private final ItemStack back;
    private final ItemStack weeklyshop;
    private final ItemStack grade;
    private final ItemStack kit;
    private final ItemStack spawner;
    private final ItemStack commandes;
    private final ItemStack cle;
    public BoutiqueGui() {
        this.inv = Bukkit.createInventory((InventoryHolder)null, 54, "§8Boutique");
        this.v = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte)10).setName("").toItemStack();
        this.close = new ItemBuilder(Material.ARROW).setName("§eRevenir en arri\u00e8re").toItemStack();
        this.back = new ItemBuilder(Material.BARRIER).setName("§cFermer l'inventaire").toItemStack();
        this.weeklyshop = new ItemBuilder(Material.EMERALD).setName("§6Weekly§cShop").setLore("§eDécouvrez un shop in\u00e9dit", "§echaque semaines").toItemStack();
        this.grade = new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§cGrades").setLore("§eAcheter des grades pour profiter", "§ede nos avantages").toItemStack();
        this.kit = new ItemBuilder(Material.BLAZE_ROD).setName("§cKits").setLore("§eAcheter des kits pour profiter", "§ede nos avantages").toItemStack();
        this.spawner = new ItemBuilder(Material.MOB_SPAWNER).setName("§cSpawners").setLore("§eAcheter des spawners pour profiter", "§ed'un bon farming").toItemStack();
        this.commandes = new ItemBuilder(Material.PAPER).setName("§cCommandes").setLore("§eAcheter des commandes pour", "§eprofiter d'avantages").toItemStack();
        this.cle = new ItemBuilder(Material.TRIPWIRE_HOOK).setName("§cCl\u00e9s").setLore("§eAcheter des cl\u00e9s pour obtenir", "§edes r\u00e9compenses").toItemStack();
        for (int i = 0; i < 11; ++i) {
            this.inv.setItem(i, this.v);
        }
        for (int i = 44; i < 54; ++i) {
            this.inv.setItem(i, this.v);
        }
        this.inv.setItem(16, this.v);
        this.inv.setItem(17, this.v);
        this.inv.setItem(18, this.v);
        this.inv.setItem(26, this.v);
        this.inv.setItem(27, this.v);
        this.inv.setItem(35, this.v);
        this.inv.setItem(36, this.v);
        this.inv.setItem(37, this.v);
        this.inv.setItem(43, this.v);
        this.inv.setItem(4, this.weeklyshop);
        this.inv.setItem(20, this.grade);
        this.inv.setItem(24, this.kit);
        this.inv.setItem(38, this.spawner);
        this.inv.setItem(40, this.commandes);
        this.inv.setItem(42, this.cle);
        this.inv.setItem(53, this.close);
        this.inv.setItem(45, this.back);
    }

    public final Inventory getInventory() {
        return this.inv;
    }
}
