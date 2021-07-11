package com.RPGJC;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.*;

public class InventoryChecks {
    private Main plugin;
    public InventoryChecks(Main plugin) {
        this.plugin = plugin;
    }
    public void onRaceItemsClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Inventory c = e.getClickedInventory();

        if(inv.equals(plugin.minecraftInventories.get("RaceInventory"))){
            if(c == null) return;
            e.setCancelled(true);
            if(c.equals(plugin.minecraftInventories.get("RaceInventory"))){
                ItemStack item = e.getCurrentItem();
                //plugin.getLogger().info(String.valueOf(item));
                if(item.equals(plugin.items.HumansRaceBlock())){
                    e.getWhoClicked().sendMessage("You are Human!");
                    try {
                        Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
                        Statement s = conn.createStatement();
                        s.executeUpdate(String.format("UPDATE player_data SET race='Human' WHERE player='%s';",((Player)e.getWhoClicked()).getName()));
                        plugin.race.replace((Player) e.getWhoClicked(),"Human");
                        s.close();
                        conn.close();
                        e.getWhoClicked().closeInventory();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if(item.equals(plugin.items.MageRaceBlock())){
                    e.getWhoClicked().sendMessage("You are Mage!");
                    try {
                        Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
                        Statement s = conn.createStatement();
                        s.executeUpdate(String.format("UPDATE player_data SET race='Mage' WHERE player='%s';",((Player)e.getWhoClicked()).getName()));
                        plugin.race.replace((Player) e.getWhoClicked(),"Mage");
                        s.close();
                        conn.close();
                        e.getWhoClicked().closeInventory();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if(item.equals(plugin.items.ZomoRaceBlock())){
                    e.getWhoClicked().sendMessage("You are Zomo!");
                    try {
                        Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
                        Statement s = conn.createStatement();
                        s.executeUpdate(String.format("UPDATE player_data SET race='Zomo' WHERE player='%s';",((Player)e.getWhoClicked()).getName()));
                        plugin.race.replace((Player) e.getWhoClicked(),"Zomo");
                        s.close();
                        conn.close();
                        e.getWhoClicked().closeInventory();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }

                if(item.equals(plugin.items.CriganeRaceBlock())){
                    e.getWhoClicked().sendMessage("You are Crigane!");
                    try {
                        Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
                        Statement s = conn.createStatement();
                        s.executeUpdate(String.format("UPDATE player_data SET race='Crigane' WHERE player='%s';",((Player)e.getWhoClicked()).getName()));
                        plugin.race.replace((Player) e.getWhoClicked(),"Crigane");
                        s.close();
                        conn.close();
                        e.getWhoClicked().closeInventory();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if(item.equals(plugin.items.CriganeRaceBlock()) || item.equals(plugin.items.ZomoRaceBlock())|| item.equals(plugin.items.MageRaceBlock())||item.equals(plugin.items.HumansRaceBlock())){
                    plugin.sb.updateBoard((Player) e.getWhoClicked());
                }
            }
        }
    }
}
