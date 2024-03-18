package fr.loxuh.items.event;

import fr.loxuh.items.items.moissoneuse.MoissoneuseItem;
import fr.loxuh.items.items.diamant.DiamantPrecieuxItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class BlockBreakListener implements Listener {
    private Plugin plugin;
    private Random random = new Random();

    public BlockBreakListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (isCrop(event.getBlock().getType())) {
            // Vérifiez si l'outil utilisé est une Moissoneuse
            ItemStack tool = event.getPlayer().getInventory().getItemInHand();
            if (tool.hasItemMeta() && tool.getItemMeta().hasDisplayName()
                    && tool.getItemMeta().getDisplayName().equals(MoissoneuseItem.MOISSONEUSE_DISPLAY_NAME)) {
                // Récupérez le bloc cible
                Block targetBlock = event.getBlock();

                // Cassez les blocs dans un rayon de 1 bloc autour du bloc cible
                int radius = 1;
                for (int x = -radius; x <= radius; x++) {
                    for (int y = -radius; y <= radius; y++) {
                        for (int z = -radius; z <= radius; z++) {
                            BlockFace blockFace = getBlockFace(x, y, z);
                            Block nearbyBlock = targetBlock.getRelative(blockFace);

                            if (isCrop(nearbyBlock.getType())) {
                                nearbyBlock.breakNaturally();
                            }
                        }
                    }
                }

                // Créez un diamant précieux nommé "Diamant Précieux"

                // Donnez le diamant précieux au joueur
                double dropChance = 0.5; // 50% de chance
                if (random.nextDouble() < dropChance) {
                    ItemStack diamond = DiamantPrecieuxItem.createDiamantPrecieux();
                    event.getPlayer().getInventory().addItem(diamond);
                    event.getPlayer().sendMessage("§aVous avez reçu un Diamant Précieux grâce à la Moissoneuse!");

            }
            }
        }
    }

    private boolean isCrop(Material material) {
        switch (material) {
            case CROPS:
            case CARROT:
            case POTATO:
            case NETHER_WARTS:
            case INK_SACK:
                return true;
            default:
                return false;
        }
    }

    private BlockFace getBlockFace(int x, int y, int z) {
        if (x == 0 && y == 0 && z == 0) {
            return BlockFace.SELF;
        }
        if (x == 0 && y == 0 && z == -1) {
            return BlockFace.NORTH;
        }
        if (x == 0 && y == 0 && z == 1) {
            return BlockFace.SOUTH;
        }
        if (x == 0 && y == -1 && z == 0) {
            return BlockFace.DOWN;
        }
        if (x == 0 && y == 1 && z == 0) {
            return BlockFace.UP;
        }
        if (x == -1 && y == 0 && z == 0) {
            return BlockFace.WEST;
        }
        if (x == 1 && y == 0 && z == 0) {
            return BlockFace.EAST;
        }
        return BlockFace.SELF;
    }
}