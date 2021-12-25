/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Packages;

import DbConnection.SQLConnection;
import Helper.DateFormatter;
import java.util.List;
import Packages.Packages;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class PackageInf {

    public static List<Packages> getAllPackages() {
        List<Packages> list = new ArrayList<>();
        String sql = "Select * from PACKAGE ORDER BY REPLICATE(' ',6-LEN(ID)) + ID";
        try (
                Connection connection = SQLConnection.getConnection();  
                PreparedStatement pstmt = connection.prepareStatement(sql);) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Packages pk = new Packages(rs.getString("ID"), rs.getString("NAME"), rs.getInt("LIMITNUM"),
                        rs.getDate("LIMITTIME").toLocalDate(), rs.getFloat("PRICE"), rs.getInt("QUANTITY"));
//                System.out.println(pk.toString());
                list.add(pk);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Can't get all packages");
//            e.printStackTrace();
        }
        return null;
    }

    public static boolean addPackages(Packages pk) {
        String sql = "Insert into PACKAGE (ID, NAME, LIMITNUM, LIMITTIME, PRICE, QUANTITY)"
                + "VALUES (?,?,?,?,?,?)";
        try (
                 Connection connection = SQLConnection.getConnection();    
                PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, pk.getpID());
            pstmt.setString(2, pk.getpName());
            pstmt.setInt(3, pk.getLimitNum());
            pstmt.setDate(4, Date.valueOf(pk.getLimitTime()));
            pstmt.setFloat(5, pk.getPrice());
            pstmt.setInt(6, pk.getQuantity());
//            System.out.println("A");
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't add packages to database");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removePackages(Packages pk) {
        String sql = "Delete from PACKAGE where ID=? and NAME=? and LIMITNUM=? and"
                + " LIMITTIME = ? and PRICE =? and QUANTITY=?";
        try (
                 Connection connection = SQLConnection.getConnection(); 
                PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, pk.getpID());
            pstmt.setString(2, pk.getpName());
            pstmt.setInt(3, pk.getLimitNum());
            pstmt.setDate(4, java.sql.Date.valueOf(pk.getLimitTime()));
            pstmt.setFloat(5, pk.getPrice());
            pstmt.setInt(6, pk.getQuantity());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't remove package from database");
//            e.printStackTrace();
        }
        return false;
    }

    public static boolean removePackages(String id) {
        String sql = "Delete from PACKAGE where ID=?";
        try (
            
                Connection connection = SQLConnection.getConnection();    
                PreparedStatement pstmt = connection.prepareStatement(sql);) {
            
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't remove package from database");
//            e.printStackTrace();
        }
        return false;
    }

    public static boolean updatePackages(Packages pk) {
        String sql = "Update PACKAGE set NAME=?, LIMITNUM=?, "
                + " LIMITTIME = ?, PRICE =?, QUANTITY=? where ID=?";
        try (
                 Connection connection = SQLConnection.getConnection();    PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, pk.getpName());
            pstmt.setInt(2, pk.getLimitNum());
            pstmt.setDate(3, java.sql.Date.valueOf(pk.getLimitTime()));
            pstmt.setFloat(4, pk.getPrice());
            pstmt.setInt(5, pk.getQuantity());
            pstmt.setString(6, pk.getpID());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't update package to database");
            e.printStackTrace();
        }
        return false;
    }

    public static List<Object> statisticsPackages() {
        List<Object> list = new ArrayList<>();
        String sql = "select p.ID,p.NAME, PRICE, PKR.REGISTER, (PRICE*PKR.REGISTER) as TOTAL from PACKAGE p "
                + "join (select PACKAGEID, sum(QUANTITY) as REGISTER from PACKAGEREGISTER "
                + "group by PACKAGEID)  as PKR on pkr.PACKAGEID = p.ID ORDER BY REPLICATE(' ',6-LEN(p.ID)) + p.ID";
        try (
                 Connection connection = SQLConnection.getConnection();    PreparedStatement pstmt = connection.prepareStatement(sql);) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Object[] obj = {rs.getString("ID"), rs.getString("NAME"), rs.getFloat("PRICE"),
                    rs.getInt("REGISTER"), rs.getFloat("TOTAL")};
                list.add(obj);
//                System.out.println(obj[0] + " " + obj[1] + " " + obj[2] + " " + obj[3] + " " + obj[4]);
            }

            return list;
        } catch (SQLException e) {
            System.out.println("Can't update package to database");
//            e.printStackTrace();
        }
        return null;
    }

    public static boolean searchPackage(String ID) {
        String sql = "select * from PACKAGE where ID=? ORDER BY REPLICATE(' ',6-LEN(ID)) + ID";
        try (
                 Connection connection = SQLConnection.getConnection();    
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {

            pstmt.setString(1, ID);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Can't search package by id");
            e.printStackTrace();
        }
        return false;
    }
     
    public static List<Packages> searchPackageByName(String name){
        List<Packages> lst = new ArrayList<>();
        String sql = "select * from PACKAGE where NAME=? ORDER BY REPLICATE(' ',6-LEN(ID)) + ID";
        try (
                 Connection connection = SQLConnection.getConnection();    
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
   
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Packages pk = new Packages(rs.getString("ID"), rs.getString("NAME"), rs.getInt("LIMITNUM"),
                        rs.getDate("LIMITTIME").toLocalDate(), rs.getFloat("PRICE"), rs.getInt("QUANTITY"));
//                System.out.println(pk.toString());
                lst.add(pk);
            }
            return lst;
        } catch (SQLException e) {
            System.out.println("Can't search package by name");
            e.printStackTrace();
        }
        return null;
    }

    
    public static List<Packages> filterPackageByName(String name){
        List<Packages> lst = new ArrayList<>();
        String sql = "select * from PACKAGE where NAME like N'%" + name + "%' ORDER BY REPLICATE(' ',6-LEN(ID)) + ID";
        try (
                 Connection connection = SQLConnection.getConnection();    
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
    
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Packages pk = new Packages(rs.getString("ID"), rs.getString("NAME"), rs.getInt("LIMITNUM"),
                        rs.getDate("LIMITTIME").toLocalDate(), rs.getFloat("PRICE"), rs.getInt("QUANTITY"));
//                System.out.println(pk.toString());
                lst.add(pk);
            }
            return lst;
        } catch (SQLException e) {
            System.out.println("Can't filter package by name");
            e.printStackTrace();
        }
        return null;
    }
    
      public static String[] getAllIdPackages(){
        List<String> lst = new ArrayList<>();
        String sql = "select * from PACKAGE where LIMITTIME > GETDATE() ORDER BY REPLICATE(' ',6-LEN(ID)) + ID";
        try (
                 Connection connection = SQLConnection.getConnection();    
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
    
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String pk = rs.getString("ID") + " - "+rs.getString("NAME");
//                System.out.println(pk.toString());
                lst.add(pk);
            }
            return lst.stream().toArray(String[]::new);
        } catch (SQLException e) {
            System.out.println("Can't filter package by name");
//            e.printStackTrace();
        }
        return null;
    }
      
    public static String getQuantity(String psID,String pkID){
        String sql = "SELECT PERSONID, PACKAGEID, SUM(QUANTITY) AS SL FROM dbo.PACKAGEREGISTER" +
                        " WHERE PERSONID = ? AND PACKAGEID = ? GROUP BY PERSONID, PACKAGEID";
        String sql2 = "SELECT LIMITNUM,QUANTITY FROM PACKAGE WHERE dbo.PACKAGE.ID = ?";
        try (
                 Connection connection = SQLConnection.getConnection();    
                PreparedStatement pstmt = connection.prepareStatement(sql);
                PreparedStatement pstmt2 = connection.prepareStatement(sql2);
            ) {
    
            pstmt.setString(1, psID);
            pstmt.setString(2, pkID);
            pstmt2.setString(1, pkID);
            
            ResultSet rs = pstmt2.executeQuery();
            int limit = 0, amount = 0;
            
            if (rs.next()){
                limit = rs.getInt("LIMITNUM");
                amount = rs.getInt("QUANTITY");
            }
            
            ResultSet rs2 = pstmt.executeQuery();
            if (rs2.next()) {
                //                System.out.println(pk.toString());
                int quantity = limit - rs2.getInt("SL");
                if (quantity <= 0) 
                    return "0";
                else{
                    if (amount < quantity)
                        return Integer.toString(amount);
                    else 
                        return Integer.toString(quantity);
                }
                    
            }else{
                if (amount < limit)
                        return Integer.toString(amount);
                else
                    return Integer.toString(limit);
            }
        } catch (SQLException e) {
            System.out.println("Can't filter package by name");
            e.printStackTrace();
        }
        return "0";
    }
      
    public static boolean buyPackage(String psID, String pkID, int quantity){
        String sql = "INSERT INTO PACKAGEREGISTER VALUES(?,?,?,?)";
        try (
                 Connection connection = SQLConnection.getConnection();    
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ) {
    
            pstmt.setString(1, psID);
            pstmt.setString(2, pkID);
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(4, quantity);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't add register package");
            e.printStackTrace();
        }
        return false;
    }
}
