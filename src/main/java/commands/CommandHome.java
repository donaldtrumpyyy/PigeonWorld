package commands;

import PigeonWorld.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Timer;
import java.util.TimerTask;

public class CommandHome implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if (cmd.getName().equalsIgnoreCase("sethome")) {
                if (args.length == 0 || args.length >= 2) {
                    p.sendMessage("§cLa commande se fait comme ceci §f/sethome <nom du home>");
                }

                if (args.length == 1) {
                    Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".world", p.getWorld().getName());
                    Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".x", p.getLocation().getX());
                    Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".y", p.getLocation().getY());
                    Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".z", p.getLocation().getZ());
                    Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".pitch", p.getEyeLocation().getPitch());
                    Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".yaw", p.getEyeLocation().getYaw());
                    Main.getInstance().saveConfig();

                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                    p.sendMessage("§aTon home §f" + args[0] + " §aa bien été enregistré §8!");
                }
            } else if (cmd.getName().equalsIgnoreCase("home")) {
                if (args.length == 0 || args.length >= 2) {
                    p.sendMessage("§cLa commande se fait comme ceci §f/home <nom du home>");
                }

                if (args.length == 1) {
                    if (Main.getInstance().getConfig().contains("home." + p.getUniqueId().toString() + "." + args[0])) {
                        World world = Bukkit.getWorld(Main.getInstance().getConfig().getString("home." + p.getUniqueId().toString() + "." + args[0] + ".world"));

                        double x = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".x");
                        double y = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".y");
                        double z = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".z");
                        double yaw = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".yaw");
                        double pitch = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".pitch");

                        if (p.hasPermission("teleport.player")) {
                            p.sendMessage("§aEt voilà §8! §aBon jeu §8!");
                            p.teleport(new Location(world, x, y, z, (float) yaw, (float) pitch));
                        } else {
                            Timer t = new Timer();

                            t.schedule(new TimerTask() {
                                int i = 6;

                                @Override
                                public void run() {
                                    i--;

                                    p.sendMessage("§6Téléportation dans §b5 secondes");

                                    if (i == 3) {
                                        p.sendMessage("§6Téléportation dans §b" + i + " secondes");
                                    } else if (i == 2) {
                                        p.sendMessage("§6Téléportation dans §b" + i + " secondes");
                                    } else if (i == 1) {
                                        p.sendMessage("§6Téléportation dans §b" + i + " secondes");
                                    } else if (i == 0) {
                                        p.sendMessage("§aEt voilà §8! §aBon jeu §8!");
                                        p.teleport(new Location(world, x, y, z, (float) yaw, (float) pitch));
                                    }
                                }
                            }, 0, 1000);
                        }
                    } else {
                        p.sendMessage("§cTon home §f" + args[0] + " §cn'extiste pas §8!");
                    }
                }
            } else if (cmd.getName().equalsIgnoreCase("delhome")) {
                if (args.length == 0 || args.length >= 2) {
                    p.sendMessage("§cLa commande se fait comme ceci §f/delhome <nom du home>");
                }

                if (args.length == 1) {
                    if (Main.getInstance().getConfig().contains("home." + p.getUniqueId().toString() + "." + args[0])) {
                        Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0], null);
                        Main.getInstance().saveConfig();

                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                        p.sendMessage("§aTon home §f" + args[0] + " §aa été supprimé avec succès §8!");
                    } else {
                        p.sendMessage("§cTon home §f" + args[0] + " §cn'extiste pas §8!");
                    }
                }
            }

        }

        return false;
    }

}
