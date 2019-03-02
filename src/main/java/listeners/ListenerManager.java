package listeners;

import PigeonWorld.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if(!p.hasPlayedBefore()) {

            for(Player players : Bukkit.getOnlinePlayers()) {
                Main.setActionBar(p, "§f" + p.getName() + " §6est nouveau sur le serveur §8!");
            }

            p.sendMessage("§eBonjour à toi §f" + p.getName() + " §8!");
            p.sendMessage("§ePour savoir tes commandes, fait §f/help §eou alors §f/aide §8!");

        }

    }

}
