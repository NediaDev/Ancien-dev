package fr.AtlantiCraft.core.command.boutique;

import fr.AtlantiCraft.core.Main;
import fr.AtlantiCraft.core.utils.command.iCommand;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class CommandBoutique extends iCommand
{
    private final Main main;

    public CommandBoutique(final Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final String cmd, final String[] args) {
        ((Player)sender).openInventory(this.main.guiManager.boutiqueGui.getInventory());
        return true;
    }

    @Override
    public String commandName() {
        return "boutique";
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

