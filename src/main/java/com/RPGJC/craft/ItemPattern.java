package com.RPGJC.craft;

import com.RPGJC.EnchantmentParams;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemPattern {
        private String name;
        private Material material;
        private Integer count = 1;
        private ItemStack item;
        private ItemMeta meta;
        private EnchantmentParams[] enchantments;
        private List<String> lore;
        public ItemPattern(String name, Material material){
            this.name = name;
            this.material = material;
            this.item = new ItemStack(this.material);
            this.meta = item.getItemMeta();
            this.meta.setDisplayName(this.name);
            this.item.setItemMeta(this.meta);
        }
        public ItemPattern count(Integer count){
            this.count = count;
            return this;
        }
        public ItemPattern enchant(EnchantmentParams ...enchantments){
            if(enchantments.length == 0) return this;
            this.enchantments = enchantments;
            for(EnchantmentParams enchantment: enchantments){
                this.meta.addEnchant(enchantment.getEnchantment(),enchantment.getLevel(),enchantment.getIgnoreLevelRestriction());
            }
            return this;
        }
        public ItemPattern lore(String ...lore){
            this.lore = Arrays.asList(lore);
            this.meta.setLore(this.lore);
            return this;
        }
        public ItemStack buildItem(){
            item.setAmount(this.count);
            item.setItemMeta(this.meta);
            return this.item;
        }

}
