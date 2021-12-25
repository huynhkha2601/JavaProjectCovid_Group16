/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManagedHistory;

import DbConnection.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ManagedHistoryInf {
    
    public static List<ManagedHistory> getAllManagedHistorys() {
        List<ManagedHistory> list = new ArrayList<>();
        String sql = "Select * from MANAGEHISTORY";
        try (
                 Connection connection = SQLConnection.getConnection();  
                PreparedStatement pstmt = connection.prepareStatement(sql);) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ManagedHistory mh = new ManagedHistory(rs.getString("ID"), rs.getString("FROMSTATUS"), rs.getString("TOSTATUS"),
                rs.getTimestamp("RECORD").toLocalDateTime());
                list.add(mh);
//                System.out.println(mh.toString());
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Can't get all managed history!");
//            e.printStackTrace();
        }
        return null;
    }

    public static boolean addManagedHistory(ManagedHistory mh) {
        String sql = "Insert into MANAGEHISTORY (ID, FROMSTATUS, TOSTATUS, RECORD)"
                + "VALUES (?,?,?,?)";
        try (
                Connection connection = SQLConnection.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, mh.getmID());
            pstmt.setString(2, mh.getFromStatus());
            pstmt.setString(3, mh.getToStatus());
            pstmt.setTimestamp(4, Timestamp.valueOf(mh.getRecord()));

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Can't add managed history record to database");
//            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeManagedHistory(ManagedHistory mh) {
        String sql = "Delete from MANAGEHISTORY where ID=? and FROMSTATUS=? and TOSTATUS=? and"
                + " RECORD = ?";
        try (
                Connection connection = SQLConnection.getConnection();PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, mh.getmID());
            pstmt.setString(2, mh.getFromStatus());
            pstmt.setString(3, mh.getToStatus());
            pstmt.setTimestamp(4, Timestamp.valueOf(mh.getRecord()));

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't remove manage history from database");
//            e.printStackTrace();
        }
        return false;
    }
    
        public static boolean removeManagedHistory(String id) {
        String sql = "Delete from MANAGEHISTORY where ID=?";
        try (
                Connection connection = SQLConnection.getConnection();PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, id);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't remove all manage history of specific patient from database");
//            e.printStackTrace();
        }
        return false;
    }

    
    public static boolean updateManagedHistory(ManagedHistory mh) {
        String sql = "Update MANAGEHISTORY set FROMSTATUS=?, TOSTATUS=? where ID=? and RECORD=?";
        try (
                Connection connection = SQLConnection.getConnection();PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setString(1, mh.getFromStatus());
            pstmt.setString(2, mh.getToStatus());
            pstmt.setString(3, mh.getmID());
            pstmt.setTimestamp(4, Timestamp.valueOf(mh.getRecord()));

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't update manage history to database");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateManagedHistory(List<ManagedHistory> lst) {
        String sql = "Update MANAGEHISTORY set FROMSTATUS=?, TOSTATUS=? where ID=? and RECORD=?";
        try (
                Connection connection = SQLConnection.getConnection();PreparedStatement pstmt = connection.prepareStatement(sql);) {
            for (ManagedHistory managedHistory : lst) {
                pstmt.setString(1, managedHistory.getFromStatus());
                pstmt.setString(2, managedHistory.getToStatus());
                pstmt.setString(3, managedHistory.getmID());
                pstmt.setTimestamp(4, Timestamp.valueOf(managedHistory.getRecord()));

                pstmt.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Can't update manage history to database");
            e.printStackTrace();
        }
        return false;
    }

    public static List<ManagedHistory> searchManageHistory(ManagedHistory mh) {
        String sql = "select * from MANAGEHISTORY where ID=? and FROMSTATUS=? and TOSTATUS=? and RECORD=?";
        try (
                Connection connection = SQLConnection.getConnection();PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, mh.getmID());
            pstmt.setString(2, mh.getFromStatus());
            pstmt.setString(3, mh.getToStatus());
            pstmt.setTimestamp(4, Timestamp.valueOf(mh.getRecord()));

            ResultSet rs = pstmt.executeQuery();
            List<ManagedHistory> lst = new ArrayList<>();
            if (rs.next()) {
                ManagedHistory obj = new ManagedHistory(rs.getString("ID"), rs.getString("FROMSTATUS"), rs.getString("TOSTATUS"),
                rs.getTimestamp("RECORD").toLocalDateTime());
//                System.out.println(LocalDateFormatter.parseToLocalDateTime(date));
//                System.out.println(mh.toString());
                lst.add(obj);
                return lst;
            }

        } catch (SQLException e) {
            System.out.println("Can't search specific manage history");
            e.printStackTrace();
        }
        return null;
    }

    public static List<ManagedHistory> searchManageHistoryByDate(String record) {
        List<ManagedHistory> list = new ArrayList<>();
        String sql = "select * from MANAGEHISTORY where RECORD LIKE %"+record+"%" ;
        try (
                Connection connection = SQLConnection.getConnection();PreparedStatement pstmt = connection.prepareStatement(sql);) {


            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ManagedHistory mh = new ManagedHistory(rs.getString("ID"), rs.getString("FROMSTATUS"), rs.getString("TOSTATUS"),
                rs.getTimestamp("RECORD").toLocalDateTime());
                list.add(mh);
//                System.out.println(mh.toString());
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Can't search manage history by date");
            e.printStackTrace();
        }
        return null;
    }

    public static List<ManagedHistory> searchManageHistory(String personID) {
        List<ManagedHistory> list = new ArrayList<>();
        String sql = "select * from MANAGEHISTORY where ID=?";
        try (
                Connection connection = SQLConnection.getConnection();PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, personID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ManagedHistory mh = new ManagedHistory(rs.getString("ID"), rs.getString("FROMSTATUS"), rs.getString("TOSTATUS"),
                rs.getTimestamp("RECORD").toLocalDateTime());
                list.add(mh);
//                System.out.println(mh.toString());
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Can't search manage history by id");
            e.printStackTrace();
        }
        return null;
    }

    public static List<ManagedHistory> filterManageHistory(LocalDateTime fromDate, LocalDateTime toDate) {
        List<ManagedHistory> list = new ArrayList<>();
        String sql = "select * from MANAGEHISTORY where RECORD between ? and ?";
        try (
                Connection connection = SQLConnection.getConnection();PreparedStatement pstmt = connection.prepareStatement(sql);) {
            
            pstmt.setTimestamp(1, Timestamp.valueOf(fromDate));
            pstmt.setTimestamp(2, Timestamp.valueOf(toDate));

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                 ManagedHistory mh = new ManagedHistory(rs.getString("ID"), rs.getString("FROMSTATUS"), rs.getString("TOSTATUS"),
                rs.getTimestamp("RECORD").toLocalDateTime());
                list.add(mh);
//                System.out.println(mh.toString());
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Can't filter manage history by id");
            e.printStackTrace();
        }
        return null;
    }
    
    
}
