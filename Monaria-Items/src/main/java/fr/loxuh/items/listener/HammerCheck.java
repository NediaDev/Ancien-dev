package fr.loxuh.items.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HammerCheck {

    public boolean isHammer(Player player) {

        if(!(player.getInventory().getItemInHand().hasItemMeta())) return false;

        if(player.getInventory().getItemInHand() == null) return false;

        if(!(player.getInventory().getItemInHand().getItemMeta().hasDisplayName())) return false;

        if(!player.getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§f§l▸ §6§l§nHammer")
                && player.getInventory().getItemInHand().getType() == Material.DIAMOND_PICKAXE
                && player.getInventory().getItemInHand().getItemMeta().hasLore()) {

            return false;
        }
        return true;
    }
}