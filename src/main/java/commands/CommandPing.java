package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CommandPing implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(((CraftPlayer) p).getHandle().ping < 100) {
                p.sendMessage("§eTon ping est de §a" + ((CraftPlayer) p).getHandle().ping);
            }else if(((CraftPlayer) p).getHandle().ping > 100) {
                p.sendMessage("§eTon ping est de §c" + ((CraftPlayer) p).getHandle().ping);
            }else if(((CraftPlayer) p).getHandle().ping > 500) {
                p.sendMessage("§eTon ping est de §4" + ((CraftPlayer) p).getHandle().ping);
            }

        }

        return false;
    }

}
