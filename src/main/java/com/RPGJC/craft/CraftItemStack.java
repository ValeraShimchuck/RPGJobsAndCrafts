package com.RPGJC.craft;

import com.RPGJC.Main;
import com.RPGJC.dataKeeper.RaceType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

public class CraftItemStack {
    private Main plugin;
    public CraftItemStack(Main plugin){
        this.plugin=plugin;
    }
    public ItemStack getItem(CraftItems craftItems,Integer lvl) {
        switch (craftItems){
            case TEST_ITEM:
                return new ItemPattern("TEST_ITEM1", Material.DIAMOND)
                        .lore(
                                ChatColor.RED+"Item for test!!!",
                                ChatColor.WHITE+"Item lvl: "+lvl)
                        .setUnEjected().buildItem();
            case TEST_ITEM2:
                return new ItemPattern("TEST_ITEM2", Material.EMERALD)
                        .lore(
                                ChatColor.RED+"Item for test!!!",
                                ChatColor.WHITE+"Item lvl: "+lvl)
                        .setUnEjected().buildItem();
            case TEST_ITEM3:
                return new ItemPattern("TEST_ITEM3", Material.COAL)
                        .lore(
                                ChatColor.RED+"Item for test!!!",
                                ChatColor.WHITE+"Item lvl: "+lvl)
                        .setUnEjected().buildItem();
            case TEST_ITEM4:
                return new ItemPattern("TEST_ITEM4", Material.REDSTONE)
                        .lore(
                                ChatColor.RED+"Item for test!!!",
                                ChatColor.WHITE+"Item lvl: "+lvl)
                        .setUnEjected().buildItem();
        }
        return null;
    }
    public CraftItems getCraftItem(ItemStack item){
        List<String> lore = item.getItemMeta().getLore();
        if(lore.size() < 2)return null;
        if(!lore.get(1).startsWith(ChatColor.WHITE+"Item lvl: "))return null;
        Integer lvl = Integer.valueOf(lore.get(1).substring(12));

        for(CraftItems craftItems: CraftItems.values()){

            if(item.isSimilar(getItem(craftItems,lvl))){
                return craftItems;
            }
        }
        return null;
    }
    public ItemCraftInfo getCraftInfo(CraftItems craftItems){
        int lvl;
        int mana;
        NamespacedKey key;
        CustomRecipe shape;
        switch (craftItems){
            case TEST_ITEM:
                lvl = 1;
                mana = 0;
                key = new NamespacedKey(plugin,"TEST_ITEM1");
                shape = new CustomRecipe(key,getItem(craftItems,lvl));
                shape.shape(new String[]{"A A"," A ","A A"});
                shape.setIngredient('A',Material.STICK);
                return new ItemCraftInfo(shape,craftItems,lvl,mana, RaceType.HUMAN);
            case TEST_ITEM2:
                lvl = 0;
                mana = 0;
                key = new NamespacedKey(plugin,"TEST_ITEM2");
                shape = new CustomRecipe(key,getItem(craftItems,lvl));
                shape.shape(new String[]{"AAA","AAA","AAA"});
                shape.setIngredient('A',Material.STICK);
                return new ItemCraftInfo(shape,craftItems,lvl,mana, RaceType.HUMAN);
            case TEST_ITEM3:
                lvl = 2;
                mana = 0;
                key = new NamespacedKey(plugin,"TEST_ITEM3");
                shape = new CustomRecipe(key,getItem(craftItems,lvl));
                shape.shape(new String[]{"AAA","A A","AAA"});
                shape.setIngredient('A',Material.STICK);
                return new ItemCraftInfo(shape,craftItems,lvl,mana, RaceType.HUMAN);
            case TEST_ITEM4:
                lvl = 1;
                mana = 1;
                key = new NamespacedKey(plugin,"TEST_ITEM4");
                shape = new CustomRecipe(key,getItem(craftItems,lvl));
                shape.shape(new String[]{" A ","A A"," A "});
                shape.setIngredient('A',Material.STICK);
                return new ItemCraftInfo(shape,craftItems,lvl,mana, RaceType.HUMAN);
        }
        return null;
    }
    public ItemCraftInfo[] getAllCrafts(){
        List<ItemCraftInfo> list = new ArrayList<>();
        for(CraftItems craftItems:CraftItems.values()){
            list.add(getCraftInfo(craftItems));
        }
        return list.toArray(new ItemCraftInfo[0]);
    }
}
