package PigeonWorld;

import commands.*;
import listeners.ListenerManager;
import mod.ModCancels;
import mod.ModInteract;
import mod.PlayerManager;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static String nameServer = "§a§lPigeonWorld ";
    public static String money = " $";

    public static Main instance;

    public ArrayList<UUID> moderateurs = new ArrayList<>();
    public HashMap<UUID, PlayerManager> players = new HashMap<>();

    @Override
    public void onEnable() {

        instance = this;

        getCommand("test").setExecutor(new CommandTest());
        getCommand("licence").setExecutor(new CommandLicence());
        getCommand("heal").setExecutor(new CommandHeal());
        getCommand("feed").setExecutor(new CommandFeed());
        getCommand("gamemode").setExecutor(new CommandGM());
        getCommand("broadcast").setExecutor(new CommandBroadcast());
        getCommand("ban").setExecutor(new CommandBan());
        getCommand("unban").setExecutor(new CommandBan());
        getCommand("kick").setExecutor(new CommandKick());
        getCommand("vanish").setExecutor(new CommandVanish());
        getCommand("invsee").setExecutor(new CommandInvsee());
        getCommand("ping").setExecutor(new CommandPing());
        getCommand("home").setExecutor(new CommandHome());
        getCommand("sethome").setExecutor(new CommandHome());
        getCommand("delhome").setExecutor(new CommandHome());
        getCommand("money").setExecutor(new CommandMoney());
        getCommand("day").setExecutor(new CommandTime());
        getCommand("night").setExecutor(new CommandTime());
        getCommand("fly").setExecutor(new CommandFly());
        getCommand("freeze").setExecutor(new CommandFreeze());
        getCommand("unfreeze").setExecutor(new CommandFreeze());
        getCommand("mod").setExecutor(new CommandMod());

        getServer().getPluginManager().registerEvents(new ListenerManager(), this);

        getServer().getPluginManager().registerEvents(new ModCancels(), this);
        getServer().getPluginManager().registerEvents(new ModInteract(), this);

    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    public static void freezeEntity(Entity en) {
        net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) en).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        nmsEn.c(compound);
        compound.setByte("NoAI", (byte) 1);
        nmsEn.f(compound);
    }

    public static void setActionBar(Player player, String message) {
        CraftPlayer p = (CraftPlayer) player;
        IChatBaseComponent ibc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(ibc, (byte) 2);
        p.getHandle().playerConnection.sendPacket(packet);
    }

}
