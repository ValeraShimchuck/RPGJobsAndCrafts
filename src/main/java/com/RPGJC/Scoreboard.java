package com.RPGJC;

import com.RPGJC.dataKeeper.Job;
import com.RPGJC.dataKeeper.RaceType;
import net.citizensnpcs.npc.ai.speech.Chat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

public class Scoreboard {
    private Main plugin;
    public Scoreboard(Main plugin) {
        this.plugin = plugin;
    }

    public void onJoin(Player p){
        org.bukkit.scoreboard.Scoreboard board = plugin.manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("stats","dummy");
        if(objective == null)plugin.getLogger().info("objective is null");
        objective.setDisplayName("Информация");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        String race = getRaceName(plugin.data.getRace(p));
        String job = getJobName(plugin.data.getJob(p));


        objective.getScore(ChatColor.GREEN +"Раса: "+ChatColor.WHITE+race).setScore(6);
        objective.getScore(ChatColor.GOLD+"Уровень: "+ ChatColor.WHITE+plugin.data.getPlayerLvl(p)).setScore(5);
        objective.getScore(ChatColor.GOLD+"Опыт: "+ ChatColor.WHITE+plugin.data.getPlayerXp(p)).setScore(4);
        objective.getScore(ChatColor.AQUA+"Работа: "+ChatColor.WHITE+job).setScore(3);
        objective.getScore(ChatColor.GOLD+"Уровень работы: "+ChatColor.WHITE+getJobLevel(p)).setScore(2);
        objective.getScore(ChatColor.GOLD+"Опыт работы: "+ChatColor.WHITE+getJobXp(p)).setScore(1);

        p.setScoreboard(board);
    }
    public void onLeave(Player p){
        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective("stats");
        if(objective != null)objective.unregister();
    }
    public void updateBoard(Player p){
        String race = getRaceName(plugin.data.getRace(p));
        String job = getJobName(plugin.data.getJob(p));

        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();

        Objective objective = board.getObjective("stats");
        for(String e: board.getEntries()){
            board.resetScores(e);
        }
        objective.getScore(ChatColor.GREEN +"Раса: "+ChatColor.WHITE+race).setScore(6);
        objective.getScore(ChatColor.GOLD+"Уровень: "+ ChatColor.WHITE+plugin.data.getPlayerLvl(p)).setScore(5);
        objective.getScore(ChatColor.GOLD+"Опыт: "+ ChatColor.WHITE+plugin.data.getPlayerXp(p)).setScore(4);
        objective.getScore(ChatColor.AQUA+"Работа: "+ChatColor.WHITE+job).setScore(3);
        objective.getScore(ChatColor.GOLD+"Уровень работы: "+ChatColor.WHITE+getJobLevel(p)).setScore(2);
        objective.getScore(ChatColor.GOLD+"Опыт работы: "+ChatColor.WHITE+getJobXp(p)).setScore(1);
        //return info
        //p.setScoreboard(board);
    }
    private String getRaceName(RaceType raceType){
        String race = null;
        switch (raceType){
            case HUMAN:
                race="Человек";
                break;
            case MAGE:
                race="Маг";
                break;
            case ZOMO:
                race = "Зомо";
                break;
            case CRIGANE:
                race = "Криган";
                break;
            case NONE:
                race = "Не выбрана";
                break;
        }
        if(race == null) race = "Error";
        return race;
    }
    private String getJobName(Job job){
        String jobString = null;
        switch (job){
            case LUMBERJACK:
                jobString = "Лесоруб";
                break;
            case MINER:
                jobString = "Шахтер";
                break;
            case FARMER:
                jobString = "Фермер";
                break;
            case BUTCHER:
                jobString = "Мясник";
                break;
            case NONE:
                jobString = "Не выбрана";
                break;
        }
        return jobString;
    }
    private String getJobLevel(Player p){
        Job job = plugin.data.getJob(p);
        Integer lvl = plugin.data.getJobLvl(p,job);
        if(lvl == null) return "Нету";
        return String.valueOf(lvl);
    }
    private String getJobXp(Player p){
        Job job = plugin.data.getJob(p);
        Integer xp = plugin.data.getJobXp(p,job);
        if(xp == null) return "Нету";
        return String.valueOf(xp);
    }

}
