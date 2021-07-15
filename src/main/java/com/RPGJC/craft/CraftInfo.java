package com.RPGJC.craft;

import com.RPGJC.dataKeeper.RaceType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CraftInfo {
    private final ShapedRecipe shapedRecipe;
    private final CustomRecipe recipe;
    private final Integer minLvl;
    private final Integer mana;
    private final RaceType[] raceTypes;

    public CraftInfo(CustomRecipe recipe, Integer minLvl, Integer mana, RaceType... raceTypes) {
        this.recipe = recipe;
        this.minLvl = minLvl;
        this.mana = mana;
        this.raceTypes = raceTypes;
        shapedRecipe = recipe.getShapedRecipe();
    }

    public ShapedRecipe getRecipe() {
        return shapedRecipe;
    }

    public Integer getMinLvl() {
        return minLvl;
    }

    public Integer getMana() {
        return mana;
    }

    public RaceType[] getRaceTypes() {
        return raceTypes;
    }

    public ItemStack getItem() {
        return recipe.getResult();
    }

    public List<ItemStack> getItemsInCraft() {
        String[] shape = shapedRecipe.getShape();
        HashMap<Character, ItemStack> ingredients = (HashMap<Character, ItemStack>) shapedRecipe.getIngredientMap();
        List<ItemStack> items = new ArrayList<>();
        for (String line : shape) {
            for (Character character : line.toCharArray()) {
                if (ingredients.containsKey(character)) items.add(ingredients.get(character));
                else items.add(new ItemStack(Material.AIR));
            }
        }
        return items;
    }

}
