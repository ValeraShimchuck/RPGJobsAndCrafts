package com.RPGJC;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentParams {
    private Enchantment enchantment;
    private Integer level;
    private Boolean ignoreLevelRestriction;

    public EnchantmentParams(Enchantment enchantment, int level, boolean b) {
        this.enchantment = enchantment;
        this.level = level;
        this.ignoreLevelRestriction = b;
    }

    public Integer getLevel() {
        return level;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public Boolean getIgnoreLevelRestriction() {
        return ignoreLevelRestriction;
    }
}
