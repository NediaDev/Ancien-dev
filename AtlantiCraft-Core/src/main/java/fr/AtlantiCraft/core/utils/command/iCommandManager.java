package fr.AtlantiCraft.core.utils.command;


import fr.AtlantiCraft.core.Main;
import fr.AtlantiCraft.core.command.bottlexp.CommandBottlexp;
import fr.AtlantiCraft.core.command.boutique.CommandBoutique;
import fr.AtlantiCraft.core.command.furnace.CommandFurnace;
import fr.AtlantiCraft.core.command.help.CommandHelp;
import fr.AtlantiCraft.core.command.rtp.CommandRandomtp;
import fr.AtlantiCraft.core.command.vision.CommandVision;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.*;

public class iCommandManager implements CommandExecutor
{
    private final ArrayList<iCommand> shyzaCommands;
    private final Main main;

    public iCommandManager(final Main main) {
        this.shyzaCommands = new ArrayList<iCommand>();
        this.main = main;
        this.addCommand(new CommandPoubelle());
        this.addCommand(new CommandRandomtp());
        this.addCommand(new CommandVision());
        this.addCommand(new CommandFurnace(main));
        this.addCommand(new CommandHelp());
        this.addCommand(new CommandBottlexp());
        this.addCommand(new CommandBoutique(main));
    }

    private void addCommand(final iCommand cmd) {
        this.shyzaCommands.add(cmd);
        this.main.getCommand(cmd.commandName()).setExecutor((CommandExecutor)this);
    }

    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        for (final iCommand cmd : this.shyzaCommands) {
            if (cmd.commandName().equalsIgnoreCase(command.getName().toLowerCase())) {
                if (!(commandSender instanceof Player) && cmd.playerOnly()) {
                    commandSender.sendMessage("§cVous devez \u00eatre un joueur pour effectuer cette commande!");
                    return true;
                }
                if (commandSender.hasPermission(cmd.permission()) || cmd.permission().equals("")) {
                    cmd.onCommand(commandSender, command.getName(), strings);
                    return true;
                }
                commandSender.sendMessage("§cVous n'avez pas la permission d'executer cette commande.");
                return false;
            }
        }
        return false;
    }
}
