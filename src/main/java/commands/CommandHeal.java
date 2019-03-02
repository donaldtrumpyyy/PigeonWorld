package commands;

import PigeonWorld.Particle;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHeal implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            Particle particle = new Particle(EnumParticle.HEART, p.getLocation().add(0,2.25,0), true, 0.75f, 0.75f, 0.75f, 0, 10);
            particle.sendAll();

            p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
            p.sendMessage("§aEt voilà §8! §aTu as maintenant tout tes coeurs §8!");
            p.setHealth(20);

        }

        return false;
    }

}
