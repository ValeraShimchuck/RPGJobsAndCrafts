package com.RPGJC;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Inventories {
    private Main plugin;
    public Inventories(Main plugin) {
        this.plugin = plugin;
    }
    public Inventory RaceInventory(){
        Inventory inventory = Bukkit.createInventory(null,9,"Выберете расу");
        inventory.setItem(2,plugin.items.HumansRaceBlock());
        inventory.setItem(3,plugin.items.MageRaceBlock());
        inventory.setItem(4,plugin.items.InfoBlock());
        inventory.setItem(5,plugin.items.ZomoRaceBlock());
        inventory.setItem(6,plugin.items.CriganeRaceBlock());
        return inventory;
    }
    public Inventory menuMainInventory(){
        Inventory inventory = Bukkit.createInventory(null,9*5,"Меню");
        for(int i=0;i<9;i++){
            inventory.setItem(i,plugin.items.fillItem());
            inventory.setItem(i+4*9,plugin.items.fillItem());
        }
        for(int i=1;i <5;i++){
            inventory.setItem(i*9,plugin.items.fillItem());
            inventory.setItem(i*9+8,plugin.items.fillItem());
        }
        inventory.setItem(2*9-1+5,plugin.items.playerStatistic());
        inventory.setItem(2*9-1+3,plugin.items.craftBookItem());
        inventory.setItem(2*9-1+7,plugin.items.jobItem());
        return inventory;
    }
}
