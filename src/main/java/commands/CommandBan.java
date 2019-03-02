package commands;

import PigeonWorld.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBan implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

           if(cmd.getName().equalsIgnoreCase("ban")) {
                if(args.length >= 2) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null) {
                        StringBuilder str = new StringBuilder();

                        for (int i = 1; i < args.length; i++) {
                            str.append(args[i]).append(" ");
                        }

                        target.kickPlayer(new StringBuilder().append(Main.nameServer + "\n\n            §cVous avez banni par §f" + p.getName() + "§8. \n\n     §cRaison §8: §f" + str.toString() + "").toString());
                        target.setBanned(true);

                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                        p.sendMessage(new StringBuilder().append("§f" + target.getName() + " §aa bien été banni pour §f" + str.toString() + " §8!").toString());
                    }else{
                        p.sendMessage("§f" + args[0] + " §cest introuvable §8!");
                    }
                }else{
                    p.sendMessage("§cCette commande se fait comme ceci §f/ban <nom du joueur> <raison>");
                }
           }else if(cmd.getName().equalsIgnoreCase("unban")) {
                if(args.length == 1) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                    if(target.isBanned()) {
                        target.setBanned(false);

                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                        p.sendMessage("§f" + target.getName() + " §aa bien été débanni §8!");
                    }else{
                        p.sendMessage("§f" + args[0] + " §cn'est pas banni §8!");
                    }
                }
           }

        }

        return false;
    }

}
