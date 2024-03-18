package fr.AtlantiCraft.core.utils.command;

import org.bukkit.command.*;

public abstract class iCommand
{
    public abstract boolean onCommand(final CommandSender p0, final String p1, final String[] p2);

    public abstract String commandName();

    public abstract String permission();

    public abstract boolean playerOnly();
}
