package fr.loxuh.items.manager;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

public class EconomyManager {

    private static Economy economy;

    public static void setEconomy(Economy econ) {
        economy = econ;
    }

    public static void deposit(Player player, double amount) {
        if (economy != null) {
            economy.depositPlayer(player, amount);
        }
    }
}
