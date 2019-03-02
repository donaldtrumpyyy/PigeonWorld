package commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(p.getAllowFlight() == false) {
                p.setAllowFlight(true);
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                p.sendMessage("§aTu peux maintenant voler §8!");
            }else{
                p.setAllowFlight(false);
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                p.sendMessage("§cTu peux plus voler §8!");
            }

        }

        return false;

    }

}
