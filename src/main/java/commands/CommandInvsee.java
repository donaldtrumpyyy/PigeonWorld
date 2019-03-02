package commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class CommandInvsee implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);

                if(args[1].equalsIgnoreCase("inv")) {
                    if(target != null) {
                        p.openInventory(target.getInventory());
                    }else{
                        p.sendMessage("§f" + target + " §cn'est pas connecté §8!");
                    }
                }else if(args[1].equalsIgnoreCase("ec")) {
                    if(target != null) {
                        p.openInventory(target.getEnderChest());
                    }else{
                        p.sendMessage("§f" + target + " §cn'est pas connecté §8!");
                    }
                }
            }else if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    Inventory inv = Bukkit.createInventory(p, 9, "§f§lInvsee");

                    ItemStack other = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
                    ItemMeta otherM = other.getItemMeta();

                    other.setItemMeta(otherM);

                    ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta itemM = (SkullMeta) item.getItemMeta();

                    itemM.setOwner(target.getName());
                    itemM.setDisplayName("§fRegarder son inventaire");
                    itemM.setLore(Arrays.asList("§7Pour le voir, tu dois faire §f/invsee " + target.getName() + " inv"));

                    item.setItemMeta(itemM);

                    ItemStack item1 = new ItemStack(Material.ENDER_CHEST);
                    ItemMeta item1M = item1.getItemMeta();

                    item1M.setDisplayName("§fRegarder son EnderChest");
                    item1M.setLore(Arrays.asList("§7Pour le voir, tu dois faire §f/invsee " + target.getName() + " ec"));

                    item1.setItemMeta(item1M);

                    inv.setItem(0, other);
                    inv.setItem(1, other);
                    inv.setItem(2, item);
                    inv.setItem(3, other);
                    inv.setItem(4, other);
                    inv.setItem(5, other);
                    inv.setItem(6, item1);
                    inv.setItem(7, other);
                    inv.setItem(8, other);

                    p.openInventory(inv);
                }
            }

        }

        return false;
    }

}
