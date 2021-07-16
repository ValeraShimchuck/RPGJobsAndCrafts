package com.RPGJC;

import com.RPGJC.craft.CraftItems;
import com.RPGJC.dataKeeper.Job;
import com.RPGJC.dataKeeper.RaceType;
import com.RPGJC.menu.CraftMenu;
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
            if(e.getCurrentItem().equals(plugin.items.jobItem())){
                plugin.menu.changeMenu(Menus.JOB,p);
            }
            if(e.getCurrentItem().equals(plugin.items.craftBookItem())){
                plugin.menu.changeMenu(Menus.CRAFTS,p);
            }
        }
    }
    public void onJobMenuClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Inventory c = e.getClickedInventory();
        Player p = (Player) e.getWhoClicked();
        if(plugin.menu.getInventory(Menus.JOB,p)==null)return;
        if(inv.equals(plugin.menu.getInventory(Menus.JOB,p))){
            if(c == null)return;
            e.setCancelled(true);
            Job job = null;
            if(e.getCurrentItem().equals(plugin.items.lumberjackJobItem(p)))job = Job.LUMBERJACK;
            if(e.getCurrentItem().equals(plugin.items.minerJobItem(p)))job = Job.MINER;
            if(e.getCurrentItem().equals(plugin.items.farmerJobItem(p)))job = Job.FARMER;
            if(e.getCurrentItem().equals(plugin.items.butcherJobItem(p)))job = Job.BUTCHER;
            if(job != null){
                plugin.data.createJobPlayer(job,p);
                plugin.data.setJob(p,job);
                plugin.sb.updateBoard(p);
                p.closeInventory();
            }
            if(e.getCurrentItem().equals(plugin.items.returnItem())){
                plugin.menu.changeMenu(Menus.MAIN,p);
            }
        }
    }
    public void onCraftMenuClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Inventory c = e.getClickedInventory();
        Player p = (Player) e.getWhoClicked();
        if(plugin.menu.getInventory(Menus.CRAFTS,p)==null)return;
        if(inv.equals(plugin.menu.getInventory(Menus.CRAFTS,p))){
            if(c == null)return;
            e.setCancelled(true);
            for(CraftItems craftItems: CraftItems.values()){
                if(e.getCurrentItem().equals(plugin.craft.getCraftInfo(craftItems).getItem())){
                    plugin.menu.setTransition(p,true);
                    CraftMenu craftMenu = (CraftMenu) plugin.menu.getMenuObject(Menus.CRAFTS);
                    p.openInventory(craftMenu.getRecipe(craftItems));
                    plugin.menu.setTransition(p,false);
                    break;
                }
                if(e.getCurrentItem().equals(plugin.items.rightSiteItem())){
                    CraftMenu craftMenu = (CraftMenu) plugin.menu.getMenuObject(Menus.CRAFTS);
                    craftMenu.nextPage(p);
                }
                if(e.getCurrentItem().equals(plugin.items.leftSiteItem())){
                    CraftMenu craftMenu = (CraftMenu) plugin.menu.getMenuObject(Menus.CRAFTS);
                    craftMenu.prevPage(p);
                }
                if(e.getCurrentItem().equals(plugin.items.returnItem())){
                    plugin.menu.changeMenu(Menus.MAIN,p);
                }
            }

        }
    }
    public void onRecipeInventoryClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Inventory c = e.getClickedInventory();
        Player p = (Player) e.getWhoClicked();
        if(plugin.menu.getInventory(Menus.CRAFTS,p)==null)return;
        if(c == null)return;
        for(CraftItems craftItems:CraftItems.values()){
            CraftMenu craftMenu = (CraftMenu) plugin.menu.getMenuObject(Menus.CRAFTS);
            if(inv.equals(craftMenu.getRecipe(craftItems))){
                e.setCancelled(true);
                if(e.getCurrentItem().equals(plugin.items.returnItem())){
                    plugin.menu.setTransition(p,true);
                    p.openInventory(plugin.menu.getInventory(Menus.CRAFTS,p));
                    plugin.menu.setTransition(p,false);
                }
                break;
            }
        }
    }
}
