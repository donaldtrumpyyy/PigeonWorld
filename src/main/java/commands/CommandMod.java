package commands;

import PigeonWorld.Main;
import mod.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CommandMod implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(PlayerManager.isInMod(p)) {
                PlayerManager pm = PlayerManager.getFromPlayer(p);

                Main.getInstance().moderateurs.remove(p.getUniqueId());
                p.getInventory().clear();
                p.sendMessage("§cMode modération désactivé §8!");
                pm.giveInventory();
                pm.destroy();

                Bukkit.dispatchCommand(p, "vanish");
                p.setAllowFlight(false);
                p.setFlying(false);

                return false;
            }

            PlayerManager pm = new PlayerManager(p);
            pm.init();

            Main.getInstance().moderateurs.add(p.getUniqueId());
            p.sendMessage("§aMode modération activé §8!");
            pm.saveInventory();
            Bukkit.dispatchCommand(p, "vanish");
            p.setAllowFlight(true);
            p.setFlying(true);


            ItemStack item = new ItemStack(Material.PACKED_ICE);
            ItemMeta itemM = item.getItemMeta();

            itemM.setDisplayName("§bFreeze");
            itemM.setLore(Arrays.asList("§7Clique droit sur un joueur pour le freeze §8!"));

            item.setItemMeta(itemM);

            ItemStack item1 = new ItemStack(Material.DARK_OAK_DOOR_ITEM);
            ItemMeta item1M = item1.getItemMeta();

            item1M.setDisplayName("§cKick");
            item1M.setLore(Arrays.asList("§7Clique droit sur un joueur pour kick §8!"));

            item1.setItemMeta(item1M);

            ItemStack item2 = new ItemStack(Material.BLAZE_POWDER);
            ItemMeta item2M = item2.getItemMeta();

            item2M.setDisplayName("§2Vanish");
            item2M.setLore(Arrays.asList("§7Clique droit quelque part pour enlever ton vanish ou le remettre §8!"));

            item2.setItemMeta(item2M);

            ItemStack item3 = new ItemStack(Material.BLAZE_ROD);
            ItemMeta item3M = item3.getItemMeta();

            item3M.setDisplayName("§6KB");
            item3M.setLore(Arrays.asList("§7Frappe un joueur pour savoir s'il cheat ou non §8!"));
            item3M.addEnchant(Enchantment.KNOCKBACK, 500, true);
            item3M.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            item3.setItemMeta(item3M);

            ItemStack item4 = new ItemStack(Material.CHEST);
            ItemMeta item4M = item4.getItemMeta();

            item4M.setDisplayName("§fInvsee");
            item4M.setLore(Arrays.asList("§7Clique droit pour voir l'inventaire du joueur §8!"));

            item4.setItemMeta(item4M);

            ItemStack item5 = new ItemStack(Material.SKULL_ITEM);
            ItemMeta item5M = item5.getItemMeta();

            item5M.setDisplayName("§eJoueur aléatoire");
            item5M.setLore(Arrays.asList("§7Clique droit pour se téléporter au joueur aléatoire §8!"));

            item5.setItemMeta(item5M);

            ItemStack item6 = new ItemStack(Material.BARRIER);
            ItemMeta item6M = item6.getItemMeta();

            item6M.setDisplayName("§4§lQuitter");
            item6M.setLore(Arrays.asList("§7Clique droit pour quitter le Mod §8!"));

            item6.setItemMeta(item6M);

            p.getInventory().setItem(0, item5);
            p.getInventory().setItem(1, item3);
            p.getInventory().setItem(2, item1);
            p.getInventory().setItem(4, item);
            p.getInventory().setItem(6, item4);
            p.getInventory().setItem(7, item2);
            p.getInventory().setItem(8, item6);

        }

        return false;

    }

}
