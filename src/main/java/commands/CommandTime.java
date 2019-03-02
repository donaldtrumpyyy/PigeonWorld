package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTime implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(cmd.getName().equalsIgnoreCase("day")) {
                p.getWorld().setTime(100);
                p.sendMessage("§eLe soleil se lève §8!");
            }else if(cmd.getName().equalsIgnoreCase("night")) {
                p.getWorld().setTime(-10000);
                p.sendMessage("§eLa nuit se lève §8!");
            }

        }

        return false;
    }

}
