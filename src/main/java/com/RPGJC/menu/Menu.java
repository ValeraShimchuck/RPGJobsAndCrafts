package com.RPGJC.menu;

import com.RPGJC.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class Menu {
    private HashMap<Player, Boolean> transition = new HashMap<>();
    private Main plugin;
    private MenuInterface mainMenu;
    private MenuInterface jobMenu;
    private CraftMenu craftMenu;
    private MenuInterface statisticMenu;
    public Menu(Main plugin){
        this.plugin = plugin;
        this.mainMenu = new MainMenu(this.plugin);
        this.jobMenu = new JobMenu(this.plugin);
        this.craftMenu = new CraftMenu(this.plugin);
        this.statisticMenu = new StatisticMenu(this.plugin);
    }
    public void buildInventory(Menus menu, Player p){
        switch (menu){
            case MAIN:
                mainMenu.buildInventory(p);
                break;
            case STATISTIC:
                statisticMenu.buildInventory(p);
                break;
            case CRAFTS:
                craftMenu.buildInventory(p);
                break;
            case JOB:
                jobMenu.buildInventory(p);
                break;
        }
    }
    public Inventory getInventory(Menus menu,Player p){
        Inventory inv = null;
        switch (menu){
            case MAIN:
                if(mainMenu.getInventory(p) == null) return null;
                inv = mainMenu.getInventory(p);
                break;
            case STATISTIC:
                if(statisticMenu.getInventory(p) == null) return null;
                inv = statisticMenu.getInventory(p);
                break;
            case CRAFTS:
                if(craftMenu.getInventory(p) == null) return null;
                inv = craftMenu.getInventory(p);
                break;
            case JOB:
                if(jobMenu.getInventory(p) == null) return null;
                inv = jobMenu.getInventory(p);
                break;
        }
        return inv;
    }
    public Menus getMenu(Player p){
        if(mainMenu.playerInInventory(p))return Menus.MAIN;
        if(jobMenu.playerInInventory(p))return Menus.JOB;
        if(statisticMenu.playerInInventory(p))return Menus.STATISTIC;
        if(craftMenu.playerInInventory(p))return Menus.CRAFTS;
        return Menus.NONE;
    }
    private void deleteInventory(Menus menu, Player p){
        switch (menu){
            case MAIN:
                mainMenu.deleteInventory(p);
                break;
            case STATISTIC:
                statisticMenu.deleteInventory(p);
                break;
            case CRAFTS:
                craftMenu.deleteInventory(p);
                break;
            case JOB:
                jobMenu.deleteInventory(p);
                break;
        }
    }
    public void setTransition(Player p,Boolean bool){
        if(transition.containsKey(p))transition.replace(p,bool);
        else transition.put(p,bool);

    }
    public void deletePlayer(Player p){
        if(getMenu(p) == null) return;
        Menus menu = getMenu(p);
        deleteInventory(menu, p);
    }
    public Boolean getTransition(Player p){
        if(!transition.containsKey(p))setTransition(p,false);
        return transition.get(p);
    }

    public void changeMenu(Menus menu,Player p){
        setTransition(p,true);
        deleteInventory(getMenu(p),p);
        buildInventory(menu, p);
        setTransition(p,false);
    }
    public MenuInterface getMenuObject(Menus menu){
        switch (menu){
            case MAIN:
                return mainMenu;
            case STATISTIC:
                return statisticMenu;
            case CRAFTS:
                return craftMenu;
            case JOB:
                return jobMenu;
        }
        return null;
    }
}
