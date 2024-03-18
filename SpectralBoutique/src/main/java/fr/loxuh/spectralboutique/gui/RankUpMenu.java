package fr.loxuh.spectralboutique.gui;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RankUpMenu
extends GUI {
    public static final String GUI_NAME = "§f§l» §eBoutique §fGrades §4VIP";
    public static final String RETOUR_NAME = "§f§l» §cRetour";
    public static final String PAYSAN_PLUS_NAME = "§f§l» §eAchat §f: Matelot";
    public static final String CHEVALIER_NAME = "§f§l» §eAchat §f: Amiral";
    public static final String PRINCE_NAME = "§f§l» §eAchat §f: Lieutenant";
    public static final String DUC_NAME = "§f§l» §eAchat §f: Capitaine";
    Inventory GUI = Bukkit.createInventory(null, 54, "§f§l» §eBoutique §fGrades §4VIP");

    @Override
    public void open(Player p) {
        this.construct();
        p.openInventory(this.GUI);
    }

    @Override
    void construct() {
        ArrayList<String> lore = new ArrayList<String>();
        ItemStack retour = new ItemStack(Material.ARROW);
        ItemMeta retourM = retour.getItemMeta();
        retourM.setDisplayName(RETOUR_NAME);
        retour.setItemMeta(retourM);
        ItemStack paysanPlusGrade = this.addGlow(new ItemStack(Material.LEATHER_CHESTPLATE));
        ItemMeta paysanPlusGradeM = paysanPlusGrade.getItemMeta();
        paysanPlusGradeM.setDisplayName(PAYSAN_PLUS_NAME);
        lore.add("§7§m-------------------");
        lore.add("");
        lore.add("§f§l» §eGrade par §6version §e!");
        lore.add("");
        lore.add("§6§nAvantages§6:");
        lore.add("");
        lore.add("§f• §fPrefix: §bMatelot §b");
        lore.add("§f• §f3 §fhomes disponibles");
        lore.add("§f• §f/kit Matelot (4 heures)");
        lore.add("§f• §f5 items dans le /HDV");
        lore.add("");
        lore.add("§f§l» §e(Click Gauche) Achat : §6{price}§e !".replace("{price}", String.valueOf(800) + " §bDoublons"));
        lore.add("");
        lore.add("§7§m-------------------");
        lore.add("");
        paysanPlusGradeM.setLore(lore);
        paysanPlusGrade.setItemMeta(paysanPlusGradeM);
        lore.clear();
        ItemStack chevalierGrade = this.addGlow(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        ItemMeta chevalierGradeM = chevalierGrade.getItemMeta();
        chevalierGradeM.setDisplayName(CHEVALIER_NAME);
        lore.add("§7§m-------------------");
        lore.add("");
        lore.add("§f§l» §eGrade par §6version §e!");
        lore.add("");
        lore.add("§6§nAvantages§6:");
        lore.add("");
        lore.add("§f• §fPrefix: §9Amiral §9");
        lore.add("§f• §f6 §fhomes disponibles");
        lore.add("§f• §f/kit Amiral (5 heures)");
        lore.add("§f• §fCommande /feed");
        lore.add("§f• §f10 items dans le /HDV");
        lore.add("§f• §a+ §fles avantages du grade précédent");
        lore.add("");
        lore.add("§f§l» §e(Click Gauche) Achat : §6{price}§e !".replace("{price}", String.valueOf(1500) + " §bDoublons"));
        lore.add("");
        lore.add("§7§m-------------------");
        lore.add("");
        chevalierGradeM.setLore(lore);
        chevalierGrade.setItemMeta(chevalierGradeM);
        lore.clear();
        ItemStack princeGrade = this.addGlow(new ItemStack(Material.IRON_CHESTPLATE));
        ItemMeta princeGradeM = princeGrade.getItemMeta();
        princeGradeM.setDisplayName(PRINCE_NAME);
        lore.add("§7§m-------------------");
        lore.add("");
        lore.add("§f§l» §eGrade par §6version §e!");
        lore.add("");
        lore.add("§6§nAvantages§6:");
        lore.add("");
        lore.add("§f• §fPrefix: §eLieutenant §e");
        lore.add("§f• §f9 §fhomes disponibles");
        lore.add("§f• §f/kit Lieutenant (6 heures)");
        lore.add("§f• §fCommande /craft");
        lore.add("§f• §fCommande /back");
        lore.add("§f• §fCommande /near");
        lore.add("§f• §f15 items dans le /HDV");
        lore.add("§f• §a+ §fles avantages du grade précédent");
        lore.add("");
        lore.add("§f§l» §e(Click Gauche) Achat : §6{price}§e  !".replace("{price}", String.valueOf(2000) + " §bDoublons"));
        lore.add("");
        lore.add("§7§m-------------------");
        lore.add("");
        princeGradeM.setLore(lore);
        princeGrade.setItemMeta(princeGradeM);
        lore.clear();
        ItemStack ducGrade = this.addGlow(new ItemStack(Material.GOLD_CHESTPLATE));
        ItemMeta ducGradeM = ducGrade.getItemMeta();
        ducGradeM.setDisplayName(DUC_NAME);
        lore.add("§7§m-------------------");
        lore.add("");
        lore.add("§f§l» §eGrade par §6version §e!");
        lore.add("");
        lore.add("§6§nAvantages§6:");
        lore.add("");
        lore.add("§f• §fPrefix: §aCapitaine §a");
        lore.add("§f• §f20 §fhomes disponibles");
        lore.add("§f• §f/kit Capitaine (6 jours) ");
        lore.add("§f• §fCommande /repair all (24h) ");
        lore.add("§f• §fCommande /repair (30m) ");
        lore.add("§f• §fCommande /enderchest");
        lore.add("§f• §f20 items dans le /HDV");
        lore.add("§f• §a+ §fles avantages du grade précédent");
        lore.add("");
        lore.add("§f§l» §e(Click Gauche) Achat : §6{price}§e !".replace("{price}", String.valueOf(3500) + " §bDoublons" ));
        lore.add("");
        lore.add("§7§m-------------------");
        lore.add("");
        ducGradeM.setLore(lore);
        ducGrade.setItemMeta(ducGradeM);
        lore.clear();

        this.GUI.setItem(0, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(1, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(7, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(8, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(44, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(52, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(52, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(46, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(45, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(36, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
        this.GUI.setItem(53, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));

        this.GUI.setItem(20, paysanPlusGrade);
        this.GUI.setItem(21, chevalierGrade);
        this.GUI.setItem(23, princeGrade);
        this.GUI.setItem(24, ducGrade);
        this.GUI.setItem(49, retour);
    }

    private ItemStack addGlow(ItemStack item) {
        ItemMeta i = item.getItemMeta();
        i.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        i.addEnchant(Enchantment.DURABILITY, 3, true);
        item.setItemMeta(i);
        return item;
    }
}

