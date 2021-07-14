package com.RPGJC.dataKeeper;

import com.RPGJC.Main;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.HashMap;

public class Data extends SQLData{
    private Main plugin;
    private HashMap<Player,LevelXPData> playersData = new HashMap<>();
    private HashMap<Player,RaceType> playersRace = new HashMap<>();
    private HashMap<Player,Job> playersJob = new HashMap<>();
    private HashMap<PlayerJob,LevelXPData> playersJobDataMap = new HashMap<>();
    public Data(Main plugin){
        super(plugin);
        this.plugin=plugin;}
    public void addPlayer(Player p){
        try {
            Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
            Statement s = conn.createStatement();
            ResultSet result = s.executeQuery(String.format("SELECT * FROM player_data WHERE player='%s';",p.getName()));
            if(plugin.resultLength(result) == 1){
                result.next();
                LevelXPData data = new LevelXPData(result.getInt(3),result.getInt(4));
                playersData.put(p,data);
                playersRace.put(p,getRaceByString(result.getString(5)));
                playersJob.put(p,getJobByString(result.getString(6)));


            }else{
                s.executeUpdate(String.format("INSERT player_data(player,level,experience,race,current_job) VALUES('%s',1,0,'None','None')",p.getName()));
                playersData.put(p,new LevelXPData(1,0));
                playersRace.put(p,RaceType.NONE);
                playersJob.put(p,Job.NONE);
                p.setExp(0);
                p.setLevel(playersData.get(p).getLvl());
            }
            s.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void deletePlayer(Player p){
        for(Job job: Job.values()){
            PlayerJob pj = new PlayerJob(p,job);
            if(playersJobDataMap.containsKey(pj)) playersJobDataMap.remove(pj);
        }
        if(playersData.containsKey(p))playersData.remove(p);
        if(playersJob.containsKey(p))playersJob.remove(p);
        if(playersRace.containsKey(p))playersRace.remove(p);
    }
    public void setRace(Player p,RaceType raceType){
        Integer id = getPlayerId(p);
        setString(id,"player_data","race",getStringByRace(raceType));
        if(playersRace.containsKey(p))playersRace.replace(p,raceType);
        else playersRace.put(p,raceType);
    }
    public RaceType getRace(Player p){
        if(playersRace.containsKey(p))return playersRace.get(p);
        return null;
    }
    public void setPlayerLevel(Player p,Integer lvl){
        String table="player_data";
        setInt(getIDByString(table,"player",p.getName()),table,"level",lvl);
        if(playersData.containsKey(p))playersData.get(p).setLvl(lvl);
        else playersData.put(p,new LevelXPData(lvl,0));
    }
    public void setPlayerXp(Player p, Integer xp){
        String table="player_data";
        setInt(getIDByString(table,"player",p.getName()),table,"experience",xp);
        if(playersData.containsKey(p))playersData.get(p).setXp(xp);
        else playersData.put(p,new LevelXPData(xp,0));
    }
    public Integer getPlayerLvl(Player p){
        if(playersData.containsKey(p))return playersData.get(p).getLvl();
        return null;
    }
    public Integer getPlayerXp(Player p){
        if(playersData.containsKey(p))return playersData.get(p).getXp();
        return null;
    }
    public void setJob(Player p, Job job){
        String table = "player_data";
        setString(getIDByString(table,"player",p.getName()),table,"current_job",getStringByJob(job));
        if(playersJob.containsKey(p))playersJob.replace(p,job);
        else playersJob.put(p,job);
    }
    public Job getJob(Player p){
        if(playersJob.containsKey(p))return playersJob.get(p);
        return null;
    }
    public Integer getJobLvl(Player p, Job job){
        PlayerJob  pj= new PlayerJob(p,job);
        if(playersJobDataMap.containsKey(pj))return playersJobDataMap.get(pj).getLvl();
        return null;
    }
    public Integer getJobXp(Player p, Job job){
        PlayerJob  pj= new PlayerJob(p,job);
        if(playersJobDataMap.containsKey(pj))return playersJobDataMap.get(pj).getXp();
        return null;
    }
    public void setJobLvl(Player p, Job job, Integer lvl){
        Integer jobID = getJobId(p,job);
        setInt(jobID,"jobs_data","level",lvl);
    }
    public void setJobXp(Player p, Job job, Integer xp){
        Integer jobID = getJobId(p,job);
        setInt(jobID,"jobs_data","experience",xp);
    }

    private Integer getPlayerId(Player p){
        Integer i = null;
        i = getIDByString("player_data","player",p.getName());
        return i;
    }
    private Integer getJobId(Player p, Job job){
        Integer playerID = getPlayerId(p);
        Integer jobID = null;
        try {
            Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
            Statement s = conn.createStatement();
            ResultSet resultSet = s.executeQuery(String.format("SELECT * FROM jobs_data WHERE playerID=%d;"));
            while (resultSet.next()){
                String jobString = resultSet.getString(3);
                if(getJobByString(jobString).equals(job)){
                    jobID = resultSet.getInt(1);
                }
            }
            s.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jobID;
    }
    private String getStringByJob(Job job){
        switch (job){
            case LUMBERJACK:
                return "Lumberjack";
            case MINER:
                return "Miner";
            case FARMER:
                return "Farmer";
            case BUTCHER:
                return "Butcher";
            case NONE:
                return "None";
        }
        return null;
    }
    private Job getJobByString(String s){
        switch (s){
            case "Lumberjack":
                return Job.LUMBERJACK;
            case "Miner":
                return Job.MINER;
            case "Farmer":
                return Job.FARMER;
            case "Butcher":
                return Job.BUTCHER;
            case "None":
                return Job.NONE;
        }
        return null;
    }
    private String getStringByRace(RaceType race){
        switch (race){
            case HUMAN:
                return "Human";
            case MAGE:
                return "Mage";
            case ZOMO:
                return "Zomo";
            case CRIGANE:
                return "Crigane";
            case NONE:
                return "None";
        }
        return null;
    }
    private RaceType getRaceByString(String s){
        switch (s){
            case "Human":
                return RaceType.HUMAN;
            case "Mage":
                return RaceType.MAGE;
            case "Zomo":
                return RaceType.ZOMO;
            case "Crigane":
                return RaceType.CRIGANE;
            case "None":
                return RaceType.NONE;
        }
        return null;
    }

}
class PlayerJob{
    private Player p;
    private Job job;
    public PlayerJob(Player p, Job job){
        this.p=p;
        this.job=job;
    }
    public Player getPlayer(){
        return p;
    }
    public Job getJob(){
        return job;
    }
}
class LevelXPData{
    private Integer xp;
    private Integer lvl;
    public LevelXPData(Integer lvl, Integer xp){
        this.lvl=lvl;
        this.xp=xp;
    }

    public Integer getLvl() {
        return lvl;
    }

    public Integer getXp() {
        return xp;
    }
    public void setLvl(Integer lvl){
        this.lvl=lvl;
    }
    public void setXp(Integer xp){
        this.xp=xp;
    }
}
