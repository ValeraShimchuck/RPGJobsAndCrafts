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

        objective.setDisplayName("Информация");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        String race = null;
        if(plugin.race.get(p).equals("Human")) race = "Человек";
        if(plugin.race.get(p).equals("Mage")) race = "Маг";
        if(plugin.race.get(p).equals("Zomo")) race = "Зомо";
        if(plugin.race.get(p).equals("Crigane")) race = "Криган";
        if(plugin.race.get(p).equals("None")) race = "Не выбрана";
        if(race == null) race = "Error";
        objective.getScore(ChatColor.GOLD+"Уровень: "+ ChatColor.WHITE+plugin.playersLvl.get(p)).setScore(3);
        objective.getScore(ChatColor.GOLD+"Опыт: "+ ChatColor.WHITE+plugin.playersXP.get(p)).setScore(2);
        objective.getScore(ChatColor.GREEN +"Раса: "+ChatColor.WHITE+race).setScore(1);

        p.setScoreboard(board);
    }
    public void onLeave(Player p){
        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective("stats");
        objective.unregister();
    }
    public void updateBoard(Player p){
        String race = null;
        if(plugin.race.get(p).equals("Human")) race = "Человек";
        if(plugin.race.get(p).equals("Mage")) race = "Маг";
        if(plugin.race.get(p).equals("Zomo")) race = "Зомо";
        if(plugin.race.get(p).equals("Crigane")) race = "Криган";
        if(plugin.race.get(p).equals("None")) race = "Не выбрана";
        if(race == null) race = "Error";

        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();

        Objective objective = board.getObjective("stats");
        for(String e: board.getEntries()){
            board.resetScores(e);
        }
        objective.getScore(ChatColor.GOLD+"Уровень: "+ ChatColor.WHITE+plugin.playersLvl.get(p)).setScore(3);
        objective.getScore(ChatColor.GOLD+"Опыт: "+ ChatColor.WHITE+plugin.playersXP.get(p)).setScore(2);
        objective.getScore(ChatColor.GREEN +"Раса: "+ChatColor.WHITE+race).setScore(1);
        //return info
        //p.setScoreboard(board);
    }

}
