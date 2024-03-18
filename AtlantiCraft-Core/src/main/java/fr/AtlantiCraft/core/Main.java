package fr.AtlantiCraft.core;

import fr.AtlantiCraft.core.gui.GuiManager;
import fr.AtlantiCraft.core.listener.MENU.BoutiqueListener;
import fr.AtlantiCraft.core.listener.bottlexp.BottlexpListener;

import fr.AtlantiCraft.core.utils.command.iCommandManager;

import org.bukkit.plugin.java.*;
import com.google.common.collect.*;

import org.bukkit.event.*;
import org.bukkit.plugin.*;
import java.util.*;
import com.google.gson.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import java.lang.reflect.*;

public class Main extends JavaPlugin
{
    private static Main instance;
    private Gson gson;

    public final HashMap<Material, Material> furnace;

    public GuiManager guiManager;

    public Main() {
        this.furnace = new HashMap<Material, Material>();
    }
    public void onEnable() {
        Main.instance = this;
        this.getDataFolder().mkdir();
        this.registerCommands();
        this.registerListeners();
        this.guiManager = new GuiManager();
    }

    private void registerCommands() {
        new iCommandManager(this);
    }

    private void registerListeners() {
        new BottlexpListener(this);
        new BoutiqueListener(this);
    }



    public void registerListener(final Listener listener) {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(listener, (Plugin)this);
    }



    public void onDisable() {
    }


    public static Main getInstance() {
        return Main.instance;
    }




    public Gson getGson() {
        return this.gson;
    }


    private void initFurnace() {
        this.furnace.put(Material.IRON_ORE, Material.IRON_INGOT);
        this.furnace.put(Material.GOLD_ORE, Material.GOLD_INGOT);
        this.furnace.put(Material.COAL_ORE, Material.COAL);
        this.furnace.put(Material.DIAMOND_ORE, Material.DIAMOND);
        this.furnace.put(Material.EMERALD_ORE, Material.EMERALD);
        this.furnace.put(Material.RAW_BEEF, Material.COOKED_BEEF);
        this.furnace.put(Material.RAW_CHICKEN, Material.COOKED_CHICKEN);
        this.furnace.put(Material.RAW_FISH, Material.COOKED_FISH);
        this.furnace.put(Material.COBBLESTONE, Material.STONE);
        this.furnace.put(Material.RABBIT, Material.COOKED_RABBIT);
        this.furnace.put(Material.MUTTON, Material.COOKED_MUTTON);
        this.furnace.put(Material.PORK, Material.GRILLED_PORK);
        this.furnace.put(Material.CLAY_BALL, Material.CLAY_BRICK);
        this.furnace.put(Material.SAND, Material.GLASS);
        this.furnace.put(Material.POTATO, Material.BAKED_POTATO);
        this.furnace.put(Material.QUARTZ_ORE, Material.QUARTZ);
    }
}
