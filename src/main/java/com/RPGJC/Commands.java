package com.RPGJC;

import com.RPGJC.craft.CraftItems;
import com.RPGJC.dataKeeper.RaceType;
import com.RPGJC.menu.Menus;
import com.mojang.authlib.GameProfile;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import net.minecraft.server.v1_13_R2.MinecraftServer;
import net.minecraft.server.v1_13_R2.PlayerInteractManager;
import net.minecraft.server.v1_13_R2.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_13_R2.CraftServer;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class Commands implements CommandExecutor {
    private Main plugin;
    public Commands(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equals("testinventory")){
            if(!(commandSender instanceof Player)) return true;
            Player p = (Player) commandSender;
            p.openInventory(plugin.inventories.craftBookInventory(CraftItems.TEST_ITEM));
        }
        if(command.getName().equals("createracenpc")){
            if(!(commandSender instanceof Player)) return true;
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER,"Choose your race!");
            Player p = (Player) commandSender;
            Location loc = new Location(p.getWorld(),p.getLocation().getX(),p.getLocation().getY(),p.getLocation().getZ(),p.getLocation().getYaw(),p.getLocation().getPitch());
            npc.spawn(loc);
            List<Player> players = (List<Player>) Bukkit.getServer().getOnlinePlayers();

        }
        if(command.getName().equals("resetrace")){
            if(!(commandSender instanceof Player)) return true;
            String name = commandSender.getName();
            try {
                Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
                Statement stat = conn.createStatement();
                stat.executeUpdate(String.format("UPDATE player_data SET race='None' WHERE player='%s'",name));
                stat.close();
                conn.close();
                plugin.data.setRace((Player) commandSender , RaceType.NONE);
                plugin.sb.updateBoard((Player) commandSender);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(command.getName().equals("menu")){
            if(!(commandSender instanceof Player)) return true;
            plugin.menu.buildInventory(Menus.MAIN, (Player) commandSender);
        }
        return true;
    }
}
