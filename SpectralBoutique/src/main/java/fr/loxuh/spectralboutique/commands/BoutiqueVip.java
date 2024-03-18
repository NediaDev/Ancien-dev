package fr.loxuh.spectralboutique.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loxuh.spectralboutique.Main;
import fr.loxuh.spectralboutique.gui.GradesGUI;
import fr.loxuh.spectralboutique.gui.RankUpMenu;

public class BoutiqueVip
implements CommandExecutor {
    Main main;

    public BoutiqueVip(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rankupvip") && sender instanceof Player) {
            Player p = (Player)sender;
            new RankUpMenu().open(p);
        }
        return false;
    }
}

