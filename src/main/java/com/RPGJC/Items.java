package com.RPGJC;

import com.RPGJC.craft.ItemPattern;
import com.RPGJC.dataKeeper.Job;
import net.citizensnpcs.npc.ai.speech.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
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

    public ItemStack playerStatistic(){
        ItemStack item = new ItemPattern(ChatColor.GOLD+"Статистика", Material.ENDER_EYE)
                .lore(ChatColor.WHITE+"Ваша статистика на сервере")
                .buildItem();
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Main.glow,1,true);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack jobItem(){
        ItemStack item = new ItemPattern(ChatColor.AQUA+"Работы", Material.DIAMOND_PICKAXE)
                .lore(ChatColor.WHITE+"Нажмите, что бы выбрать работу")
                .buildItem();
        return item;
    }
    public ItemStack craftBookItem(){
        ItemStack item = new ItemPattern(ChatColor.WHITE+"Книга рецептов", Material.BOOK)
                .lore(ChatColor.GOLD+"Нажмите, что бы открыть книгу рецептов")
                .buildItem();
        return item;
    }
    public ItemStack fillItem(){
        ItemStack item = new ItemPattern(" ", Material.BLACK_STAINED_GLASS_PANE).buildItem();
        return item;
    }
    public ItemStack lumberjackJobItem(Player p){
        ItemStack item;
        if(plugin.data.getJobXp(p, Job.LUMBERJACK) !=null){
            item = new ItemPattern(ChatColor.GOLD+""+ChatColor.BOLD+"Лесоруб", Material.DIAMOND_AXE)
                    .lore(
                            ChatColor.WHITE+"Стань лесорубом и руби деревья за деньги",
                            ChatColor.GOLD+"Уровень: "+ChatColor.WHITE+plugin.data.getJobLvl(p,Job.LUMBERJACK),
                            ChatColor.GOLD+"Опыт: "+ChatColor.WHITE+plugin.data.getJobXp(p,Job.LUMBERJACK)

                    ).buildItem();
        }else
        item = new ItemPattern(ChatColor.GOLD+""+ChatColor.BOLD+"Лесоруб", Material.DIAMOND_AXE)
                .lore(
                        ChatColor.WHITE+"Стань лесорубом и руби деревья за деньги",
                        ChatColor.RED+"Вы еще не работали лесорубом!"

                ).buildItem();

        return item;
    }
    public ItemStack minerJobItem(Player p){
        ItemStack item;
        if(plugin.data.getJobXp(p, Job.MINER) !=null){
            item = new ItemPattern(ChatColor.GOLD+""+ChatColor.BOLD+"Шахтер", Material.DIAMOND_PICKAXE)
                    .lore(
                            ChatColor.WHITE+"Стань шахтером и добывай минералы за деньги",
                            ChatColor.GOLD+"Уровень: "+ChatColor.WHITE+plugin.data.getJobLvl(p,Job.MINER),
                            ChatColor.GOLD+"Опыт: "+ChatColor.WHITE+plugin.data.getJobXp(p,Job.MINER)

                    ).buildItem();
        }else
            item = new ItemPattern(ChatColor.GOLD+""+ChatColor.BOLD+"Шахтер", Material.DIAMOND_PICKAXE)
                    .lore(
                            ChatColor.WHITE+"Стань шахтером и добывай минералы за деньги",
                            ChatColor.RED+"Вы еще не работали шахтером!"

                    ).buildItem();

        return item;
    }
    public ItemStack farmerJobItem(Player p){
        ItemStack item;
        if(plugin.data.getJobXp(p, Job.FARMER) !=null){
            item = new ItemPattern(ChatColor.GOLD+""+ChatColor.BOLD+"Фермер", Material.DIAMOND_HOE)
                    .lore(
                            ChatColor.WHITE+"Стань фермером и рости культуры за деньги",
                            ChatColor.GOLD+"Уровень: "+ChatColor.WHITE+plugin.data.getJobLvl(p,Job.FARMER),
                            ChatColor.GOLD+"Опыт: "+ChatColor.WHITE+plugin.data.getJobXp(p,Job.FARMER)

                    ).buildItem();
        }else
            item = new ItemPattern(ChatColor.GOLD+""+ChatColor.BOLD+"Фермер", Material.DIAMOND_HOE)
                    .lore(
                            ChatColor.WHITE+"Стань фермером и рости культуры за деньги",
                            ChatColor.RED+"Вы еще не работали фермером!"

                    ).buildItem();

        return item;
    }
    public ItemStack butcherJobItem(Player p){
        ItemStack item;
        if(plugin.data.getJobXp(p, Job.BUTCHER) !=null){
            item = new ItemPattern(ChatColor.GOLD+""+ChatColor.BOLD+"Мясник", Material.DIAMOND_SWORD)
                    .lore(
                            ChatColor.WHITE+"Стань мясником и руби монстров и животных за деньги",
                            ChatColor.GOLD+"Уровень: "+ChatColor.WHITE+plugin.data.getJobLvl(p,Job.BUTCHER),
                            ChatColor.GOLD+"Опыт: "+ChatColor.WHITE+plugin.data.getJobXp(p,Job.BUTCHER)

                    ).buildItem();
        }else
            item = new ItemPattern(ChatColor.GOLD+""+ChatColor.BOLD+"Мясник", Material.DIAMOND_SWORD)
                    .lore(
                            ChatColor.WHITE+"Стань мясником и руби монстров и животных за деньги",
                            ChatColor.RED+"Вы еще не работали мясником!"

                    ).buildItem();

        return item;
    }
    public ItemStack returnItem(){
        ItemStack item = new ItemPattern(ChatColor.YELLOW+""+ChatColor.BOLD+"Вернуться назад",Material.ARROW).buildItem();
        return item;
    }
    public ItemStack rightSiteItem(){
        ItemStack item = new ItemPattern(ChatColor.YELLOW+""+ChatColor.BOLD+"Вперед",Material.ARROW).buildItem();
        return item;
    }
    public ItemStack leftSiteItem(){
        ItemStack item = new ItemPattern(ChatColor.YELLOW+""+ChatColor.BOLD+"Назад",Material.ARROW).buildItem();
        return item;
    }
    public ItemStack mainStatisticItem(Player p){
        ItemStack item = new ItemPattern(ChatColor.AQUA+p.getName(),Material.PLAYER_HEAD).setSkullMeta(p)
                .lore(
                        ChatColor.WHITE+"lvl: "+plugin.data.getPlayerLvl(p),
                        ChatColor.WHITE+"exp: "+plugin.data.getPlayerXp(p),
                        ChatColor.GOLD+"__________________",
                        ChatColor.WHITE+"race: "+String.valueOf(plugin.data.getRace(p)).toLowerCase(),
                        ChatColor.WHITE+"job: "+String.valueOf(plugin.data.getJob(p)).toLowerCase()
                ).buildItem();
        return item;
    }
    public ItemStack jobStatisticItem(Player p,Job job){
        switch (job){
            case LUMBERJACK:
                if(plugin.data.getJobXp(p,job) != null)return new ItemPattern(ChatColor.GREEN+"Lumberjack",Material.DIAMOND_AXE)
                        .lore(
                                ChatColor.WHITE+"lvl: "+plugin.data.getJobLvl(p,job),
                                ChatColor.WHITE+"exp: "+plugin.data.getJobXp(p,job)
                        ).buildItem();
                else return new ItemPattern(ChatColor.RED+"Lumberjack",Material.DIAMOND_AXE)
                        .lore(
                                ChatColor.RED+"You don`t work on this job"
                        ).buildItem();
            case MINER:
                if(plugin.data.getJobXp(p,job) != null)return new ItemPattern(ChatColor.GREEN+"Miner",Material.DIAMOND_PICKAXE)
                        .lore(
                                ChatColor.WHITE+"lvl: "+plugin.data.getJobLvl(p,job),
                                ChatColor.WHITE+"exp: "+plugin.data.getJobXp(p,job)
                        ).buildItem();
                else return new ItemPattern(ChatColor.RED+"Miner",Material.DIAMOND_PICKAXE)
                        .lore(
                                ChatColor.RED+"You don`t work on this job"
                        ).buildItem();
            case FARMER:
                if(plugin.data.getJobXp(p,job) != null)return new ItemPattern(ChatColor.GREEN+"Farmer",Material.DIAMOND_HOE)
                        .lore(
                                ChatColor.WHITE+"lvl: "+plugin.data.getJobLvl(p,job),
                                ChatColor.WHITE+"exp: "+plugin.data.getJobXp(p,job)
                        ).buildItem();
                else return new ItemPattern(ChatColor.RED+"Farmer",Material.DIAMOND_HOE)
                        .lore(
                                ChatColor.RED+"You don`t work on this job"
                        ).buildItem();
            case BUTCHER:
                if(plugin.data.getJobXp(p,job) != null)return new ItemPattern(ChatColor.GREEN+"Butcher",Material.DIAMOND_SWORD)
                        .lore(
                                ChatColor.WHITE+"lvl: "+plugin.data.getJobLvl(p,job),
                                ChatColor.WHITE+"exp: "+plugin.data.getJobXp(p,job)
                        ).buildItem();
                else return new ItemPattern(ChatColor.RED+"Butcher",Material.DIAMOND_SWORD)
                        .lore(
                                ChatColor.RED+"You don`t work on this job"
                        ).buildItem();

        }
        return null;
    }

}


