package fr.loxuh.spectralboutique.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import fr.loxuh.spectralboutique.Main;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;

import java.io.IOException;
import java.util.List;

public class GradesVIP implements Listener {
    private Main main;

    public GradesVIP(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onGUIClick(InventoryClickEvent event) throws IOException {
        final Player p = (Player) event.getWhoClicked();

        if (event.getClickedInventory() != null && p.getOpenInventory().getTitle().equalsIgnoreCase("§f§l» §eBoutique §fGrades §4VIP")) {
            event.setCancelled(true);

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getItemMeta().getDisplayName() != null) {
                final ItemStack i = event.getCurrentItem();
                if (event.getClick() == ClickType.LEFT) {
                    switch (i.getItemMeta().getDisplayName()) {
                        case "§f§l» §eAchat §f: Matelot": {
                            p.closeInventory();
                            this.buyGradePlayerPoints(p, "Matelot", 800);
                            break;
                        }
                        case "§f§l» §cRetour": {
                            p.closeInventory();
                            p.performCommand("dm open aide");
                            break;
                        }
                        case "§f§l» §eAchat §f: Amiral": {
                            p.closeInventory();
                            this.buyGradePlayerPoints(p, "Amiral", 1500);
                            break;
                        }
                        case "§f§l» §eAchat §f: Lieutenant": {
                            p.closeInventory();
                            this.buyGradePlayerPoints(p, "Lieutenant", 2000);
                            break;
                        }
                        case "§f§l» §eAchat §f: Capitaine": {
                            p.closeInventory();
                            this.buyGradePlayerPoints(p, "Capitaine", 3500);
                            break;
                        }
                        default:
                            break;
                    }
                } else if (event.getClick() == ClickType.RIGHT) {
                    // Ajoutez un autre cas si vous souhaitez gérer un clic droit sur les items du GUI
                }
            }
        }
    }

    private void buyGradePlayerPoints(final Player p, final String grade, final int price) {
        PlayerPoints playerPoints = (PlayerPoints) Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints");
        if (playerPoints != null) {
            int playerPointsBalance = playerPoints.getAPI().look(p.getUniqueId());
            if (playerPointsBalance >= price) {
                switch (grade.toLowerCase()) {
                    case "matelot": {
                        if (!p.hasPermission("vip.Fer")) {
                            this.boughtMessage(p, "Matelot");
                            playerPoints.getAPI().take(p.getUniqueId(), price);
                            Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "lp user " + p.getName() + " group set Matelot");
                            break;
                        } else {
                            this.alreadyHaveMessage(p, "Matelot");
                            break;
                        }
                    }
                    case "amiral": {
                        if (!p.hasPermission("vip.Or") && p.hasPermission("vip.Fer")) {
                            this.boughtMessage(p, "Amiral");
                            playerPoints.getAPI().take(p.getUniqueId(), price);
                            Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "lp user " + p.getName() + " group set Amiral");
                            break;
                        } else if (p.hasPermission("vip.Or")) {
                            this.alreadyHaveMessage(p, "Amiral");
                            break;
                        } else {
                            this.needLowerGradeMessage(p, "Matelot");
                            break;
                        }
                    }
                    case "lieutenant": {
                        if (!p.hasPermission("vip.Diamant") && p.hasPermission("vip.Or")) {
                            this.boughtMessage(p, "Lieutenant");
                            playerPoints.getAPI().take(p.getUniqueId(), price);
                            Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "lp user " + p.getName() + " group set Lieutenant");
                            break;
                        } else if (p.hasPermission("vip.Diamant")) {
                            this.alreadyHaveMessage(p, "Lieutenant");
                            break;
                        } else {
                            this.needLowerGradeMessage(p, "Amiral");
                            break;
                        }
                    }
                    case "capitaine": {
                        if (!p.hasPermission("vip.Netherite") && p.hasPermission("vip.Diamant")) {
                            this.boughtMessage(p, "Capitaine");
                            playerPoints.getAPI().take(p.getUniqueId(), price);
                            Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "lp user " + p.getName() + " group set Capitaine");
                            break;
                        } else if (p.hasPermission("vip.Netherite")) {
                            this.alreadyHaveMessage(p, "Capitaine");
                            break;
                        } else {
                            this.needLowerGradeMessage(p, "Lieutenant");
                            break;
                        }
                    }
                    default:
                        break;
                }
            } else {
                this.errorBought(p);
            }
        }
    }

    private void alreadyHaveMessage(final Player p, final String grade) {
        p.sendMessage(ChatColor.RED + "Vous avez déjà le grade " + ChatColor.GOLD + grade);
    }

    private void needLowerGradeMessage(final Player p, final String grade) {
        p.sendMessage(ChatColor.RED + "Vous devez avoir le grade " + ChatColor.GOLD + grade + ChatColor.RED + " pour effectuer cet achat.");
    }

    private void boughtMessage(final Player p, final String name) {
        p.sendMessage(ChatColor.GRAY + "(" + ChatColor.GOLD + "SpectralMC" + ChatColor.GRAY + ")" + ChatColor.WHITE + " » " + ChatColor.GREEN + "Vous avez acheté le grade " + ChatColor.GOLD + name + ChatColor.GREEN + " !");
    }

    private void errorBought(final Player p) {
        p.sendMessage(ChatColor.RED + "Vous n'avez pas assez de points pour effectuer cet achat.");
    }
}