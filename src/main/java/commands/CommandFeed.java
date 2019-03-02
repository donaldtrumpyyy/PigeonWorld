package commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFeed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            p.setFoodLevel(20);
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
            p.sendMessage("§aEt voilà §8! §aTu peux maintenant continuer ton jeu §8!");

        }

        return false;
    }

}
