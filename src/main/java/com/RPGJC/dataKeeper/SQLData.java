package com.RPGJC.dataKeeper;

import com.RPGJC.Main;

import java.sql.*;

public class SQLData {
    private Main plugin;
    public SQLData(Main plugin){
        this.plugin=plugin;
    }
    public boolean setInt(Integer id ,String tablename,String colname, Integer value){
        try {
            Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
            Statement s = conn.createStatement();
            s.executeUpdate(String.format("UPDATE %s SET %s=%d WHERE Id=%d;",tablename,colname,value,id));
            s.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean setString(Integer id, String tablename, String colname, String value){
        try {
            Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
            Statement s = conn.createStatement();
            s.executeUpdate(String.format("UPDATE %s SET %s='%s' WHERE Id=%d;",tablename,colname,value,id));
            s.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public Integer getInt(Integer id, String table, String col){
        Integer i = null;
        try {
            Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
            Statement s = conn.createStatement();
            ResultSet result = s.executeQuery(String.format("SELECT %s FROM %s WHERE Id=%d;",col,table,id));
            if(plugin.resultLength(result) == 1){
                result.next();
                i = result.getInt(1);
            }
            s.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return i;
    }
    public String getString(Integer id, String table, String col){
        String str=null;
        try {
            Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
            Statement s = conn.createStatement();
            ResultSet result = s.executeQuery(String.format("SELECT %s FROM %s WHERE Id=%d;",col,table,id));
            if(plugin.resultLength(result) == 1){
                result.next();
                str = result.getString(1);
            }
            s.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return str;
    }
    public Integer getIDByInt(String table,String col,int value){
        Integer id = null;
        try {
            Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
            Statement s = conn.createStatement();
            ResultSet result = s.executeQuery(String.format("SELECT Id FROM %s WHERE %s=%d;",table,col,value));
            if(plugin.resultLength(result) == 1){
                result.next();
                id = result.getInt(1);
            }
            s.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
    public Integer getIDByString(String table,String col, String value){
        Integer id = null;
        try {
            Connection conn = DriverManager.getConnection(plugin.url, plugin.user, plugin.password);
            Statement s = conn.createStatement();
            ResultSet result = s.executeQuery(String.format("SELECT Id FROM %s WHERE %s='%s';",table,col,value));
            if(plugin.resultLength(result) == 1){
                result.next();
                id = result.getInt(1);
            }
            s.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

}
