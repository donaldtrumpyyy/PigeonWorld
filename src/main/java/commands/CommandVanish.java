package commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandVanish implements CommandExecutor {

    private ArrayList<Player> vanishPlayers = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(!(vanishPlayers.contains(p))) {
                for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.hidePlayer(p);
                }
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                p.sendMessage("§aTu es maintenant invisible §8!");
                vanishPlayers.add(p);
            }else{
                for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.showPlayer(p);
                }
                p.sendMessage("§cTu n'est plus invisible §8!");
                vanishPlayers.remove(p);
            }

        }

        return false;
    }

}
