package com.RPGJC.craft;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomRecipe {
    private ShapedRecipe shapedRecipe;
    private HashMap<Character,ItemStack> charItemMap = new HashMap<>();
    private String[] shape;
    public CustomRecipe(NamespacedKey key, ItemStack item){
        shapedRecipe=new ShapedRecipe(key,item);
    }
    public void shape(String...shape){
        this.shape=shape;
        shapedRecipe.shape(shape);
    }
    public void setIngredient(char character,ItemStack item){
        charItemMap.put(character,item);
        shapedRecipe.setIngredient(character,item.getType());
    }
    public void setIngredient(char character,Material material){
        charItemMap.put(character,new ItemStack(material));
        shapedRecipe.setIngredient(character,material);
    }
    public ItemStack[] getIngredients(){
        List<ItemStack> items = new ArrayList<>();
        for(String line:shape){
            for(Character character:line.toCharArray()){
                if(charItemMap.containsKey(character)) items.add(charItemMap.get(character));
                else items.add(null);
            }
        }
        return items.toArray(new ItemStack[0]);
    }

    public ShapedRecipe getShapedRecipe() {
        return shapedRecipe;
    }
    public ItemStack getResult(){
        return shapedRecipe.getResult();
    }
}
