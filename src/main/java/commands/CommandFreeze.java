package commands;

import PigeonWorld.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandFreeze implements CommandExecutor {

    public static ArrayList<Player> freezePlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(cmd.getName().equalsIgnoreCase("freeze")) {
                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null && !freezePlayers.contains(target)) {
                        p.sendMessage("§f" + target.getName() + " §aa bien été freeze §8!");

                        target.sendTitle(Main.nameServer, "§7Tu es freeze §8!");
                        freezePlayers.add(target);
                    }else if(freezePlayers.contains(target)) {
                        p.sendMessage("§f" + args[0] + " §cest déjà freeze §8!");
                    }else{
                        p.sendMessage("§f" + args[0] + " §cn'est pas connecté §8!");
                    }
                }
            }else if(cmd.getName().equalsIgnoreCase("unfreeze")) {
                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null && freezePlayers.contains(target)) {
                        p.sendMessage("§f" + target.getName() + " §aa bien été unfreeze §8!");

                        target.sendTitle(Main.nameServer, "§7Tu es unfreeze §8!");
                        freezePlayers.remove(target);
                    }else if(!freezePlayers.contains(target)) {
                        p.sendMessage("§f" + args[0] + " §cn'est pas freeze §8!");
                    }else{
                        p.sendMessage("§f" + args[0] + " §cn'est pas connecté §8!");
                    }
                }
            }

        }

        return false;

    }

}
