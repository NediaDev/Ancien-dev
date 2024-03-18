package fr.loxuh.spectralboutique.listeners;

import net.milkbowl.vault.economy.*;
import org.bukkit.plugin.*;

import fr.loxuh.spectralboutique.*;
import fr.loxuh.spectralboutique.gui.GradesGUI;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.*;
import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.command.*;

public class GradesGUIListener implements Listener
{
    Main main;
    public static Economy economy;
    
    static {
        GradesGUIListener.economy = null;
    }
    
    public GradesGUIListener(final Main main) {
        this.main = main;
    }
    
    private boolean setupEconomy() {
        @SuppressWarnings({ "unchecked", "rawtypes" })
		final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>)Bukkit.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (economyProvider != null) {
            GradesGUIListener.economy = (Economy)economyProvider.getProvider();
        }
        return GradesGUIListener.economy != null;
    }
    
    @EventHandler
    public void onGUIClick(InventoryClickEvent event) throws IOException {
    	final Player p = (Player) event.getWhoClicked();

    	
    if (event.getClickedInventory() != null && p.getOpenInventory().getTitle().equalsIgnoreCase("§f§l» §eBoutique §fGrades")){
    	
    	
    	
    	
        event.setCancelled(true);

    	
    	if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getItemMeta().getDisplayName() != null) {

    		final ItemStack i = event.getCurrentItem();
                if (event.getClick() == ClickType.LEFT) {
                    switch (i.getItemMeta().getDisplayName()) {
                        case "§f§l» §eAchat §f: Matelot": {
                            p.closeInventory();
                            this.buyGradeVault(p, "Matelot");
                            break;
                        }
                        case "§f§l» §cRetour": {
                            p.closeInventory();
                            p.performCommand("dm open aide");
                            break;
                        }
                        case "§f§l» §eAchat §f: Amiral": {
                            p.closeInventory();
                            this.buyGradeVault(p, "Amiral");
                            break;
                        }
                        case "§f§l» §eAchat §f: Lieutenant": {
                            p.closeInventory();
                            this.buyGradeVault(p, "Lieutenant");
                            break;
                        }
                        case "§f§l» §eAchat §f: Capitaine": {
                            p.closeInventory();
                            this.buyGradeVault(p, "Capitaine");
                            break;
                        }
                        default:
                            break;
                    }
                }
                else if (event.getClick() == ClickType.RIGHT) {
					switch (i.getItemMeta().getDisplayName()) {
                        case "§f§l» §eAchat §f: Matelot": {
                            p.closeInventory();
                            break;
                        }
                        case "§f§l» §cRetour": {
                            p.closeInventory();
                            break;
                        }
                        case "§f§l» §eAchat §f: Amiral": {
                            p.closeInventory();
                            break;
                        }
                        case "§f§l» §eAchat §f: Lieutenant": {
                            p.closeInventory();
                            break;
                        }
                        case "§f§l» §eAchat §f: Capitaine": {
                            p.closeInventory();
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
    }
        }
    
    
    
    
    
    
    private void buyGradeVault(final Player p, final String grade) throws IOException {
        if (this.setupEconomy()) {
            @SuppressWarnings("deprecation")
			final double balance = GradesGUIListener.economy.getBalance(p.getName());
            if (grade.equalsIgnoreCase("Matelot")) {
                if (!p.hasPermission("vip.Fer")) {
                    if (balance >= 250000.0) {
                        this.boughtMessage(p, "Matelot");
                        GradesGUIListener.economy.withdrawPlayer((OfflinePlayer)p, 500000.0);
                        Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user {player} group set Matelot".replace("{player}", p.getName()));
                    }
                    else {
                        this.errorBought(p);
                    }
                }
                else {
                    this.alreadyHaveMessage(p, "Matelot");
                }
            }
            
            
            
            if (grade.equalsIgnoreCase("Amiral")) {
                if (!p.hasPermission("vip.Or")) {
                    if (p.hasPermission("vip.Fer")) {
                        if (balance >= 550000.0) {
                            GradesGUIListener.economy.withdrawPlayer((OfflinePlayer)p, 700000.0);
                            this.boughtMessage(p, "Or");
                            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user {player} group set Amiral".replace("{player}", p.getName()));
                        }
                        else {
                            this.errorBought(p);
                        }
                    }
                    else {
                        this.needLowerGradeMessage(p, "Matelot");
                    }
                }
                else {
                    this.alreadyHaveMessage(p, "Or");
                }
            }
            
            
            
            
            if (grade.equalsIgnoreCase("Lieutenant")) {
                if (!p.hasPermission("vip.Diamant")) {
                    if (p.hasPermission("vip.Or")) {
                        if (balance >= 1000000.0) {
                            GradesGUIListener.economy.withdrawPlayer((OfflinePlayer)p, 1000000.0);
                            this.boughtMessage(p, "Lieutenant");
                            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user {player} group set Lieutenant".replace("{player}", p.getName()));
                        }
                        else {
                            this.errorBought(p);
                        }
                    }
                    else {
                        this.needLowerGradeMessage(p, "Amiral");
                    }
                }
                else {
                    this.alreadyHaveMessage(p, "Diamant");
                }
            }
            
            
            if (grade.equalsIgnoreCase("Capitaine")) {
                if (!p.hasPermission("vip.Netherite")) {
                    if (p.hasPermission("vip.Diamant")) {
                        if (balance >= 5000000.0) {
                            GradesGUIListener.economy.withdrawPlayer((OfflinePlayer)p, 5000000.0);
                            this.boughtMessage(p, "Capitaine");
                            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user {player} group set Capitaine".replace("{player}", p.getName()));
                        }
                        else {
                            this.errorBought(p);
                        }
                    }
                    else {
                        this.needLowerGradeMessage(p, "Lieutenant");
                    }
                }
                else {
                    this.alreadyHaveMessage(p, "Capitaine");
                }
            }
            
            
        }
    }
    

    
    
    
    private void alreadyHaveMessage(final Player p, final String grade) {
    	p.sendMessage("§cVous avez déjà eu le grade {grade}".replace("{grade}", grade));
    	}
    
    private void needLowerGradeMessage(final Player p, final String grade) {
        p.sendMessage("§cVous devez avoir le grade {grade} pour faire cela.".replace("{grade}", grade));
    }
    
    private void boughtMessage(final Player p, final String name) {
        Bukkit.broadcastMessage("§8(§6§lSpectralMC§8) §f§l» {player} §eviens d'acheter le grade {name} !".replace("{player}", p.getName()).replace("{name}", name));
    }
    
    private void errorBought(final Player p) {
        p.sendMessage("§cTu n'a pas assez d'agent");
    }
}
