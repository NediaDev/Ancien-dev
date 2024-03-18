package fr.AtlantiCraft.core.command.help;

import fr.AtlantiCraft.core.utils.command.iCommand;

import org.bukkit.command.*;

public class CommandHelp extends iCommand
{
    @Override
    public boolean onCommand(final CommandSender sender, final String cmd, final String[] args) {
        if (args.length == 0 || "1".equalsIgnoreCase(args[0])) {
            sender.sendMessage(" ");
            sender.sendMessage("§d/menu §8» §eVous affiche le menu principal.");
            sender.sendMessage("§d/spawn §8» §eVous t\u00e9leporte au spawn.");
            sender.sendMessage("§d/randomtp §8» §eVous t\u00e9leporte al\u00e9atoirement dans la map.");
            sender.sendMessage("§d/f help §8» §eVous affiche les commandes faction.");
            sender.sendMessage("§d/poubelle §8» §eVous permet de vider votre inventaire.");
            sender.sendMessage("§d/vision §7<on/off> §8» §eVous permet d'activer la vision nocturne.");
            sender.sendMessage("§d/boutique §8» §eVous affiche la boutique du serveur");
            sender.sendMessage("§d/info §8» §eVous affiche des informations sur le serveur ");
            sender.sendMessage("§eFaites §c/help 2.");
            sender.sendMessage(" ");
        }
        else if ("2".equalsIgnoreCase(args[0])) {}
        return true;
    }

    @Override
    public String commandName() {
        return "help";
    }

    @Override
    public String permission() {
        return "";
    }

    @Override
    public boolean playerOnly() {
        return false;
    }
}
