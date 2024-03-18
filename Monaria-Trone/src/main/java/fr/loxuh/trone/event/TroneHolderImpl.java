package fr.loxuh.trone.event;

import fr.loxuh.trone.Main;
import fr.loxuh.trone.utils.DateUtils;
import me.clip.placeholderapi.expansion.*;
import org.bukkit.entity.*;
;

public class TroneHolderImpl extends PlaceholderExpansion
{
    public String onPlaceholderRequest(final Player player, final String params) {
        final Trone trone = Main.getInstance().getEventTrone().getTrone();
        if (params.equals("time")) {
            return DateUtils.getDate(trone.getTroneTask().getSeconds() * 1000);
        }
        if (params.equals("player")) {
            return (trone.getPlayer() != null) ? trone.getPlayer().getName() : "personne";
        }
        return null;
    }

    public String getIdentifier() {
        return "trone";
    }

    public String getAuthor() {
        return "UnRandom";
    }

    public String getVersion() {
        return "1.0.0";
    }
}

