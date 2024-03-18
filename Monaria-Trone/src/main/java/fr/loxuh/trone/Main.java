package fr.loxuh.trone;

import fr.loxuh.trone.event.EventTrone;
import fr.loxuh.trone.event.TroneHolderImpl;
import org.bukkit.plugin.java.*;
import net.milkbowl.vault.economy.*;

import org.bukkit.plugin.*;

public class Main extends JavaPlugin
{
    private EventTrone eventTrone;
    private Economy economy;

    public Main() {
        this.economy = null;
    }

    public void onEnable() {
        if (!this.getDataFolder().exists()) {
            this.saveDefaultConfig();
        }
        this.setupEconomy();
        new TroneHolderImpl().register();
        this.eventTrone = new EventTrone(this.getConfig());
        this.registerCommands();
    }

    private void registerCommands() {
    }

    private boolean setupEconomy() {
        final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>)this.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (economyProvider != null) {
            this.economy = (Economy)economyProvider.getProvider();
        }
        return this.economy != null;
    }

    public static String getMessage(final String key) {
        return getInstance().getConfig().getString("messages." + key);
    }

    public static Main getInstance() {
        return (Main)getPlugin((Class)Main.class);
    }

    public EventTrone getEventTrone() {
        return this.eventTrone;
    }

    public Economy getEconomy() {
        return this.economy;
    }
}
