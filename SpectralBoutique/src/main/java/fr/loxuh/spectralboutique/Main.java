package fr.loxuh.spectralboutique;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.loxuh.spectralboutique.commands.BoutiqueCMD;
import fr.loxuh.spectralboutique.commands.BoutiqueVip;
import fr.loxuh.spectralboutique.listeners.GradesGUIListener;
import fr.loxuh.spectralboutique.listeners.GradesVIP;

public class Main
extends JavaPlugin {
    public Economy economy = null;

    public void onEnable() {

        if (!this.setupEconomy()) {
            Bukkit.getConsoleSender().sendMessage("§cVous n'avez pas vault !");
        }
        this.getCommand("rankup").setExecutor((CommandExecutor)new BoutiqueCMD(this));

        getCommand("rankupvip").setExecutor(new BoutiqueVip(this));
        
        this.getServer().getPluginManager().registerEvents((Listener)new GradesGUIListener(this), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new GradesVIP(this), (Plugin)this);



    }

    public boolean setupEconomy() {
        @SuppressWarnings("rawtypes")
		RegisteredServiceProvider economyProvider = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            this.economy = (Economy)economyProvider.getProvider();
        }
        return this.economy != null;
    }

    

}

