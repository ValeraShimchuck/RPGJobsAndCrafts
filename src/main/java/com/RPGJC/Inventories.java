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
}
