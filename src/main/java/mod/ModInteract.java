package mod;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class ModInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();

        if(!PlayerManager.isInMod(p)) return;
        if(!(e.getRightClicked() instanceof Player)) return;

        Player target = Bukkit.getPlayer(e.getRightClicked().getName());

        switch (p.getInventory().getItemInHand().getType()) {
            case PACKED_ICE:
                Bukkit.dispatchCommand(p, "freeze " + target.getName());
            break;

            case DARK_OAK_DOOR_ITEM:
                Bukkit.dispatchCommand(p, "kick " + target.getName() + " Contacte le staff sur le discord du serveur");
            break;

            case CHEST:
                Bukkit.dispatchCommand(p, "invsee " + target.getName() + " inv");
            break;
        }

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack it = e.getItem();

        if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if(it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§2Vanish")) {
                Bukkit.dispatchCommand(p, "vanish");
            }else if(it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§eJoueur aléatoire")) {
                int random = new Random().nextInt(getServer().getOnlinePlayers().size());
                Player player = (Player) getServer().getOnlinePlayers().toArray()[random];

                p.sendMessage("§eTu as été téléporté au joueur §b" + player.getName() + " §8!");
                p.teleport(player);
            }else if(it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§4§lQuitter")) {
                Bukkit.dispatchCommand(p, "mod");
            }
        }
    }

}
