/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Packages;

import DbConnection.SQLConnection;
import java.util.List;
import Packages.Package;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class PackageInf {

    public static List<Package> getAllPackages() {
        List<Package> list = new ArrayList<>();
        String sql = "Select * from PACKAGE";
        try (
                 Connection connection = SQLConnection.conn;  PreparedStatement pstmt = connection.prepareStatement(sql);) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Package pk = new Package(rs.getString("ID"), rs.getString("NAME"), rs.getInt("LIMITNUM"),
                        rs.getDate("LIMITTIME"), rs.getFloat("PRICE"), rs.getInt("QUANTITY"));
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

    public static boolean addPackages(Package pk) {
        String sql = "Insert into PACKAGE (ID, NAME, LIMITNUM, LIMITTIME, PRICE, QUANTITY)"
                + "VALUES (?,?,?,?,?,?)";
        try (
                 Connection connection = SQLConnection.conn;  PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, pk.getpID());
            pstmt.setString(2, pk.getpName());
            pstmt.setInt(3, pk.getLimitNum());
            pstmt.setDate(4, new java.sql.Date(pk.getLimitTime().getTime()));
            pstmt.setFloat(5, pk.getPrice());
            pstmt.setInt(6, pk.getQuantity());

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Can't add packages to database");
//            e.printStackTrace();
        }
        return false;
    }

    public static boolean removePackages(Package pk) {
        String sql = "Delete from PACKAGE where ID=? and NAME=? and LIMITNUM=? and"
                + " LIMITTIME = ? and PRICE =? and QUANTITY=?";
        try (
                 Connection connection = SQLConnection.conn;  PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, pk.getpID());
            pstmt.setString(2, pk.getpName());
            pstmt.setInt(3, pk.getLimitNum());
            pstmt.setDate(4, new java.sql.Date(pk.getLimitTime().getTime()));
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
                 Connection connection = SQLConnection.conn;  PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't remove package from database");
//            e.printStackTrace();
        }
        return false;
    }

    public static boolean updatePackages(Package pk) {
        String sql = "Update PACKAGE set NAME=?, LIMITNUM=?, "
                + " LIMITTIME = ?, PRICE =?, QUANTITY=? where ID=?"
                + "GO";
        try (
                 Connection connection = SQLConnection.conn;  PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, pk.getpName());
            pstmt.setInt(2, pk.getLimitNum());
            pstmt.setDate(3, new java.sql.Date(pk.getLimitTime().getTime()));
            pstmt.setFloat(4, pk.getPrice());
            pstmt.setInt(5, pk.getQuantity());
            pstmt.setString(6, pk.getpID());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't update package to database");
//            e.printStackTrace();
        }
        return false;
    }

    public static List<Object> statisticsPackages() {
        List<Object> list = new ArrayList<>();
        String sql = "select p.ID,p.NAME, PRICE, PKR.REGISTER, (PRICE*PKR.REGISTER) as TOTAL from PACKAGE p "
                + "join (select PACKAGEID, sum(QUANTITY) as REGISTER from PACKAGEREGISTER "
                + "group by PACKAGEID)  as PKR on pkr.PACKAGEID = p.ID";
        try (
                 Connection connection = SQLConnection.conn;  PreparedStatement pstmt = connection.prepareStatement(sql);) {

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

}
