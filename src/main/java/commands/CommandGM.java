package commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGM implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("1")) {
                    p.sendMessage("§aMode créatif activé");
                    p.setGameMode(GameMode.CREATIVE);
                }else if(args[0].equalsIgnoreCase("2")) {
                    p.sendMessage("§aMode aventure activé");
                    p.setGameMode(GameMode.ADVENTURE);
                }else if(args[0].equalsIgnoreCase("3")) {
                    p.sendMessage("§aMode spectateur activé");
                    p.setGameMode(GameMode.SPECTATOR);
                }else if(args[0].equalsIgnoreCase("0")) {
                    p.sendMessage("§aMode survie activé");
                    p.setGameMode(GameMode.SURVIVAL);
                }
            }

        }

        return false;
    }

}
