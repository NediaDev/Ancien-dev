package fr.AtlantiCraft.core.command.vision;

import fr.AtlantiCraft.core.utils.command.iCommand;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

public final class CommandVision extends iCommand
{
    @Override
    public boolean onCommand(final CommandSender sender, final String cmd, final String[] args) {
        final Player p = (Player)sender;
        if (args.length != 1) {
            p.sendMessage("§c/vision <on/off>.");
            return false;
        }
        if ("on".equalsIgnoreCase(args[0])) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1));
            p.sendMessage("§eVision nocturne §aactiv\u00e9.");
        }
        else {
            p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            p.sendMessage("§eVision nocturne §cd\u00e9sactiv\u00e9.");
        }
        return true;
    }

    @Override
    public String commandName() {
        return "vision";
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
