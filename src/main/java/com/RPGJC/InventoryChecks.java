package com.RPGJC;

import com.RPGJC.dataKeeper.RaceType;
import com.RPGJC.menu.Menus;
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
                    plugin.data.setRace((Player) e.getWhoClicked(), RaceType.HUMAN);
                }
                if(item.equals(plugin.items.MageRaceBlock())){
                    e.getWhoClicked().sendMessage("You are Mage!");
                    plugin.data.setRace((Player) e.getWhoClicked(), RaceType.MAGE);
                }
                if(item.equals(plugin.items.ZomoRaceBlock())){
                    e.getWhoClicked().sendMessage("You are Zomo!");
                    plugin.data.setRace((Player) e.getWhoClicked(), RaceType.ZOMO);
                }

                if(item.equals(plugin.items.CriganeRaceBlock())){
                    e.getWhoClicked().sendMessage("You are Crigane!");
                    plugin.data.setRace((Player) e.getWhoClicked(), RaceType.CRIGANE);
                }
                if(item.equals(plugin.items.CriganeRaceBlock()) || item.equals(plugin.items.ZomoRaceBlock())|| item.equals(plugin.items.MageRaceBlock())||item.equals(plugin.items.HumansRaceBlock())){
                    plugin.sb.updateBoard((Player) e.getWhoClicked());
                    e.getWhoClicked().closeInventory();
                }
            }
        }
    }
    public void onMainMenuClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Inventory c = e.getClickedInventory();
        Player p = (Player) e.getWhoClicked();
        if(plugin.menu.getInventory(Menus.MAIN,p) == null)return;
        if(inv.equals(plugin.menu.getInventory(Menus.MAIN,p))){
            if(c == null)return;
            e.setCancelled(true);
        }
    }
}
