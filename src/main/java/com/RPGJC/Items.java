package com.RPGJC;

import net.citizensnpcs.npc.ai.speech.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Items {
    private Main plugin;
    public Items(Main plugin) {
        this.plugin = plugin;
    }
    public ItemStack HumansRaceBlock(){
        ItemStack item = new ItemPattern(ChatColor.YELLOW+""+ChatColor.BOLD +"Раса людей",Material.PLAYER_HEAD)
                .lore(
                        ChatColor.WHITE+"Раса людей - простые существа в своем роде, которые",
                        ChatColor.WHITE+"хотят получить славу и богатства, но за это им нужно",
                        ChatColor.WHITE+"платить кропотливым трудом. Сможите ли вы прославится",
                        ChatColor.WHITE+"и розбагатеть? Зависит только от вас!",
                        ChatColor.GOLD+""+ChatColor.BOLD+"Особенности расы:",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Данная раса не имеет приемуществ, но также и не имеет недостатков",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Доступны все рецепты из основной книги").buildItem();
        return item;
    }
    public  ItemStack MageRaceBlock(){
        ItemStack item = new ItemPattern(ChatColor.YELLOW+""+ChatColor.BOLD +"Раса магов",Material.TOTEM_OF_UNDYING)
                .lore(
                        ChatColor.WHITE+"Раса магов - существа наделенные магической силой, очень ленивы по своей природе.",
                        ChatColor.WHITE+"Все маги хотят стать невероятно сильными, а также подчинить остальные расы, которые",
                        ChatColor.WHITE+"были опделены магическими способностями. Станете ли вы сильным магом и правителем?",
                        ChatColor.WHITE+"Зависит только от вас!",
                        ChatColor.GOLD+""+ChatColor.BOLD+"Особенности расы:",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Возможность крафтить и улучшать магические посохы",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Все крафты и улучшения требуют ману",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Стоимость создание клана в два раза дешевле, по",
                        ChatColor.GOLD+""+ChatColor.BOLD+"сравнения с остальными расами",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Возможность одевать артефакты на 2 уровня выше",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Нельзя одевать тяжелую броню").buildItem();
        return item;
    }
    public ItemStack ZomoRaceBlock(){
        ItemStack item = new ItemPattern(ChatColor.YELLOW+""+ ChatColor.BOLD+"Раса зомо",Material.ROTTEN_FLESH)
                .lore(
                        ChatColor.WHITE+"Раса зомо - глуповатые, но живучие и сильные существа. У самих зомо иметь разум ",
                        ChatColor.WHITE+"является чем-то странным и даже грешным. Но несмотря на эти ярлыки, все молодые зомо,",
                        ChatColor.WHITE+"хотят стать умными и быть лучше своих братьев. Сможете ли вы обрести разум и стать",
                        ChatColor.WHITE+"великим воином? Зависит только от вас!",
                        ChatColor.GOLD+""+ChatColor.BOLD+"Особенности расы:",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Для крафта предмета, нужно на 2 уровня больше, чем обычно",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- +50% к здоровью и +2 к атаке",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Тяжелая броня дает медлительность 1, а не 2",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Спешка 1 уровня навсегда",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Нельзя зачаровать предметы").buildItem();
        return item;
    }
    public ItemStack CriganeRaceBlock(){
        ItemStack item = new ItemPattern(ChatColor.YELLOW+""+ChatColor.BOLD+"Раса криган",Material.NETHER_STAR)
                .lore(
                        ChatColor.WHITE+"Раса криган - проворные и хитроумные создания, но слабые. Будучи высокоразвитой",
                        ChatColor.WHITE+"цывилизацией кригане крайне не устойчивы к магии и любая для них магия болезненна.",
                        ChatColor.WHITE+"Целью криган является уничтожение остальных рас, но своих бить рука не поднимается.",
                        ChatColor.WHITE+"Сможете ли вы улучшить свои навыки и уничтожить представителей других рас?",
                        ChatColor.WHITE+"Зависит только от вас!",
                        ChatColor.GOLD+""+ChatColor.BOLD+"Особенности расы:",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Для крафта предмета, нужно на 2 уровня меньше, чем обычно",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- -10% к здоровью",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- +3 к атаке",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Нельзя одевать тяжелую броню",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Скорость +50%",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Дополнительный урон по вам от магии +20%",
                        ChatColor.GOLD+""+ChatColor.BOLD+"- Урон по игрокам одной расы на 50% ниже").buildItem();
        return item;
    }
    public  ItemStack InfoBlock(){
        ItemStack item = new ItemPattern(ChatColor.GOLD+"Выберите расу",Material.YELLOW_STAINED_GLASS_PANE)
                .buildItem();
        return item;
    }
}

class ItemPattern{
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
