package com.RPGJC.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;

public interface MenuInterface {
    void buildInventory(Player p);
    void deleteInventory(Player p);
    Inventory getInventory(Player p);
    Boolean playInInventory(Player p);
    HashMap<Player, Inventory> getMap();
    List<Player> getPlayers();

}
