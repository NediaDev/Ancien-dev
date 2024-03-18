package fr.AtlantiCraft.core.listener.MENU;

import fr.AtlantiCraft.core.Main;

import fr.AtlantiCraft.core.utils.ItemBuilder;
import org.bukkit.plugin.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;

import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;

public final class BoutiqueListener implements Listener
{
    private final Main main;

    public BoutiqueListener(final Main main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)main);
    }

    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        if (!e.getInventory().getTitle().equalsIgnoreCase("§8Boutique")) {
            return;
        }
        e.setCancelled(true);
        final Player p = (Player)e.getWhoClicked();
        switch (e.getCurrentItem().getType()) {
            case EMERALD: {
                final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§8WeeklyShop");
                final ItemStack v = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte)10).setName("").toItemStack();
                final ItemStack close = new ItemBuilder(Material.ARROW).setName("§eRevenir en arri\u00e8re").toItemStack();
                final ItemStack back = new ItemBuilder(Material.BARRIER).setName("§cFermer l'inventaire").toItemStack();
                for (int i = 0; i < 10; ++i) {
                    inv.setItem(i, v);
                }
                for (int i = 44; i < 54; ++i) {
                    inv.setItem(i, v);
                }
                inv.setItem(17, v);
                inv.setItem(18, v);
                inv.setItem(26, v);
                inv.setItem(27, v);
                inv.setItem(35, v);
                inv.setItem(36, v);
              /* if (WeeklyItem.weeklyItems.size() == 0) {
                    p.openInventory(inv);
                }
                for (final WeeklyItem weeklyItem : WeeklyItem.weeklyItems.values()) {
                    final ItemStack is = weeklyItem.getItemStack();
                    final ItemMeta im = is.getItemMeta();
                    im.setLore((List)Arrays.asList("§eCette item est §dlimit\u00e9", "§eDisponibilit\u00e9§8» §c" + weeklyItem.getDispo(), " ", "§ePrix§8» §c" + weeklyItem.getPrix() + "$"));
                    is.setItemMeta(im);
                    inv.setItem(this.getSlot(weeklyItem.getSlot()), is);
                }*/
                p.closeInventory();
                p.openInventory(inv);
                p.closeInventory();
                p.openInventory(inv);
                break;
            }
            case DIAMOND_CHESTPLATE: {
                //   p.openInventory(this.main.guiManager.gradeGui.getInventory());
                break;
            }
            case BLAZE_ROD: {
                //   p.openInventory(this.main.guiManager.kitGui.getInventory());
                break;
            }
            case MOB_SPAWNER: {
                //  p.openInventory(this.main.guiManager.spawnerGui.getInventory());
                break;
            }
            case PAPER: {
                //    p.openInventory(this.main.guiManager.commandGui.getInventory());
                break;
            }
            case TRIPWIRE_HOOK: {
                //    p.openInventory(this.main.guiManager.cleGui.getInventory());
                break;
            }
            case ARROW: {
          //      p.openInventory(this.main.guiManager.mainGui.getInventory(Bukkit.getOnlinePlayers().size()));
                break;
            }
            case BARRIER: {
                p.closeInventory();
                break;
            }
        }
    }

    private int getSlot(final int slot) {
        if (slot >= 1 && slot <= 7) {
            return slot + 9;
        }
        if (slot >= 8 && slot <= 14) {
            return slot + 11;
        }
        if (slot >= 15 && slot <= 21) {
            return slot + 13;
        }
        if (slot >= 22 && slot <= 28) {
            return slot + 15;
        }
        return 0;
    }
}
