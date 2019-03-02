package commands;

import PigeonWorld.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(args.length == 0) {
                p.sendMessage("§f§o/kick <joueur> <raison>");
            }else if(args.length == 1) {
                p.sendMessage("§c§lEntre des arguments §8!");
            }

            Player pp = Bukkit.getServer().getPlayer(args[0]);

            if (args.length >= 2) {
                if (pp != null) {
                    StringBuilder str = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        str.append(args[i]).append(" ");
                    }
                    pp.kickPlayer(new StringBuilder().append(Main.nameServer + "\n\n            §cVous avez été éjecté par §f§o" + p.getName() + "§8. \n\n     §c§lRaison §8: §f§o" + str.toString() + "").toString());
                    p.sendMessage(new StringBuilder().append("§f" + pp.getName() + " §aa bien été kick pour §f" + str.toString() + " §8!").toString());
                }else{
                    p.sendMessage("§f" + args[0] + " §cn'est pas connecté §8!");
                }
            }

        }

        return false;
    }

}
