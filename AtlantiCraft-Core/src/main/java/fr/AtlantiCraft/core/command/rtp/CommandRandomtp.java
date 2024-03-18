package fr.AtlantiCraft.core.command.rtp;

import fr.AtlantiCraft.core.utils.command.iCommand;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import java.util.*;
import org.bukkit.*;

public final class CommandRandomtp extends iCommand
{
    private static final HashMap<Player, Long> cooldownRandomtp;

    @Override
    public boolean onCommand(final CommandSender sender, final String cmd, final String[] args) {
        final Player p = (Player)sender;
        if (CommandRandomtp.cooldownRandomtp.containsKey(p)) {
            final long time = CommandRandomtp.cooldownRandomtp.get(p) / 1000L + 10800L - System.currentTimeMillis() / 1000L;
            if (time > 0L) {
                p.sendMessage("§eVous devez attentre §6" + time + "s §eavant de faire un §crandomtp.");
                return false;
            }
        }
        CommandRandomtp.cooldownRandomtp.put(p, System.currentTimeMillis());
        final Random random = new Random();
        final int x = random.nextInt(6000) - 3000;
        final int z = random.nextInt(6000) - 3000;
        final int y = p.getWorld().getHighestBlockYAt(x, z) + 5;
        p.getWorld().getBlockAt(x, y, z).getChunk().load();
        p.teleport(new Location(p.getWorld(), (double)x, (double)y, (double)z));
        p.sendMessage("§eT\u00e9leportation §cal\u00e9atoire §er\u00e9ussie! X:§6" + x + " §eY:§6" + y + " §eZ:§6" + z);
        return true;
    }

    @Override
    public String commandName() {
        return "randomtp";
    }

    @Override
    public String permission() {
        return "";
    }

    @Override
    public boolean playerOnly() {
        return true;
    }

    static {
        cooldownRandomtp = new HashMap<Player, Long>();
    }
}
