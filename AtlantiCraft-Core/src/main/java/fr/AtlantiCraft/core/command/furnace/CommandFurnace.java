package fr.AtlantiCraft.core.command.furnace;

import fr.AtlantiCraft.core.Main;
import fr.AtlantiCraft.core.utils.command.iCommand;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.inventory.*;
import org.bukkit.*;

public class CommandFurnace extends iCommand
{
    private final Main main;
    private static final HashMap<Player, Long> cooldownFurnace;

    public CommandFurnace(final Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final String cmd, final String[] args) {
        final Player p = (Player)sender;
        if (p.getItemInHand() == null) {
            return false;
        }
        if (!this.main.furnace.containsKey(p.getItemInHand().getType())) {
            p.sendMessage("§eCette item ne peut pas \u00eatre §dcuis.");
            return false;
        }
        if (CommandFurnace.cooldownFurnace.containsKey(p)) {
            final long time = CommandFurnace.cooldownFurnace.get(p) / 1000L + 45L - System.currentTimeMillis() / 1000L;
            if (time > 0L) {
                p.sendMessage("§eVous devez attentre §d" + time + "s §eavant de faire cuire un item.");
                return false;
            }
        }
        CommandFurnace.cooldownFurnace.put(p, System.currentTimeMillis());
        p.setItemInHand(new ItemStack((Material)this.main.furnace.get(p.getItemInHand().getType()), p.getItemInHand().getAmount()));
        p.playSound(p.getLocation(), Sound.FUSE, 3.0f, 3.0f);
        return true;
    }

    @Override
    public String commandName() {
        return "furnace";
    }

    @Override
    public String permission() {
        return "furnace.perm";
    }

    @Override
    public boolean playerOnly() {
        return true;
    }

    static {
        cooldownFurnace = new HashMap<Player, Long>();
    }
}

