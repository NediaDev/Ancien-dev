package fr.loxuh.items.event;

import fr.loxuh.items.Main;
import fr.loxuh.items.items.axe.AxeItem;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class MelonMultiplier implements Listener {

    private Economy economy;
    public MelonMultiplier() {
        economy = Main.economy;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() == Material.MELON_BLOCK) {
            ItemStack handItem = player.getItemInHand();

            if (handItem != null && handItem.isSimilar(AxeItem.createAxe())) {
                if (handItem.getEnchantmentLevel(Enchantment.DIG_SPEED) == 10 && handItem.getEnchantmentLevel(Enchantment.DURABILITY) == 50) {
                    // Générez un nombre aléatoire entre 1 et 100 pour représenter la chance de 10 %
                    int chance = (int) (Math.random() * 100) + 1;

                    if (chance <= 10) { // Si le nombre aléatoire est de 1 à 10, le joueur reçoit de l'argent
                        int melonAmount = (int) (Math.random() * 10000) + 20000;
                        double moneyAmount = (double) melonAmount;
                        Main.economy.depositPlayer(player, moneyAmount);

                        // Formatez le montant en nombre rond
                        DecimalFormat df = new DecimalFormat("#,###");
                        String formattedMoney = df.format(moneyAmount);

                        // Envoyer un message au joueur avec le montant formaté
                        player.sendMessage("§aVous avez reçu §e " + formattedMoney + " §a$ en récoltant des melons !");
                    }
                }
            }
        }
    }


}