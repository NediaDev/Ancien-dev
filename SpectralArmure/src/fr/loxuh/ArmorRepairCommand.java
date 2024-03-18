package fr.loxuh;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ArmorRepairCommand implements CommandExecutor {

    private final Main plugin;
    private final Set<UUID> repairedPlayers = new HashSet<>();

    public ArmorRepairCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Vérifier si le joueur a la permission "spectral.armure.repair"
            if (player.hasPermission("spectral.armure.repair")) {
                // Vérifier si le joueur a l'armure en diamant spectral complète
                if (hasFullSpectralArmor(player)) {
                    // Vérifier si le joueur a déjà utilisé la commande dans les 7 derniers jours
                    if (!repairedPlayers.contains(player.getUniqueId())) {
                        // Réparer chaque pièce d'armure en diamant spectral
                        ItemStack helmet = player.getInventory().getHelmet();
                        ItemStack chestplate = player.getInventory().getChestplate();
                        ItemStack leggings = player.getInventory().getLeggings();
                        ItemStack boots = player.getInventory().getBoots();

                        repairItem(helmet);
                        repairItem(chestplate);
                        repairItem(leggings);
                        repairItem(boots);

                        player.sendMessage(ChatColor.GREEN + "Votre armure Spectral a été réparée ! Vous devez attendre 1 semaine avant de pouvoir réutiliser la commande.");

                        repairedPlayers.add(player.getUniqueId());

                        // Planifier l'enlèvement du joueur de la liste après 1 semaine (20 ticks par seconde * 60 secondes * 60 minutes * 24 heures * 7 jours)
                        int ticksPerWeek = 20 * 60 * 60 * 24 * 7;
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                repairedPlayers.remove(player.getUniqueId());
                            }
                        }.runTaskLater(plugin, ticksPerWeek);
                    } else {
                        int ticksPerWeek = 20 * 60 * 60 * 24 * 7;
                        int ticksLeft = ticksPerWeek - player.getTicksLived();
                        int daysLeft = ticksLeft / (20 * 60 * 60 * 24);
                        int hoursLeft = (ticksLeft % (20 * 60 * 60 * 24)) / (20 * 60 * 60);
                        int minutesLeft = (ticksLeft % (20 * 60 * 60)) / (20 * 60);
                        player.sendMessage(ChatColor.RED + "Il vous reste " + daysLeft + " jours, " + hoursLeft + " heures et " + minutesLeft + " minutes avant de pouvoir réparer votre armure.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Vous devez porter une armure Spectral complète pour utiliser cette commande !");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande !");
            }
        } else {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur !");
        }
        return true;
    }
        

    private boolean hasFullSpectralArmor(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        return helmet != null && chestplate != null && leggings != null && boots != null &&
                helmet.getType() == Material.DIAMOND_HELMET &&
                chestplate.getType() == Material.DIAMOND_CHESTPLATE &&
                leggings.getType() == Material.DIAMOND_LEGGINGS &&
                boots.getType() == Material.DIAMOND_BOOTS &&
                hasSpectralDisplayName(helmet) &&
                hasSpectralDisplayName(chestplate) &&
                hasSpectralDisplayName(leggings) &&
                hasSpectralDisplayName(boots);
    }

    private boolean hasSpectralDisplayName(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.hasDisplayName() &&
                meta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6&l⚔ &e" + getArmorTypeString(item) + " Spectral &6&l⚔"));
    }

    private String getArmorTypeString(ItemStack item) {
        switch (item.getType()) {
            case DIAMOND_HELMET:
                return "Casque";
            case DIAMOND_CHESTPLATE:
                return "Plastron";
            case DIAMOND_LEGGINGS:
                return "Pantalon";
            case DIAMOND_BOOTS:
                return "Bottes";
            default:
                return "Armure";
        }
    }

    private void repairItem(ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            if (item.getType() == Material.DIAMOND_HELMET ||
                    item.getType() == Material.DIAMOND_CHESTPLATE ||
                    item.getType() == Material.DIAMOND_LEGGINGS ||
                    item.getType() == Material.DIAMOND_BOOTS) {
                // Réparer l'item
                item.setDurability((short) 0);
            }
        }
    }
}