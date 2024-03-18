package fr.AtlantiCraft.core.command.bottlexp;

import fr.AtlantiCraft.core.utils.ItemBuilder;
import fr.AtlantiCraft.core.utils.command.iCommand;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.*;

public class CommandBottlexp extends iCommand
{
    @Override
    public boolean onCommand(final CommandSender sender, final String cmd, final String[] args) {
        if (args.length != 1) {
            sender.sendMessage("§c/bottlexp <niveau>!");
            return false;
        }
        final Player p = (Player)sender;
        if (p.getLevel() < 10) {
            sender.sendMessage("§eVous devez avoir au minimum §d10 niveaux.");
            return false;
        }
        if (Integer.valueOf(args[0]) > 100) {
            p.sendMessage("§eVous avez atteinds la limite de §eniveaux");
            return false;
        }
        if (Integer.valueOf(args[0]) > p.getLevel()) {
            p.sendMessage("§7Vous n'avez pas assez de §dniveaux.");
            return false;
        }
        if (Integer.valueOf(args[0]) < 0) {
            p.sendMessage("§7Veuillez indiquer un nombre d'xp §dconvenable.");
            return false;
        }
        p.setLevel(p.getLevel() - Integer.valueOf(args[0]));
        p.getInventory().addItem(new ItemStack[] { new ItemBuilder(Material.EXP_BOTTLE).setName("§d" + Integer.valueOf(args[0]) + " niveaux").toItemStack() });
        p.sendMessage("§eVous avez mis §d" + Integer.valueOf(args[0]) + "niveaux §een bouteille.");
        return false;
    }

    @Override
    public String commandName() {
        return "bottlexp";
    }

    @Override
    public String permission() {
        return "";
    }

    @Override
    public boolean playerOnly() {
        return true;
    }
}
