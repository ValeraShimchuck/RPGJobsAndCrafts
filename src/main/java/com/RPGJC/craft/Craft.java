package com.RPGJC.craft;

import com.RPGJC.Main;
import com.RPGJC.dataKeeper.RaceType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.*;

public class Craft {
    private Main plugin;
    private CraftItemStack craftItemStack;
    private HashMap<CraftItems,CraftInfo> crafts = new HashMap<>();
    public Craft(Main plugin){
        this.plugin=plugin;
        craftItemStack = new CraftItemStack(plugin);
        addCrafts(craftItemStack.getAllCrafts());
    }
    public void addCraft(CustomRecipe recipe,CraftItems craftItems, Integer minLvl, Integer mana, RaceType...raceTypes){
        crafts.put(craftItems,new CraftInfo(recipe,minLvl,mana,raceTypes));
    }
    public void addCrafts(ItemCraftInfo...itemCraftInfos){
        for(ItemCraftInfo ici:itemCraftInfos){
            addCraft(ici.getRecipe(), ici.getCraftItems(), ici.getMinLvl(), ici.getMana(),ici.getRaceTypes());
        }
    }
    public void initCrafts(){
        Collection<CraftInfo> craftInfos = crafts.values();
        for(CraftInfo craft: craftInfos){
            Bukkit.getServer().addRecipe(craft.getRecipe());
        }
    }
    public CraftInfo getCraftInfo(CraftItems craftItems){return crafts.get(craftItems);}
    public Inventory getCraftInventory(Integer beginShape, Inventory inv,CraftInfo craftInfo){
        int size = inv.getSize();
        if(size < 27) return inv;
        if(beginShape+20 > size)return inv;
        List<ItemStack> items = craftInfo.getItemsInCraft();
        for(int i =0; i<3; i++){
            inv.setItem(beginShape+i,items.get(0+i));
            inv.setItem(beginShape+9+i,items.get(3+i));
            inv.setItem(beginShape+18+i,items.get(6+i));
        }
        return inv;
    }

    public CraftItemStack getCraftItemStack() {
        return craftItemStack;
    }
}
class ItemPosition{
    private final int pos;
    private final ItemStack item;
    public ItemPosition(Integer pos, ItemStack item){
        this.pos = pos;
        this.item = item;
    }
    public int getPos(){return pos;}
    public ItemStack getItem() {return item;}
}
