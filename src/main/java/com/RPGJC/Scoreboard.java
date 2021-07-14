package com.RPGJC;

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
        String race = null;
        switch (plugin.data.getRace(p)){
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
        objective.getScore(ChatColor.GOLD+"Уровень: "+ ChatColor.WHITE+plugin.data.getPlayerLvl(p)).setScore(3);
        objective.getScore(ChatColor.GOLD+"Опыт: "+ ChatColor.WHITE+plugin.data.getPlayerXp(p)).setScore(2);
        objective.getScore(ChatColor.GREEN +"Раса: "+ChatColor.WHITE+race).setScore(1);

        p.setScoreboard(board);
        plugin.getLogger().info("passed scoreboard");
    }
    public void onLeave(Player p){
        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective("stats");
        if(objective != null)objective.unregister();
    }
    public void updateBoard(Player p){
        String race = null;
        switch (plugin.data.getRace(p)){
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

        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();

        Objective objective = board.getObjective("stats");
        for(String e: board.getEntries()){
            board.resetScores(e);
        }
        objective.getScore(ChatColor.GOLD+"Уровень: "+ ChatColor.WHITE+plugin.data.getPlayerLvl(p)).setScore(3);
        objective.getScore(ChatColor.GOLD+"Опыт: "+ ChatColor.WHITE+plugin.data.getPlayerXp(p)).setScore(2);
        objective.getScore(ChatColor.GREEN +"Раса: "+ChatColor.WHITE+race).setScore(1);
        //return info
        //p.setScoreboard(board);
    }

}
