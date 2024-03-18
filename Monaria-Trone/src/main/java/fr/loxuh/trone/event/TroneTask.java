package fr.loxuh.trone.event;

import fr.loxuh.trone.Main;
import fr.loxuh.trone.utils.LangUtil;
import org.bukkit.scheduler.*;

import java.util.*;
import org.bukkit.entity.*;
import java.util.stream.*;
import org.bukkit.*;

public class TroneTask extends BukkitRunnable
{
    private final EventTrone eventTrone;
    private int seconds;
    private final Random random;

    public TroneTask(final EventTrone eventTrone) {
        this.random = new Random();
        this.eventTrone = eventTrone;
    }

    public void run() {
        final Trone trone = this.eventTrone.getTrone();
        Player player = trone.getPlayer();
        final List<LivingEntity> players = this.getPlayers();
        if (player != null && player.isOnline()) {
            if (player.isOnline() && !player.isDead() && trone.getCuboid().contains(player.getLocation())) {
                if (players.size() > 1) {
                    players.forEach(livingEntity -> livingEntity.sendMessage(Main.getMessage("SOLO")));
                    trone.setBlocks(Material.COAL_BLOCK);
                    return;
                }
                ++this.seconds;
                trone.resetBlocks(Material.GOLD_BLOCK);
                return;
            }
            else {
                this.giveMoney(player);
                trone.setBlocks(Material.IRON_BLOCK);
                player = null;
                this.seconds = 0;
            }
        }
        else {
            player = this.getNewPlayer(players);
            if (player == null) {
                return;
            }
            trone.setBlocks(Material.GOLD_BLOCK);
            Bukkit.broadcastMessage(LangUtil.get(Main.getMessage("CAPTURE"), player.getName()));
            player.sendMessage(LangUtil.get(Main.getMessage("PRIVATE_CAPTURE"), String.valueOf(this.eventTrone.getTrone().getMoney())));
        }
        trone.setPlayer(player);
    }

    private Player getNewPlayer(final List<LivingEntity> players) {
        return (players.size() == 0) ? null : ((Player)players.get(this.random.nextInt(players.size())));
    }

    private List<LivingEntity> getPlayers() {
        return this.eventTrone.getTrone().getCuboid().getLivingEntityInside().stream().filter(livingEntity -> livingEntity instanceof Player && ((Player) livingEntity).isOnline()).collect(Collectors.toList());
    }

    private void giveMoney(final Player player) {
        final int money = this.eventTrone.getTrone().getMoney() * this.seconds;
        player.sendMessage(LangUtil.get(Main.getMessage("LEAVE"), String.valueOf(money)));
        Main.getInstance().getEconomy().depositPlayer((OfflinePlayer)player, (double)money);
    }

    public int getSeconds() {
        return this.seconds;
    }
}
