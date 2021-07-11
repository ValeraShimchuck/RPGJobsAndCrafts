package com.RPGJC;

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
        if(command.getName().equals("createnpc")){
            if(strings.length != 2) return false;
            if(strings[0].length() > 15) return false;
            //code here
            Player p = (Player) commandSender;
            Double x = p.getLocation().getX();
            Double y = p .getLocation().getY();
            Double z = p.getLocation().getZ();
            Float pitch = p.getLocation().getPitch();
            Float yaw = p.getLocation().getYaw();
            String name = strings[0];
            String fun = strings[1];
            MinecraftServer nmsServer = ((CraftServer)Bukkit.getServer()).getServer();
            WorldServer nmsWorld = ((CraftWorld)Bukkit.getWorld(p.getWorld().getName())).getHandle();
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(),name);
            EntityPlayer npc = new EntityPlayer(nmsServer,nmsWorld,gameProfile,new PlayerInteractManager(nmsWorld));
            npc.setLocation(x,y,z,yaw,pitch);
            List<Player> players = (List<Player>) Bukkit.getServer().getOnlinePlayers();
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
                Statement stat = connection.createStatement();
                stat.executeUpdate(String.format("INSERT npc_data(name,world,x,y,z,pitch,yaw,fun) VALUES('%s','%s',%f,%f,%f,%f,%f,'%s')",name,p.getWorld().getName(),x,y,z,pitch,yaw,fun));

                stat.close();
                connection.close();

            } catch (Exception exception) {
                plugin.getLogger().severe(String.valueOf(exception));
            }
            plugin.npcs.add(npc);
            for(Player pp: players){
                plugin.addNPCPacket(npc,pp);
            }
            return true;
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
                plugin.race.replace((Player) commandSender,"None");
                plugin.sb.updateBoard((Player) commandSender);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }
}
