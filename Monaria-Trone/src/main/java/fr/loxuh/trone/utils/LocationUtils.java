package fr.loxuh.trone.utils;

import org.bukkit.*;

public class LocationUtils
{
    public static String locationToString(final Location location) {
        return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
    }

    public static Location locationFromString(final String data) {
        final String[] str = data.split(",");
        return new Location(Bukkit.getWorld(str[0]), (double)Double.valueOf(str[1]), (double)Double.valueOf(str[2]), (double)Double.valueOf(str[3]), (float)Float.valueOf(str[4]), (float)Float.valueOf(str[5]));
    }
}
