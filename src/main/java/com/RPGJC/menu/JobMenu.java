package com.RPGJC.menu;

import com.RPGJC.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;

public class JobMenu implements MenuInterface{
    private HashMap<Player,Inventory> map = new HashMap<>();
    private Main plugin;
    public JobMenu(Main plugin){this.plugin=plugin;}
    @Override
    public void buildInventory(Player p) {
        Inventory inv = plugin.inventories.menuJobInventory(p);
        map.put(p,inv);
        p.openInventory(inv);
    }

    @Override
    public void deleteInventory(Player p) {
        if(playerExists(map,p)){
            map.remove(p);
        }
    }

    @Override
    public Inventory getInventory(Player p) {
        if(playerExists(map,p)){
            return map.get(p);
        }
        return null;
    }

    @Override
    public Boolean playerInInventory(Player p) {
        return playerExists(map,p);
    }

    @Override
    public HashMap<Player, Inventory> getMap() {
        return map;
    }

    @Override
    public List<Player> getPlayers() {
        return (List<Player>) map.keySet();
    }
}
