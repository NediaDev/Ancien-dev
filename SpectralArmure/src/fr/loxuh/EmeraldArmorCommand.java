package fr.loxuh;

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

public class EmeraldArmorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Utilisation incorrecte! Utilisation: /givearmure <joueur> <quantité>");
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
        ItemStack helmet = createSpectralArmorPiece(Material.DIAMOND_HELMET, "&6&l⚔ &eCasque Spectral &6&l⚔",
                "§7§l§m-------------------------",
                "§e§lRareté §f§l» §6§l✯✯✯✯✯",
                "§f● §7Ce casque §eSpectral §7te permet de",
                "§7d'avoir l'effet §6Speed 2",
                "§7§l§m-------------------------");
        ItemStack chestplate = createSpectralArmorPiece(Material.DIAMOND_CHESTPLATE, "&6&l⚔ &ePlastron Spectral &6&l⚔",
                "§7§l§m-------------------------",
                "§e§lRareté §f§l» §6§l✯✯✯✯✯",
                "§f● §7Ce plastron §eSpectral §7te permet de",
                "§7d'avoir l'effet §6Force 1",
                "§7§l§m-------------------------");
        ItemStack leggings = createSpectralArmorPiece(Material.DIAMOND_LEGGINGS, "&6&l⚔ &ePantalon Spectral &6&l⚔",
                "§7§l§m-------------------------",
                "§e§lRareté §f§l» §6§l✯✯✯✯✯",
                "§f● §7Ce pantalon §eSpectral §7te permet de",
                "§7d'avoir l'effet §6Fire Resistance 2",
                "§7§l§m-------------------------");
        ItemStack boots = createSpectralArmorPiece(Material.DIAMOND_BOOTS, "&6&l⚔ &eBottes Spectral &6&l⚔",
                "§7§l§m-------------------------",
                "§e§lRareté §f§l» §6§l✯✯✯✯✯",
                "§f● §7C'est bottes §eSpectral §7te permet de",
                "§7d'avoir l'effet §6No Fall",
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
        player.sendMessage(ChatColor.GREEN + "Vous avez reçu " + quantity + " armure(s) spectrale(s)!");
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
            if (item.getType() == Material.DIAMOND_HELMET ||
                item.getType() == Material.DIAMOND_CHESTPLATE ||
                item.getType() == Material.DIAMOND_LEGGINGS ||
                item.getType() == Material.DIAMOND_BOOTS) {

                ItemMeta meta = item.getItemMeta();
                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                meta.addEnchant(Enchantment.DURABILITY, 5, true);
                item.setItemMeta(meta);
            }
        }
    }
}