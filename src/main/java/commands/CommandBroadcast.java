package commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBroadcast implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(args.length == 0) {
                p.sendMessage("§cEntre des arguments §8!");
            }

            if(args.length >= 1) {
                StringBuilder bc = new StringBuilder();
                for(String part : args) {
                    bc.append(part + " ");
                }

                p.playSound(p.getLocation(), Sound.CAT_MEOW, 50F, 50F);
                Bukkit.broadcastMessage("§c§lBroadcast §e§l" + p.getName() + " §8>> §b" + bc.toString());
            }

        }

        return false;
    }

}
