/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package fr.loxuh.spectralboutique.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.loxuh.spectralboutique.Main;
import fr.loxuh.spectralboutique.gui.GradesGUI;

public class BoutiqueCMD
implements CommandExecutor {
    Main main;

    public BoutiqueCMD(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rankup") && sender instanceof Player) {
            Player p = (Player)sender;
            new GradesGUI().open(p);
        }
        return false;
    }
}

