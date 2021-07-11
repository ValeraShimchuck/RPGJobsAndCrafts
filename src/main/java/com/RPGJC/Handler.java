package com.RPGJC;

import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.sql.*;

public class Handler implements Listener {
    private Main plugin;
    public Handler(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onExpSpawn(EntitySpawnEvent e){
        if(e.getEntity() instanceof ExperienceOrb){e.setCancelled(true);}
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Connection connection = null;
        Boolean isConnectionPass = false;
        try {
            connection = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
            Statement s = connection.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT player FROM player_data WHERE player ='"+e.getPlayer().getName()+"';");
            if(plugin.getResultSetRowCount(resultSet) == 0){
                s.executeUpdate("INSERT player_data(player,level,experience,race) VALUES('"+e.getPlayer().getName()+"',1,0,'None');");
                e.getPlayer().setExp(0);
                e.getPlayer().setLevel(1);
            }
            ResultSet raceSet = s.executeQuery("SELECT * FROM player_data WHERE player='"+e.getPlayer().getName()+"';");
            raceSet.next();
            plugin.playersLvl.put(e.getPlayer(),raceSet.getInt(3));
            plugin.playersXP.put(e.getPlayer(),raceSet.getInt(4));
            plugin.race.put(e.getPlayer(),raceSet.getString(5));

            s.close();
            connection.close();
            isConnectionPass = true;

        } catch (Exception exception) {
            e.getPlayer().kickPlayer("SQL error! Please rejoin");
        }
        if(isConnectionPass){
            Player p = e.getPlayer();
            plugin.sb.onJoin(p);
        }


    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        plugin.sb.onLeave(e.getPlayer());
        plugin.playersXP.remove(e.getPlayer());
        plugin.playersLvl.remove(e.getPlayer());
        plugin.race.remove(e.getPlayer());
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){
        if(CitizensAPI.getNPCRegistry().isNPC(e.getRightClicked()) && e.getHand().equals(EquipmentSlot.HAND)){
            if(CitizensAPI.getNPCRegistry().getNPC(e.getRightClicked()).getName().equals("Choose your race!")){
                Connection connection = null;
                String race = null;
                try {
                    connection = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
                    Statement s = connection.createStatement();
                    ResultSet resultSet = s.executeQuery(String.format("SELECT * FROM player_data WHERE player='%s'",e.getPlayer().getName()));
                    resultSet.next();
                    race = resultSet.getString(5);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    plugin.getLogger().severe("Error! Failed to connect or use DB");
                }
                if(race.equals("None")){
                    plugin.getLogger().info("Inventory opens!");
                    Player p = e.getPlayer();
                    //plugin.getLogger().info(String.valueOf(plugin.minecraftInventories.containsKey("RaceInventory")));
                    p.openInventory(plugin.minecraftInventories.get("RaceInventory"));
                }else{
                    e.getPlayer().sendMessage("You have race!");
                }


            }
        }

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        plugin.inventoryChecks.onRaceItemsClick(e);

        if(e.getClickedInventory() == null) return;


    }
}
