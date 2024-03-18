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

import net.minecraft.server.v1_8_R3.ItemEnchantedBook;

import java.util.ArrayList;
import java.util.List;

public class EpeeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Utilisation incorrecte! Utilisation: /giveepee <joueur> <quantité>");
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
        ItemStack sword = createSpectralArmorPiece(Material.DIAMOND_SWORD, "&6&l⚔ &eÉpée Spectral &6&l⚔",
                "§7§l§m-------------------------",
                "§e§lRareté §f§l» §6§l✯✯✯✯✯",
                "§7§l§m-------------------------");

        applyEnchantments(sword);

        sword.setAmount(quantity);

        player.getInventory().addItem(sword);
        player.sendMessage(ChatColor.GREEN + "Vous avez reçu " + quantity + " l'épée spectrale !");
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
            if (item.getType() == Material.DIAMOND_SWORD) {

                ItemMeta meta = item.getItemMeta();
                meta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
                meta.addEnchant(Enchantment.DURABILITY, 5, true);
                meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
                item.setItemMeta(meta);
            }
        }
    }
}