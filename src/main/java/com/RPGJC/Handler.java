package com.RPGJC;

import com.RPGJC.craft.CraftItems;
import com.RPGJC.dataKeeper.RaceType;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.sql.*;

public class Handler implements Listener {
    private Main plugin;
    public Handler(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onExpSpawn(EntitySpawnEvent e){
        if(e.getEntity() instanceof ExperienceOrb){e.setCancelled(true);}
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        try {
            plugin.data.addPlayer(e.getPlayer());

        } catch (Exception exception) {
            exception.printStackTrace();
            e.getPlayer().kickPlayer("SQL error! Please rejoin");
        }
        Player p = e.getPlayer();
        plugin.sb.onJoin(p);


    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        plugin.sb.onLeave(e.getPlayer());
        plugin.data.deletePlayer(e.getPlayer());
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){
        if(CitizensAPI.getNPCRegistry().isNPC(e.getRightClicked()) && e.getHand().equals(EquipmentSlot.HAND)){
            if(CitizensAPI.getNPCRegistry().getNPC(e.getRightClicked()).getName().equals("Choose your race!")){
                Connection connection = null;
                String race = null;
                try {
                    connection = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
                    Statement s = connection.createStatement();
                    ResultSet resultSet = s.executeQuery(String.format("SELECT * FROM player_data WHERE player='%s'",e.getPlayer().getName()));
                    resultSet.next();
                    race = resultSet.getString(5);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    plugin.getLogger().severe("Error! Failed to connect or use DB");
                }
                if(race.equals("None")){
                    plugin.getLogger().info("Inventory opens!");
                    Player p = e.getPlayer();
                    p.openInventory(plugin.minecraftInventories.get("RaceInventory"));
                }else{
                    e.getPlayer().sendMessage("You have race!");
                }


            }
        }

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        plugin.inventoryChecks.onRaceItemsClick(e);
        plugin.inventoryChecks.onMainMenuClick(e);
        plugin.inventoryChecks.onJobMenuClick(e);
        plugin.inventoryChecks.onCraftMenuClick(e);
        plugin.inventoryChecks.onRecipeInventoryClick(e);
    }
    @EventHandler
    public void prepareCraft(PrepareItemCraftEvent e){

        Player p = (Player) e.getViewers().get(0);
        if(e.getInventory().getResult() != null && e.getInventory().getMatrix().length == 9){
            CraftItems craftItem =plugin.craft.getCraftItemStack().getCraftItem(e.getRecipe().getResult());
            if(craftItem != null){

                ItemStack[] recipeItems = plugin.craft.getCraftItemStack().getCraftInfo(craftItem).getRecipe().getIngredients();
                ItemStack[] matrix = e.getInventory().getMatrix();
                Boolean pass = true;
                plugin.getLogger().info(String.valueOf(pass));
                for(int i =0;i<9;i++){
                    if(recipeItems[i] != null && matrix[i] != null){
                        if(!recipeItems[i].isSimilar(matrix[i])){
                            pass = false;
                            break;
                        }
                    }else{
                        if(!(recipeItems[i] == null && matrix[i] == null)){
                            pass = false;
                            break;
                        }
                    }
                }
                boolean isCraftRace = false;
                Integer minlvl = plugin.craft.getCraftInfo(craftItem).getMinLvl();
                for(RaceType race: plugin.craft.getCraftInfo(craftItem).getRaceTypes()){
                    if(race.equals(plugin.data.getRace(p))){
                        isCraftRace = true;
                        break;
                    }
                }
                if(isCraftRace){
                    RaceType race = plugin.data.getRace(p);
                    if(race == RaceType.CRIGANE){if(minlvl > plugin.data.getPlayerLvl(p)+2) pass=false;}
                    else if(race == RaceType.ZOMO){if(minlvl > plugin.data.getPlayerLvl(p)-2)pass=false;}
                    else if(minlvl > plugin.data.getPlayerLvl(p))pass=false;
                }
                else pass=false;
                if(pass){
                    e.getInventory().setResult(plugin.craft.getCraftItemStack().getItem(craftItem,plugin.data.getPlayerLvl(p)));
                }else{
                    e.getInventory().setResult(null);
                }
            }

        }


    }
    @EventHandler
    public void onInventoryExit(InventoryCloseEvent e){
        if(!plugin.menu.getTransition((Player) e.getPlayer()))plugin.menu.deletePlayer((Player) e.getPlayer());
    }
}
