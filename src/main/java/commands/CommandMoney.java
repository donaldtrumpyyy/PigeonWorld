package commands;

import PigeonWorld.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class CommandMoney implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player) sender;

            double money = Main.getInstance().getConfig().getDouble("money." + p.getUniqueId() + ".money");

            if (cmd.getName().equalsIgnoreCase("money")) {

                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("send")) {
                        Player target = Bukkit.getPlayer(args[1]);

                        if (target != null) {
                            if(money >= Double.parseDouble(args[2])) {
                                Main.getInstance().getConfig().set("money." + target.getUniqueId() + ".money", Main.getInstance().getConfig().getDouble("money." + target.getUniqueId() + ".money") + Double.parseDouble(args[2]));
                                Main.getInstance().getConfig().set("money." + p.getUniqueId() + ".money", Main.getInstance().getConfig().getDouble("money." + p.getUniqueId() + ".money") - Double.parseDouble(args[2]));
                                Main.getInstance().saveConfig();

                                double moneyTarget = Main.getInstance().getConfig().getDouble("money." + target.getUniqueId() + ".money");

                                target.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                                target.sendMessage("§aLa transaction s'est bien efféctuée §8!");
                                target.sendMessage("§aTu as maintenant §f" + moneyTarget + Main.money + " §8!");

                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                                p.sendMessage("§aLa transaction s'est bien efféctuée §8!");
                                p.sendMessage("§aTu as maintenant §f" + money + Main.money + " §8!");
                            }else{
                                p.playSound(p.getLocation(), Sound.WOLF_GROWL, 50F, 50F);
                                p.sendMessage("§cTu n'as pas assez d'argent pour envoyer §f" + args[2] + Main.money + " §cà §f" + target.getName() + " §8!");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("give")) {
                        if (p.hasPermission("givemoney.admin")) {
                            Player target = Bukkit.getPlayer(args[1]);

                            if (target != null) {
                                Main.getInstance().getConfig().set("money." + target.getUniqueId() + ".money", Main.getInstance().getConfig().getDouble("money." + target.getUniqueId() + ".money") + Double.parseDouble(args[2]));
                                Main.getInstance().saveConfig();

                                double moneyTarget = Main.getInstance().getConfig().getDouble("money." + target.getUniqueId() + ".money");

                                target.playSound(p.getLocation(), Sound.NOTE_BASS, 50F, 50F);
                                target.sendMessage("§eTu as reçu §b" + args[2] + Main.money + " §ede §b" + p.getName() + " §8!");
                                target.sendMessage("§eTu as maintenant §b" + moneyTarget + Main.money + " §8!");

                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                                p.sendMessage("§aLa transaction s'est bien efféctuée §8!");
                                p.sendMessage("§f" + target.getName() + " §aa maintenant §f" + moneyTarget + Main.money + " §8!");
                            }
                        }else{
                            p.playSound(p.getLocation(), Sound.WOLF_GROWL, 50F, 50F);
                            p.sendMessage("§cTu n'as pas la permission pour faire cela §8!");
                        }
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (p.hasPermission("removemoney.admin")) {
                            Player target = Bukkit.getPlayer(args[1]);

                            if (target != null) {
                                Main.getInstance().getConfig().set("money." + target.getUniqueId() + ".money", Main.getInstance().getConfig().getDouble("money." + target.getUniqueId() + ".money") - Double.parseDouble(args[2]));
                                Main.getInstance().saveConfig();

                                double moneyTarget = Main.getInstance().getConfig().getDouble("money." + target.getUniqueId() + ".money");

                                target.playSound(p.getLocation(), Sound.NOTE_BASS, 50F, 50F);
                                target.sendMessage("§b" + p.getName() + " §et'as retiré §b" + args[2] + Main.money + " §8!");
                                target.sendMessage("§eTu as maintenant §b" + moneyTarget + Main.money + " §8!");

                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 50F, 50F);
                                p.sendMessage("§aLa transaction s'est bien efféctuée §8!");
                                p.sendMessage("§f" + target.getName() + " §aa maintenant §f" + moneyTarget + Main.money + " §8!");
                            }
                        }else{
                            p.playSound(p.getLocation(), Sound.WOLF_GROWL, 50F, 50F);
                            p.sendMessage("§cTu n'as pas la permission pour faire cela §8!");
                        }
                    }
                } else {

                    if(p.hasPermission("moneyhelp.admin")) {
                        Inventory inv = Bukkit.createInventory(p, 27, "§6§lMoney");

                        setItem(inv, "§f/money", new String[]{"§7Sert à voir son argent en ce moment§8."}, 2);
                        setItem(inv, "§f/money send", new String[]{"§7Sert à envoyer de l'argent au joueur de ton choix§8."}, 4);
                        setItem(inv, "§f/money remove", new String[]{"§7Sert à voir enlever de l'argent au joueur de ton choix§8."}, 6);

                        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                        SkullMeta itemM = (SkullMeta) item.getItemMeta();

                        itemM.setOwner(p.getName());
                        itemM.setDisplayName("§e" + p.getName());
                        itemM.setLore(Arrays.asList("§7Tu as actuellement §f" + money + Main.money + " §8!"));

                        item.setItemMeta(itemM);

                        inv.setItem(13, item);

                        setItem(inv, "§f/money give", new String[]{"§7Sert à voir donner de l'argent au joueur de ton choix§8."}, 22);

                        p.openInventory(inv);
                    }else{
                        Inventory inv = Bukkit.createInventory(p, 9, "§6§lMoney");

                        setItem(inv, "§f/money", new String[]{"§7Sert à voir son argent en ce moment§8."}, 2);

                        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                        SkullMeta itemM = (SkullMeta) item.getItemMeta();

                        itemM.setOwner(p.getName());
                        itemM.setDisplayName("§e" + p.getName());
                        itemM.setLore(Arrays.asList("§7Tu as actuellement §f" + money + Main.money + " §8!"));

                        item.setItemMeta(itemM);

                        inv.setItem(4, item);

                        setItem(inv, "§f/money send", new String[]{"§7Sert à envoyer de l'argent au joueur de ton choix§8."}, 6);

                        p.openInventory(inv);
                    }

                    p.sendMessage("§aTu as actuellement §f" + money + Main.money + " §8!");
                }

            }
        }

        return false;
    }

    private void setItem(Inventory inv, String name, String[] lore, int place) {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemM = item.getItemMeta();

        itemM.setDisplayName(name);
        itemM.setLore(Arrays.asList(lore));

        item.setItemMeta(itemM);

        inv.setItem(place, item);
    }

}
