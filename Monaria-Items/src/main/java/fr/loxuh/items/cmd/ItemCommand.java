package fr.loxuh.items.cmd;

import fr.loxuh.items.items.moissoneuse.MoissoneuseItem;
import fr.loxuh.items.items.axe.AxeItem;
import fr.loxuh.items.items.diamant.DiamantPrecieuxItem;
import fr.loxuh.items.items.megatools.MegaToolItem;
import fr.loxuh.items.items.moissoneuse.MegaMoneyItem;
import fr.loxuh.items.items.hammer.HammerItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;

public class ItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("it")) {
            if (args.length == 0) {
                // Affiche un message d'aide général
                sender.sendMessage("§3===§b=============================================§3===");
                sender.sendMessage("- §bPlugins§7: MonariaItems");
                sender.sendMessage("- §bVersion§7: 1.0");
                sender.sendMessage("- §bCréateur§7: Loxuh");
                sender.sendMessage("§3===§b=============================================§3===");

            } else if (args.length >= 1 && args[0].equalsIgnoreCase("give")) {
                if (args.length >= 4 && args[3].matches("\\d+")) {
                    if (sender.hasPermission("monariaitems.give")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target == null) {
                            sender.sendMessage("§cLe joueur cible n'est pas en ligne.");
                            return true;
                        }

                        String itemName = args[2];
                        int quantity = Integer.parseInt(args[3]);
                        ItemStack itemStack = null;

                        if (itemName.equalsIgnoreCase("MegaMoney")) {
                            itemStack = MegaMoneyItem.createMegaMoney();
                        } else if (itemName.equalsIgnoreCase("Hammer")) {
                            itemStack = HammerItem.createHammer();
                        } else if (itemName.equalsIgnoreCase("Moissoneuse")) {
                            itemStack = MoissoneuseItem.createMoissoneuse();
                        } else if (itemName.equalsIgnoreCase("DiamantPrecieux")) {
                            itemStack = DiamantPrecieuxItem.createDiamantPrecieux();
                            
                        } else if (itemName.equalsIgnoreCase("Axe")) {
                            itemStack = AxeItem.createAxe();

                        } else if (itemName.equalsIgnoreCase("Megatools")) {
                            itemStack = MegaToolItem.createMega();
                            
                        }

                        if (itemStack != null) {
                            itemStack.setAmount(quantity);
                            target.getInventory().addItem(itemStack);
                            target.sendMessage("§6§lMonaria §f▸ §fVous avez reçu §e " + quantity + " " + itemName + " !");
                            sender.sendMessage("§6§lMonaria §f▸ §fVous avez donné §e " + quantity + " " + itemName + " à " + target.getName() + ".");
                        } else {
                            sender.sendMessage("§cL'item spécifié n'existe pas.");
                        }
                    } else {
                        sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande.");
                    }
                } else {
                    sender.sendMessage("§cLa quantité doit être un nombre positif.");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
                // Affiche la liste des items disponibles
                sender.sendMessage("§aItems disponibles :");
                sender.sendMessage("§a- MegaMoney");
                sender.sendMessage("§a- Hammer");
                sender.sendMessage("§a- Moissoneuse");
                sender.sendMessage("§a- Diamant Précieux");
                sender.sendMessage("§a- Axe");
                sender.sendMessage("§a- Megatools");
            } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("§b/it give §c<player> <item-name> <quantity>");
                sender.sendMessage("§b /it list");

            }
        }
        return true;
    }
}