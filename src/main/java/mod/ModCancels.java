package mod;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ModCancels implements Listener {

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()));
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e) {
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()));
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()));
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent e) {
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()));
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if(PlayerManager.isInMod((Player) e.getWhoClicked())) {
            e.setCancelled(true);
        }else{
            return;
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player)) return;
        e.setCancelled(PlayerManager.isInMod((Player) e.getEntity()));
    }


}
