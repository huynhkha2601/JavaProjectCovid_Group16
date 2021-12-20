/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

/**
 *
 * @author ACER
 */


/*import needed package*/
import Account.*;
import Record.ActivityHistory;
import Record.TransactionHistory;
import TreatmentPlace.TreatmentPlace;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Admin {
    /*Variables for make connection*/
    static Connection con = null;
    static PreparedStatement pstm = null;
    
    
    
    
    /*Make connection function*/
    public static Connection Make_connection(){
        Connection conn = null;
        String DB_name = "Covid-19";
        String DB_URL = ("jdbc:sqlserver://localhost:1433;"
                               + "databaseName="+ DB_name);
        try{
            //Load JDBC driver:
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //Open a connection to database:
            conn = DriverManager.getConnection(
                    DB_URL + ";username=sa;password=SV19clc7CnTt");
        }
        catch (ClassNotFoundException clsNF){
            System.out.println("Error: unable to load driver class.");
            JOptionPane.showMessageDialog(null, clsNF, 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
            conn = null;
        }
        catch (SQLException sqlex){
            System.out.println("Error: Cannot open a connection to database: "
                    + DB_name);
            JOptionPane.showMessageDialog(null, sqlex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            conn = null;
        }
        return conn;
    }
    
    
    
    
    /*Functions to execute the query*/
    /*Account table*/
    /*Get data with condition (get all if there is no condition)*/
    public static ArrayList<Account> getAccounts(String condition){
        ArrayList<Account> listAcc = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from ACCOUNT ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        Account acc = new Account();
                        LocalDateTime ldt = LocalDateTime.parse(
                                            rs.getString("DATEPUBLISHED")
                                                    .replace(' ', 'T'));
                        acc.setAccount(rs.getString("USERNAME"), 
                                        rs.getString("PASSWORD"), 
                                        rs.getString("ROLE"), 
                                        rs.getString("USERID"), 
                                        rs.getInt("ACTIVATED"),
                                        ldt);
                        listAcc.add(acc);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listAcc;
    }
    
    
    /*Insert data into ACCOUNT table*/
    public static boolean addAccount(Account acc){
        int result;
        boolean returnrs = true;
        con = Make_connection();
        if (con != null){
            try {
                String query = "insert into ACCOUNT(USERNAME, PASSWORD, ROLE, "
                            + "USERID, ACTIVATED, DATEPUBLISHED) "
                            + "VALUES(?, ?, ?, ?, ?, ?)";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, acc.getUsername());
                pstm.setString(2, acc.getPassword());
                pstm.setString(3, acc.getRole());
                pstm.setString(4, acc.getUserid());
                pstm.setInt(5, acc.getState());
                ZonedDateTime zdt = acc.getDatePublished().atZone(ZoneId.of("GMT+07:00:00"));
                pstm.setTimestamp(6, new Timestamp(zdt.toInstant().toEpochMilli()));
                result = pstm.executeUpdate();
                if (result < 0){
                    JOptionPane.showMessageDialog(null, "Failed to insert data", 
                                         "Insert Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    returnrs = false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while adding data.");
                System.out.println("Error: " + ex);
                returnrs = false;
            } finally{
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return returnrs;
    }
    
    
    /*Update data in ACCOUNT table*/
    public static boolean editAccount(Account acc){
        boolean returnrs = true;
        int result;
        con = Make_connection();
        if (con != null){
            try {
                String query = "update ACCOUNT set PASSWORD=?, "
                            + "ROLE=?, USERID=?, ACTIVATED=? "
                            + "where USERNAME=?";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, acc.getPassword());
                pstm.setString(2, acc.getRole());
                pstm.setString(3, acc.getUserid());
                pstm.setInt(4, acc.getState());
                pstm.setString(5, acc.getUsername());
                result = pstm.executeUpdate();
                if (result < 0){
                    JOptionPane.showMessageDialog(null, "Failed to update data", 
                                         "Insert Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    returnrs = false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while updating data.");
                System.out.println("Error: " + ex);
                returnrs = false;
            } finally{
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return returnrs;
    }
    
    
    /*Remove data in ACCOUNT table*/
    public static boolean removeAccount(String Username){
        boolean returnrs = true;
        int result;
        con = Make_connection();
        if (con != null){
            try {
                String query = "delete from ACCOUNT where USERNAME=?";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, Username);
                result = pstm.executeUpdate();
                if (result < 0){
                    JOptionPane.showMessageDialog(null, "Failed to remove data", 
                                         "Insert Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    returnrs = false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while removing data.");
                System.out.println("Error: " + ex);
                returnrs = false;
            } finally{
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return returnrs;
    }
    
    
    
    /*Account bank table*/
    /*Get data with condition (get all if there is no condition)*/
    public static ArrayList<AccountBank> getAccountsBank(String condition){
        ArrayList<AccountBank> listAccBnk = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from Account_Bank ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        AccountBank accbnk = new AccountBank();
                        LocalDateTime ldt = LocalDateTime.parse(
                                            rs.getString("DATEPUBLISHED")
                                                    .replace(' ', 'T'));
                        accbnk.setAccountBank(rs.getString("ID"), 
                                        rs.getString("PASSWORD"), 
                                        rs.getString("ROLE"),
                                        rs.getInt("Active"),
                                        rs.getFloat("Balance"),
                                        rs.getString("USERID"), 
                                        ldt);
                        listAccBnk.add(accbnk);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listAccBnk;
    }
    
    
    /*Insert data into Account_Bank table*/
    public static boolean addAccountBank(AccountBank accbank){
        int result;
        boolean returnrs = true;
        con = Make_connection();
        if (con != null){
            try {
                String query = "insert into Account_Bank(ID, PASSWORD, ROLE, "
                            + "ACTIVE, Balance, UserID, DATEPUBLISHED) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?)";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, accbank.getBankid());
                pstm.setString(2, accbank.getPassword());
                pstm.setString(3, accbank.getRole());
                pstm.setInt(4, accbank.getState());
                pstm.setFloat(5, accbank.getBalance());
                pstm.setString(6, accbank.getUserid());
                ZonedDateTime zdt = accbank.getDatePublished().atZone(ZoneId.of("GMT+07:00:00"));
                pstm.setTimestamp(7, new Timestamp(zdt.toInstant().toEpochMilli()));
                result = pstm.executeUpdate();
                if (result < 0){
                    JOptionPane.showMessageDialog(null, "Failed to insert data", 
                                         "Insert Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    returnrs = false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while adding data.");
                System.out.println("Error: " + ex);
                returnrs = false;
            } finally{
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return returnrs;
    }
    
    
    /*Update data in Account_Bank table*/
    public static boolean editAccountBank(AccountBank accbank){
        boolean returnrs = true;
        int result;
        con = Make_connection();
        if (con != null){
            try {
                String query = "update Account_Bank set PASSWORD=?, "
                            + "ROLE=?, Active=?, Balance=?, USERID=? "
                            + "where ID=?";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, accbank.getPassword());
                pstm.setString(2, accbank.getRole());
                pstm.setInt(3, accbank.getState());
                pstm.setFloat(4, accbank.getBalance());
                pstm.setString(5, accbank.getUserid());
                pstm.setString(6, accbank.getBankid());
                result = pstm.executeUpdate();
                if (result < 0){
                    JOptionPane.showMessageDialog(null, "Failed to update data", 
                                         "Insert Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    returnrs = false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while updating data.");
                System.out.println("Error: " + ex);
                returnrs = false;
            } finally{
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return returnrs;
    }
    
    
    /*Remove data in Account_Bank table*/
    public static boolean removeAccountBank(String Bankid){
        boolean returnrs = true;
        int result;
        con = Make_connection();
        if (con != null){
            try {
                String query = "delete from Account_bank where ID=?";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, Bankid);
                result = pstm.executeUpdate();
                if (result < 0){
                    JOptionPane.showMessageDialog(null, "Failed to remove data", 
                                         "Insert Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    returnrs = false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while removing data.");
                System.out.println("Error: " + ex);
                returnrs = false;
            } finally{
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return returnrs;
    }
    
    
    
    /*Account activity table*/
    public static ArrayList<ActivityHistory> getActivityHistory(String condition){
        ArrayList<ActivityHistory> listAH = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from ACCOUNTHISTORY ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        ActivityHistory ah = new ActivityHistory();
                        LocalDateTime ldtlogin = LocalDateTime.parse(
                                            rs.getString("RECORD_LOGIN")
                                                    .replace(' ', 'T'));
                        LocalDateTime ldtlogout = LocalDateTime.parse(
                                            rs.getString("RECORD_LOGOUT")
                                                    .replace(' ', 'T'));
                        ah.setActivityHis(rs.getString("USERNAME"), ldtlogin, ldtlogout);
                        listAH.add(ah);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listAH;
    }
    
    
    /*Account activity table*/
    public static ArrayList<TransactionHistory> getTransactionHistory(String condition){
        ArrayList<TransactionHistory> listTH = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from [Transaction] ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        TransactionHistory th = new TransactionHistory();
                        LocalDateTime date = LocalDateTime.parse(
                                            rs.getString("Record")
                                                    .replace(' ', 'T'));
                        th.setActivityHis(rs.getString("Customer_ID"), 
                                        rs.getFloat("Money"), rs.getString("Content_"),
                                        date);
                        listTH.add(th);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listTH;
    }
    
    
    
    /*Treatment Management*/
    public static ArrayList<TreatmentPlace> getTreatmentPlace(String condition){
        ArrayList<TreatmentPlace> listTMP = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from TREATMENTPLACE ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        TreatmentPlace tmp = new TreatmentPlace();
                        tmp.setTreatmentPlace(rs.getString("ID"), 
                                        rs.getString("Name"), 
                                        rs.getInt("Capacity"),
                                        rs.getInt("Quantity"));
                        listTMP.add(tmp);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listTMP;
    }
    
    
    public static boolean addTreatmentPlace(TreatmentPlace tmp){
        boolean returnrs = true;
        int result;
        con = Make_connection();
        if (con != null){
            try {
                String query = "insert into TREATMENTPLACE(ID, NAME, CAPACITY, QUANTITY) "
                            + "VALUES(?, ?, ?, ?)";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, tmp.getID());
                pstm.setString(2, tmp.getName());
                pstm.setInt(3, tmp.getCapacity());
                pstm.setInt(4, tmp.getQuantity());
                result = pstm.executeUpdate();
                if (result < 0){
                    JOptionPane.showMessageDialog(null, "Failed to insert data", 
                                         "Insert Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    returnrs = false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while adding data.");
                System.out.println("Error: " + ex);
                returnrs = false;
            } finally{
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return returnrs;
    }
    
    
    public static boolean editTreatmentPlace(TreatmentPlace tmp){
        boolean returnrs = true;
        int result;
        con = Make_connection();
        if (con != null){
            try {
                String query = "update TREATMENTPLACE set NAME=?, CAPACITY=?, QUANTITY=? "
                            + "where ID=?";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, tmp.getName());
                pstm.setInt(2, tmp.getCapacity());
                pstm.setInt(3, tmp.getQuantity());
                pstm.setString(4, tmp.getID());
                result = pstm.executeUpdate();
                if (result < 0){
                    JOptionPane.showMessageDialog(null, "Failed to update data", 
                                         "Insert Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    returnrs = false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while updating data.");
                System.out.println("Error: " + ex);
                returnrs = false;
            } finally{
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return returnrs;
    }
    
    
    public static boolean removeTreatmentPlace(String ID){
        boolean returnrs = true;
        int result;
        con = Make_connection();
        if (con != null){
            try {
                String query = "delete from TREATMENTPLACE where ID=?";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, ID);
                result = pstm.executeUpdate();
                if (result < 0){
                    JOptionPane.showMessageDialog(null, "Failed to remove data", 
                                         "Insert Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    returnrs = false;
                }
            } catch (SQLException ex) {
                System.out.println("Error while removing data.");
                System.out.println("Error: " + ex);
                returnrs = false;
            } finally{
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return returnrs;
    }
}
