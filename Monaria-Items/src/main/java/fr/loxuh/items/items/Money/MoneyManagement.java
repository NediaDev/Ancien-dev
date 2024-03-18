package fr.loxuh.items.items.Money;

import fr.loxuh.items.items.moissoneuse.MegaMoneyItem;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class MoneyManagement {
    private final Economy economy;

    public MoneyManagement(Economy economy) {
        this.economy = economy;
    }

    public void onMegaMoneyClick(Player player, ItemStack itemStack) {
        if (itemStack != null
                && itemStack.getType() == Material.INK_SACK && itemStack.getDurability() == (short) 9
                && itemStack.hasItemMeta()
                && itemStack.getItemMeta().hasDisplayName()
                && itemStack.getItemMeta().getDisplayName().equals("§f§l▸ §5§l§nMegaMoney")) {
            double amount = Math.random() * 3000000 + 2000000; // entre 2 et 5 millions
            if (economy != null && itemStack != null && MegaMoneyItem.isMegaMoney(itemStack)) {
                amount = roundToMillion(amount); // Arrondir le montant à la valeur en millions la plus proche
                String formattedAmount = formatMoney(amount);
                economy.depositPlayer(player, amount);
                player.sendMessage("§aVous avez reçu §e" + formattedAmount + " §a$");

                // Enlever un "MegaMoney" de l'inventaire du joueur
                PlayerInventory inventory = player.getInventory();
                int slot = inventory.first(itemStack);
                if (slot >= 0) {
                    ItemStack itemInSlot = inventory.getItem(slot);
                    if (itemInSlot.getAmount() > 1) {
                        itemInSlot.setAmount(itemInSlot.getAmount() - 1);
                    } else {
                        inventory.setItem(slot, null);
                    }
                }
            } else {
                player.sendMessage("§cVous ne pouvez pas utiliser cet item.");
            }
        }
    }

    private String formatMoney(double amount) {
        if (amount >= 1000000) {
            return (long) amount / 1000000 + "M";
        } else {
            return (long) amount + "";
        }
    }

    private double roundToMillion(double amount) {
        return Math.round(amount / 1000000) * 1000000;
    }
}