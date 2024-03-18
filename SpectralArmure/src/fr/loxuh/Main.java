package fr.loxuh;

import org.bukkit.plugin.java.*;

import fr.loxuh.farm.FarmCommand;
import fr.loxuh.farm.ListenerFarm;

import org.bukkit.plugin.*;

public class Main extends JavaPlugin
{
    private Listener bm;
    private ListenerFarm farm;

    
    public void onEnable() {
        this.bm = new Listener(this);
        this.farm = new ListenerFarm(this);

        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents((org.bukkit.event.Listener)this.bm, (Plugin)this);

        this.getCommand("givearmure").setExecutor(new EmeraldArmorCommand());
        this.getCommand("giveepee").setExecutor(new EpeeCommand());

        getCommand("armurerepair").setExecutor(new ArmorRepairCommand(this));
        
        this.getCommand("givefarm").setExecutor(new FarmCommand());
        pm.registerEvents((org.bukkit.event.Listener)this.farm, (Plugin)this);


    }
    
    public void onDisable() {
    }
    
}
