package com.RPGJC.menu;

import com.RPGJC.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class Menu {
    private HashMap<Player, Boolean> transition = new HashMap<>();
    private Main plugin;
    private MenuInterface mainMenu;
    public Menu(Main plugin){
        this.plugin = plugin;
        this.mainMenu = new MainMenu(this.plugin);
    }
    public void buildInventory(Menus menu, Player p){
        switch (menu){
            case MAIN:
                mainMenu.buildInventory(p);
                break;
            case STATISTIC:
                //code
                break;
            case CRAFTS:
                //code
                break;
            case JOB:
                //code
                break;
        }
    }
    public Inventory getInventory(Menus menu,Player p){
        Inventory inv = null;
        switch (menu){
            case MAIN:
                inv = mainMenu.getInventory(p);
                break;
            case STATISTIC:
                //code
                break;
            case CRAFTS:
                //code
                break;
            case JOB:
                //code
                break;
        }
        return inv;
    }
    public Menus getMenu(Player p){
        if(mainMenu.playerInInventory(p))return Menus.MAIN;

        //code
        return Menus.NONE;
    }
    private void deleteInventory(Menus menu, Player p){
        switch (menu){
            case MAIN:
                mainMenu.deleteInventory(p);
                break;
            case STATISTIC:
                //code
                break;
            case CRAFTS:
                //code
                break;
            case JOB:
                //code
                break;
        }
    }
    private void setTransition(Player p,Boolean bool){
        if(transition.containsKey(p))transition.replace(p,bool);
        else transition.put(p,bool);

    }
    public void deletePlayer(Player p){
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
}
