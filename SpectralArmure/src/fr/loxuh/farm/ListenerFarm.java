package fr.loxuh.farm;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ListenerFarm implements org.bukkit.event.Listener {

    private final JavaPlugin plugin;
    private final Set<UUID> playersWithFarmBoots = new HashSet<>();

    public ListenerFarm(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        boolean hasFullLEATHERArmor = hasFullLEATHERArmor(player);
        boolean hasFullSpectralArmor = hasFullSpectralArmor(player);

        if (hasFullSpectralArmor && !playersWithFarmBoots.contains(player.getUniqueId())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));

    
            playersWithFarmBoots.add(player.getUniqueId());
        } else if (!hasFullSpectralArmor && playersWithFarmBoots.contains(player.getUniqueId())) {
            player.removePotionEffect(PotionEffectType.SPEED);
            player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            player.removePotionEffect(PotionEffectType.FAST_DIGGING);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);

            playersWithFarmBoots.remove(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (hasFullSpectralArmor(p)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));

            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (playersWithFarmBoots.contains(player.getUniqueId())) {
            playersWithFarmBoots.remove(player.getUniqueId());
            }
        
    }

    private boolean hasFullLEATHERArmor(Player player) {
        return player.getInventory().getHelmet() != null &&
                player.getInventory().getChestplate() != null &&
                player.getInventory().getLeggings() != null &&
                player.getInventory().getBoots() != null &&
                player.getInventory().getHelmet().getType() == Material.LEATHER_HELMET &&
                player.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE &&
                player.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS &&
                player.getInventory().getBoots().getType() == Material.LEATHER_BOOTS;
    }

    private boolean hasFullSpectralArmor(Player player) {
        return player.getInventory().getHelmet() != null &&
                player.getInventory().getChestplate() != null &&
                player.getInventory().getLeggings() != null &&
                player.getInventory().getBoots() != null &&
                player.getInventory().getHelmet().getType() == Material.LEATHER_HELMET &&
                player.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE &&
                player.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS &&
                player.getInventory().getBoots().getType() == Material.LEATHER_BOOTS &&
                player.getInventory().getHelmet().getItemMeta() != null &&
                player.getInventory().getChestplate().getItemMeta() != null &&
                player.getInventory().getLeggings().getItemMeta() != null &&
                player.getInventory().getBoots().getItemMeta() != null &&
                player.getInventory().getHelmet().getItemMeta().hasDisplayName() &&
                player.getInventory().getChestplate().getItemMeta().hasDisplayName() &&
                player.getInventory().getLeggings().getItemMeta().hasDisplayName() &&
                player.getInventory().getBoots().getItemMeta().hasDisplayName() &&
                player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &aCasque de Farm &6&l⚔")) &&
                player.getInventory().getChestplate().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &aPlastron de Farm &6&l⚔")) &&
                player.getInventory().getLeggings().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &aPantalon de Farm &6&l⚔")) &&
                player.getInventory().getBoots().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &aBottes de Farm &6&l⚔"));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL) {
            Player player = (Player) event.getWhoClicked();
            AnvilInventory anvilInventory = (AnvilInventory) event.getInventory();
            ItemStack firstSlot = anvilInventory.getItem(0);
            ItemStack secondSlot = anvilInventory.getItem(1);

            if (firstSlot != null && isSpectralArmor(firstSlot)) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Vous ne pouvez pas utiliser l'enclume avec l'armure Spectral !");
            }

            if (secondSlot != null && isSpectralArmor(secondSlot)) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Vous ne pouvez pas utiliser l'enclume avec l'armure Spectral !");
            }
        }
    }

    private boolean isSpectralArmor(ItemStack item) {
        return item.getItemMeta() != null &&
                item.getItemMeta().hasDisplayName() &&
                (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &aCasque de Farm &6&l⚔")) ||
                        item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &aPlastron de Farm &6&l⚔")) ||
                        item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &aPantalon de Farm &6&l⚔")) ||
                        item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &aBottes de Farm &6&l⚔")));
    }

    private ItemStack createSpectralArmorPiece(Material material, String customName) {
        ItemStack armorPiece = new ItemStack(material);
        ItemMeta meta = armorPiece.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', customName));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Ligne de lore 1");
        lore.add(ChatColor.YELLOW + "Ligne de lore 2");
        // Ajoutez d'autres lignes de lore si nécessaire
        meta.setLore(lore);

        armorPiece.setItemMeta(meta);
        return armorPiece;
    }

    private void applyEnchantments(ItemStack item) {
        if (item != null) {
            if (item.getType() == Material.LEATHER_HELMET ||
                    item.getType() == Material.LEATHER_CHESTPLATE ||
                    item.getType() == Material.LEATHER_LEGGINGS ||
                    item.getType() == Material.LEATHER_BOOTS) {

                ItemMeta meta = item.getItemMeta();
                item.setItemMeta(meta);
            }
        }
    }
}