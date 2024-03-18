package fr.AtlantiCraft.core.listener.bottlexp;

import fr.AtlantiCraft.core.Main;

import org.bukkit.plugin.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;

public class BottlexpListener implements Listener
{
    private final Main main;

    public BottlexpListener(final Main main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)main);
    }

    @EventHandler
    public void onBottlexp(final PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }
        if ((e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK) || e.getAction().equals((Object)Action.RIGHT_CLICK_AIR)) && e.getItem().getType().equals((Object)Material.EXP_BOTTLE) && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().split(" ")[1].equalsIgnoreCase("niveaux") && e.getItem().getItemMeta().getDisplayName().startsWith("§d")) {
            e.setCancelled(true);
            final String level = e.getItem().getItemMeta().getDisplayName().replace(" niveaux", "").replace("§d", "");
            if (Integer.valueOf(level) > 100) {
                return;
            }
            e.getPlayer().setLevel(e.getPlayer().getLevel() + Integer.valueOf(level));
            if (e.getPlayer().getItemInHand().getAmount() > 1) {
                e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
            }
            else {
                e.getPlayer().setItemInHand((ItemStack)null);
            }
        }
    }
}
