package com.RPGJC;

//import net.minecraft.server.v1_13_R2.*;
import com.RPGJC.menu.Menu;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
        import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {
    public Menu menu;
    public NamespacedKey key = new NamespacedKey(this,"glow");
    public static Glow glow;
    public ScoreboardManager manager;
    public Items items;
    public Inventories inventories;
    public InventoryChecks inventoryChecks;
    public com.RPGJC.Scoreboard sb;
    public String url;
    public String user;
    public String password;
    public HashMap<Player,String> race = new HashMap<>();
    public List<EntityPlayer> npcs = new ArrayList<>();
    public HashMap<String, Inventory> minecraftInventories = new HashMap<>();
    public HashMap<Player, Integer> playersLvl = new HashMap<>();
    public HashMap<Player, Integer> playersXP = new HashMap<>();
    @Override
    public void onEnable() {
        registerGlow();
        File config = new File(getDataFolder(), "config.yml");
        if(!config.exists()){
            getLogger().info("creating cfg file & stop plugin");
            getLogger().severe("Please check new config file & rerun server");
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            getLogger().info("SQL connection successful!");
            try {
                url = this.getConfig().getString("url");
                user = this.getConfig().getString("user");
                password = this.getConfig().getString("password");
                Connection connection = DriverManager.getConnection(url,user,password);
                Statement s = connection.createStatement();
                s.executeUpdate("CREATE TABLE IF NOT EXISTS player_data (Id INT PRIMARY KEY AUTO_INCREMENT, player TEXT, level TINYINT UNSIGNED, experience INT UNSIGNED, race TEXT);");
                s.executeUpdate("CREATE TABLE IF NOT EXISTS jobs_data (Id INT PRIMARY KEY AUTO_INCREMENT, playerID INT, job TEXT, level TINYINT UNSIGNED, experience INT UNSIGNED)");
                s.executeUpdate("CREATE TABLE IF NOT EXISTS npc_data (Id INT PRIMARY KEY AUTO_INCREMENT, name TINYTEXT,world TINYTEXT, x DOUBLE, y DOUBLE, z DOUBLE, pitch FLOAT, yaw FLOAT,fun TINYTEXT)");
                //spawnNPCs(s);

                s.close();
                connection.close();
                getLogger().info("SQL connection to store DB successful");
                Bukkit.getPluginManager().registerEvents(new Handler(this), this);
                //CitizensAPI.registerEvents(new Handler(this));
                getCommand("createracenpc").setExecutor(new Commands(this));
                getCommand("createnpc").setExecutor(new Commands(this));
                getCommand("resetrace").setExecutor(new Commands(this));
                getCommand("menu").setExecutor(new Commands(this));
                items = new Items(this);
                inventories = new Inventories(this);
                inventoryChecks = new InventoryChecks(this);
                minecraftInventories.put("RaceInventory",inventories.RaceInventory());
                manager = Bukkit.getScoreboardManager();
                sb = new com.RPGJC.Scoreboard(this);
                menu = new Menu(this);
            } catch (SQLException throwables) {
                getLogger().info("SQL don`t connected to DB");
                getLogger().info(String.valueOf(throwables));
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
        } catch (ClassNotFoundException e) {
            getLogger().info("SQL not found!");
            Bukkit.getPluginManager().disablePlugin(this);
        } catch (InstantiationException e) {
            getLogger().info("InstantiationExcept");
            Bukkit.getPluginManager().disablePlugin(this);
        } catch (IllegalAccessException e) {
            getLogger().info("IllegalAccessException");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
    public static int getResultSetRowCount(ResultSet resultSet) {
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
            resultSet.beforeFirst();
        }
        catch(SQLException ex) {
            return 0;
        }
        return size;
    }
    public void addNPCPacket(EntityPlayer npc, Player player){
        //PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        //connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        //connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        //connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc,(byte)(npc.yaw * 256 / 360)));
    }
    public void registerGlow(){
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{

            glow = new Glow(key);
            Enchantment.registerEnchantment(glow);
        }catch (IllegalArgumentException e){
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
