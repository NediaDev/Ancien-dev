package fr.loxuh.items.listener;

import fr.loxuh.items.manager.TransformUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ToolListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null && TransformUtil.isDiamondTool(item)) {
            Material clickedMaterial = event.getClickedBlock().getType();
            Material transformedMaterial = TransformUtil.getTransformedMaterial(clickedMaterial);

            if (transformedMaterial != null) {
                item.setType(transformedMaterial);
            }
        }
    }
}