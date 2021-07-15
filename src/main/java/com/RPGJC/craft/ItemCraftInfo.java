package com.RPGJC.craft;

import com.RPGJC.dataKeeper.RaceType;
import org.bukkit.inventory.ShapedRecipe;

public class ItemCraftInfo {
    private final CustomRecipe recipe;
    private final Integer minLvl;
    private final Integer mana;
    private final RaceType[] raceTypes;
    private final CraftItems craftItems;
    public ItemCraftInfo(CustomRecipe recipe,CraftItems craftItems, Integer minLvl, Integer mana, RaceType...raceTypes){
        this.recipe = recipe;
        this.minLvl = minLvl;
        this.mana = mana;
        this.raceTypes = raceTypes;
        this.craftItems = craftItems;
    }
    public CustomRecipe getRecipe(){
        return recipe;
    }
    public Integer getMinLvl(){
        return minLvl;
    }
    public Integer getMana(){
        return mana;
    }
    public RaceType[] getRaceTypes(){
        return raceTypes;
    }
    public CraftItems getCraftItems() {return craftItems;}
}
