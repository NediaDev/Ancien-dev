package fr.loxuh;


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

public class Listener implements org.bukkit.event.Listener {

    private final JavaPlugin plugin;
    private final Set<UUID> playersWithEmeraldBoots = new HashSet<>();

    public Listener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        boolean hasFullDiamondArmor = hasFullDiamondArmor(player);
        boolean hasFullSpectralArmor = hasFullSpectralArmor(player);

        if (hasFullSpectralArmor && !playersWithEmeraldBoots.contains(player.getUniqueId())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));

            String permission = "atout.nofall";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set " + permission);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                if (player.hasPermission(permission)) {
                    player.performCommand("atoutnofall");
                }
            }, 40);
            playersWithEmeraldBoots.add(player.getUniqueId());
        } else if (!hasFullSpectralArmor && playersWithEmeraldBoots.contains(player.getUniqueId())) {
            player.removePotionEffect(PotionEffectType.SPEED);
            player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

            String permission = "atout.nofall";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission unset " + permission);
            if (player.hasPermission(permission)) {
                player.performCommand("atoutnofall");
            }
            playersWithEmeraldBoots.remove(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (hasFullSpectralArmor(p)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (playersWithEmeraldBoots.contains(player.getUniqueId())) {
            playersWithEmeraldBoots.remove(player.getUniqueId());
            String permission = "atout.nofall";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission unset " + permission);
            if (player.hasPermission(permission)) {
                player.performCommand("atoutnofall");
            }
        }
    }

    private boolean hasFullDiamondArmor(Player player) {
        return player.getInventory().getHelmet() != null &&
                player.getInventory().getChestplate() != null &&
                player.getInventory().getLeggings() != null &&
                player.getInventory().getBoots() != null &&
                player.getInventory().getHelmet().getType() == Material.DIAMOND_HELMET &&
                player.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE &&
                player.getInventory().getLeggings().getType() == Material.DIAMOND_LEGGINGS &&
                player.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS;
    }

    private boolean hasFullSpectralArmor(Player player) {
        return player.getInventory().getHelmet() != null &&
                player.getInventory().getChestplate() != null &&
                player.getInventory().getLeggings() != null &&
                player.getInventory().getBoots() != null &&
                player.getInventory().getHelmet().getType() == Material.DIAMOND_HELMET &&
                player.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE &&
                player.getInventory().getLeggings().getType() == Material.DIAMOND_LEGGINGS &&
                player.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS &&
                player.getInventory().getHelmet().getItemMeta() != null &&
                player.getInventory().getChestplate().getItemMeta() != null &&
                player.getInventory().getLeggings().getItemMeta() != null &&
                player.getInventory().getBoots().getItemMeta() != null &&
                player.getInventory().getHelmet().getItemMeta().hasDisplayName() &&
                player.getInventory().getChestplate().getItemMeta().hasDisplayName() &&
                player.getInventory().getLeggings().getItemMeta().hasDisplayName() &&
                player.getInventory().getBoots().getItemMeta().hasDisplayName() &&
                player.getInventory().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &eCasque Spectral &6&l⚔")) &&
                player.getInventory().getChestplate().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &ePlastron Spectral &6&l⚔")) &&
                player.getInventory().getLeggings().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &ePantalon Spectral &6&l⚔")) &&
                player.getInventory().getBoots().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &eBottes Spectral &6&l⚔"));
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
                (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &eCasque Spectral &6&l⚔")) ||
                        item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &ePlastron Spectral &6&l⚔")) ||
                        item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &ePantalon Spectral &6&l⚔")) ||
                        item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &eBottes Spectral &6&l⚔")));
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
            if (item.getType() == Material.DIAMOND_HELMET ||
                    item.getType() == Material.DIAMOND_CHESTPLATE ||
                    item.getType() == Material.DIAMOND_LEGGINGS ||
                    item.getType() == Material.DIAMOND_BOOTS) {

                ItemMeta meta = item.getItemMeta();
                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                meta.addEnchant(Enchantment.DURABILITY, 5, true);
                item.setItemMeta(meta);
            }
        }
    }
}