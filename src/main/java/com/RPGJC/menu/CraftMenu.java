package com.RPGJC.menu;

import com.RPGJC.Main;
import com.RPGJC.craft.Craft;
import com.RPGJC.craft.CraftItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CraftMenu implements MenuInterface{
    private  HashMap<Player, Inventory> map = new HashMap<>();
    private HashMap<Player, Boolean> transition = new HashMap<>();
    private HashMap<CraftItems,Inventory> recipes = new HashMap<>();
    private List<Inventory> inventories = new ArrayList<>();
    private int pages;
    private Main plugin;
    public CraftMenu(Main plugin){
        this.plugin=plugin;
        for(CraftItems craftItems: CraftItems.values()){
            recipes.put(craftItems,plugin.inventories.craftBookInventory(craftItems));
        }
        Integer pages = CraftItems.values().length / 27;
        if(CraftItems.values().length%27 == 0)pages--;
        this.pages=pages;
        Inventory inv;
        for(int i=0;i<=pages;i++){
            plugin.getLogger().info(String.valueOf(i));
            if(i==0){
                if(pages>0){
                    inv = plugin.inventories.menuPatternInventory("R");
                }else{
                    inv = plugin.inventories.menuPatternInventory("");
                }
            }else{
                if(pages>i){
                    inv = plugin.inventories.menuPatternInventory("LR");
                }else{
                    inv = plugin.inventories.menuPatternInventory("L");
                }
            }
            int section =CraftItems.values().length-i*27;

            for(int i1=0;i1<section;i1++){
                inv.setItem(1*9+i1,plugin.craft.getCraftInfo(CraftItems.values()[i1+(i*27)]).getItem());
            }
            inventories.add(inv);
        }
    }
    @Override
    public void buildInventory(Player p) {
        map.put(p,inventories.get(0));
        p.openInventory(inventories.get(0));
    }

    @Override
    public void deleteInventory(Player p) {
        if(transition.containsKey(p))if(transition.get(p))return;
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
    public Integer getPage(Player p){
        if(!playerExists(map,p))return null;
        for(int i=0;i<inventories.size();i++){
            if(getInventory(p).equals(inventories.get(i))){
                return i;
            }
        }
        return null;
    }
    public void nextPage(Player p){
        if(!playerExists(map,p))return;
        if(getPage(p)<pages){
            putTransition(p,true);
            int temp = getPage(p);
            map.replace(p,inventories.get(temp+1));
            p.openInventory(inventories.get(temp+1));
            transition.replace(p,false);
        }
    }
    public void prevPage(Player p){
        if(!playerExists(map,p))return;
        if(getPage(p)>0){
            putTransition(p,true);
            int temp = getPage(p);
            map.replace(p,inventories.get(temp-1));
            p.openInventory(inventories.get(temp-1));
            transition.replace(p,false);
        }
    }
    public void openRecipe(CraftItems craftItems, Player p){
        putTransition(p,true);
        p.openInventory(recipes.get(craftItems));
        putTransition(p,false);
    }
    public Inventory getRecipe(CraftItems craftItems){return recipes.get(craftItems);}
    private void putTransition(Player p,boolean bool){
        if(transition.containsKey(p))transition.replace(p,bool);
        else transition.put(p,bool);
    }
}
