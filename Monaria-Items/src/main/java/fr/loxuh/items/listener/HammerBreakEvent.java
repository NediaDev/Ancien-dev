package fr.loxuh.items.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HammerBreakEvent implements Listener {

    private final HashMap<UUID, Integer> blockFaceMap = new HashMap<>();

    private final HammerCheck hammerCheck;

    public HammerBreakEvent(HammerCheck hammerCheck) {
        this.hammerCheck = hammerCheck;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        BlockFace blockFace = event.getBlockFace();


        if (blockFace == BlockFace.NORTH || blockFace == BlockFace.SOUTH) {
            this.blockFaceMap.put(player.getUniqueId(), 1);
        }

        if (blockFace == BlockFace.UP || blockFace == BlockFace.DOWN) {
            this.blockFaceMap.put(player.getUniqueId(), 2);
        }

        if (blockFace == BlockFace.WEST || blockFace == BlockFace.EAST) {
            this.blockFaceMap.put(player.getUniqueId(), 3);
        }
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (!this.hammerCheck.isHammer(player)) return;

        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand != null && itemInHand.hasItemMeta()) {
            ItemMeta itemMeta = itemInHand.getItemMeta();
            if (itemMeta.hasDisplayName() && itemMeta.getDisplayName().equals("§f§l▸ §6§l§nHammer")) {
                for (Block blockBreak : this.getBlocks(player, block)) {
                    blockBreak.breakNaturally();
                }
            }
        }
    }

    private List<Block> getBlocks(Player player, Block mainBlock) {

        ArrayList<Block> blocksList = new ArrayList<Block>();

        if (this.blockFaceMap.get(player.getUniqueId()) == 1) {
            blocksList.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.WEST));
            blocksList.add(mainBlock.getRelative(BlockFace.UP));
            blocksList.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.EAST));
            blocksList.add(mainBlock.getRelative(BlockFace.WEST));
            blocksList.add(mainBlock.getRelative(BlockFace.EAST));
            blocksList.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
            blocksList.add(mainBlock.getRelative(BlockFace.DOWN));
            blocksList.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
        }

        if (this.blockFaceMap.get(player.getUniqueId()) == 2) {
            blocksList.add(mainBlock.getRelative(BlockFace.NORTH_WEST));
            blocksList.add(mainBlock.getRelative(BlockFace.NORTH));
            blocksList.add(mainBlock.getRelative(BlockFace.NORTH_EAST));
            blocksList.add(mainBlock.getRelative(BlockFace.WEST));
            blocksList.add(mainBlock.getRelative(BlockFace.EAST));
            blocksList.add(mainBlock.getRelative(BlockFace.SOUTH_WEST));
            blocksList.add(mainBlock.getRelative(BlockFace.SOUTH));
            blocksList.add(mainBlock.getRelative(BlockFace.SOUTH_EAST));
        }

        if (this.blockFaceMap.get(player.getUniqueId()) == 3) {
            blocksList.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH));
            blocksList.add(mainBlock.getRelative(BlockFace.UP));
            blocksList.add(mainBlock.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH));
            blocksList.add(mainBlock.getRelative(BlockFace.NORTH));
            blocksList.add(mainBlock.getRelative(BlockFace.SOUTH));
            blocksList.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
            blocksList.add(mainBlock.getRelative(BlockFace.DOWN));
            blocksList.add(mainBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
        }
        return blocksList;
    }
}
