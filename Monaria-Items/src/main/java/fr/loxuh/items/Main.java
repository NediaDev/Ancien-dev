package fr.loxuh.items;


import fr.loxuh.items.cmd.ItemCommand;
import fr.loxuh.items.event.BlockBreakListener;
import fr.loxuh.items.event.MelonMultiplier;
import fr.loxuh.items.items.Money.MoneyManagement;
import fr.loxuh.items.listener.HammerBreakEvent;
import fr.loxuh.items.listener.HammerCheck;
import fr.loxuh.items.listener.ToolListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener {

    private MoneyManagement moneyManagement;
    private static Main instance;


    public static Economy economy;



    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(this, this);

        PluginManager pm = Bukkit.getPluginManager();
        getServer().getPluginManager().registerEvents(new HammerBreakEvent(new HammerCheck()), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new MelonMultiplier(), this);
        getServer().getPluginManager().registerEvents(new ToolListener(), this);


        moneyManagement = new MoneyManagement(getEconomy());
        getCommand("it").setExecutor(new ItemCommand());

        if (!setupEconomy()) {
            getLogger().severe("Vault and an economy plugin (e.g., Essentials) are required to use this plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.hasItem()) {
            moneyManagement.onMegaMoneyClick(event.getPlayer(), event.getItem());
        }
    }


    private Economy getEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            return rsp.getProvider();
        }
        return null;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static Main getInstance() {
        return instance;
    }


   }