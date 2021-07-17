package com.RPGJC;

import com.RPGJC.craft.CraftItems;
import com.RPGJC.dataKeeper.Job;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
        ItemStack playerStatistic = plugin.items.playerStatistic();
        inventory.setItem(2*9-1+5,playerStatistic);
        inventory.setItem(2*9-1+3,plugin.items.craftBookItem());
        inventory.setItem(2*9-1+7,plugin.items.jobItem());
        return inventory;
    }
    public Inventory menuJobInventory(Player p){
        Inventory inventory = Bukkit.createInventory(null,9*5,"Работы");
        for(int i=0;i<9;i++){
            inventory.setItem(i,plugin.items.fillItem());
            inventory.setItem(i+4*9,plugin.items.fillItem());
        }
        for(int i=1;i <5;i++){
            inventory.setItem(i*9,plugin.items.fillItem());
            inventory.setItem(i*9+8,plugin.items.fillItem());
        }
        inventory.setItem(2*9-1+2,plugin.items.lumberjackJobItem(p));
        inventory.setItem(2*9-1+4,plugin.items.minerJobItem(p));
        inventory.setItem(2*9-1+6,plugin.items.farmerJobItem(p));
        inventory.setItem(2*9-1+8,plugin.items.butcherJobItem(p));
        inventory.setItem(3*9-1+8,plugin.items.returnItem());
        return inventory;
    }
    public Inventory craftBookInventory(CraftItems craftItems){
        Inventory inventory = Bukkit.createInventory(null,9*5,"Крафт");
        for(int i = 0;i<5;i++){
            for(int i1 =0;i1<5;i1++){
                inventory.setItem(9*i+2+i1,new ItemStack(Material.CRAFTING_TABLE));
            }
        }
        inventory = plugin.craft.getCraftInventory(12,inventory,plugin.craft.getCraftInfo(craftItems));
        inventory.setItem(26,plugin.craft.getCraftInfo(craftItems).getItem());
        inventory.setItem(44,plugin.items.returnItem());
        return inventory;
    }
    public Inventory menuPatternInventory(String arrowpos){
        Inventory inventory = Bukkit.createInventory(null,5*9,"Крафты");
        for(int i=0; i<9;i++){
            inventory.setItem(i,plugin.items.fillItem());
            inventory.setItem(i+4*9,plugin.items.fillItem());
        }
        inventory.setItem(4*9+4,plugin.items.returnItem());
        switch (arrowpos){
            case "R":
                inventory.setItem(5*9-1,plugin.items.rightSiteItem());
                break;
            case "L":
                inventory.setItem(4*9,plugin.items.leftSiteItem());
                break;
            case "RL":
            case "LR":
                inventory.setItem(5*9-1,plugin.items.rightSiteItem());
                inventory.setItem(4*9,plugin.items.leftSiteItem());
                break;
        }
        return inventory;
    }
    public Inventory statisticInventory(Player p){
        Inventory inventory = Bukkit.createInventory(null,5*9,"Statistic");
        for(int i=0;i<9;i++){
            inventory.setItem(i,plugin.items.fillItem());
            inventory.setItem(i+4*9,plugin.items.fillItem());
        }
        for(int i=1;i <5;i++){
            inventory.setItem(i*9,plugin.items.fillItem());
            inventory.setItem(i*9+8,plugin.items.fillItem());
        }
        inventory.setItem(9+4,plugin.items.mainStatisticItem(p));
        int i = 0;
        for(Job job: Job.values()){
            i++;
            if(job != Job.NONE) inventory.setItem(i*2+8,plugin.items.jobStatisticItem(p,job));
        }
        inventory.setItem(4*9-2,plugin.items.returnItem());
        return inventory;
    }

}
