package fr.loxuh.farm;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FarmCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Utilisation incorrecte! Utilisation: /givefarm <joueur> <quantité>");
            return true;
        }

        Player targetPlayer = sender.getServer().getPlayer(args[0]);
        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Le joueur spécifié n'est pas en ligne ou n'existe pas!");
            return true;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "La quantité doit être un nombre entier!");
            return true;
        }

        if (quantity <= 0) {
            sender.sendMessage(ChatColor.RED + "La quantité doit être supérieure à zéro!");
            return true;
        }

        giveSpectralArmor(targetPlayer, quantity);
        return true;
    }

    private void giveSpectralArmor(Player player, int quantity) {
        ItemStack helmet = createSpectralArmorPiece(Material.LEATHER_HELMET, "&6&l⚔ &aCasque de Farm &6&l⚔",
                "§7§l§m-------------------------",
                "§e§lRareté §f§l» §6§l✯✯§7§L✯✯✯",
                "§f● §7Ce casque §ade Farm §7te permet de",
                "§7d'avoir l'effet §eSpeed 2",
                "§7§l§m-------------------------");
        ItemStack chestplate = createSpectralArmorPiece(Material.LEATHER_CHESTPLATE, "&6&l⚔ &aPlastron de Farm &6&l⚔",
                "§7§l§m-------------------------",
                "§e§lRareté §f§l» §6§l✯✯§7§L✯✯✯",
                "§f● §7Ce plastron §ade Farm §7te permet de",
                "§7d'avoir l'effet §eHaste 2",
                "§7§l§m-------------------------");
        ItemStack leggings = createSpectralArmorPiece(Material.LEATHER_LEGGINGS, "&6&l⚔ &aPantalon de Farm &6&l⚔",
                "§7§l§m-------------------------",
                "§e§lRareté §f§l» §6§l✯✯§7§L✯✯✯",
                "§f● §7Ce pantalon §ade Farm §7te permet de",
                "§7d'avoir l'effet §eFire resistance 2",
                "§7§l§m-------------------------");
        ItemStack boots = createSpectralArmorPiece(Material.LEATHER_BOOTS, "&6&l⚔ &aBottes de Farm &6&l⚔",
                "§7§l§m-------------------------",
                "§e§lRareté §f§l» §6§l✯✯§7§L✯✯✯",
                "§f● §7C'est Bottes §ade Farm §7te permet de",
                "§7d'avoir l'effet §eInvisibilité 2",
                "§7§l§m-------------------------");

        applyEnchantments(helmet);
        applyEnchantments(chestplate);
        applyEnchantments(leggings);
        applyEnchantments(boots);

        helmet.setAmount(quantity);
        chestplate.setAmount(quantity);
        leggings.setAmount(quantity);
        boots.setAmount(quantity);

        player.getInventory().addItem(helmet, chestplate, leggings, boots);
        player.sendMessage(ChatColor.GREEN + "Vous avez reçu " + quantity + " armure(s) de farm!");
    }

    private ItemStack createSpectralArmorPiece(Material material, String customName, String... loreLines) {
        ItemStack armorPiece = new ItemStack(material);
        ItemMeta meta = armorPiece.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', customName));

        // Créer une liste pour le lore et ajouter les descriptions
        List<String> loreList = new ArrayList<>();
        for (String lore : loreLines) {
            loreList.add(ChatColor.translateAlternateColorCodes('&', lore));
        }
        meta.setLore(loreList);

        armorPiece.setItemMeta(meta);
        return armorPiece;
    }

    private void applyEnchantments(ItemStack item) {
        if (item != null) {
            if (item.getType() == Material.LEATHER_HELMET ||
                item.getType() == Material.LEATHER_CHESTPLATE ||
                item.getType() == Material.LEATHER_LEGGINGS ||
                item.getType() == Material.LEATHER_BOOTS) {
            	
                ItemMeta meta = item.getItemMeta();
                item.setItemMeta(meta);

            }
        }
    }
}