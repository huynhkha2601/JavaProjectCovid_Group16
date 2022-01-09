/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashbell
 */
public class AccountInf {
    static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Covid-19;user=sa;password=sa";
    public static Account getAccountBank(String id) {
        Account resultAccount = new Account();
        try {
            Class.forName(AccountInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(AccountInf.DB_URL); 
            Statement stmt = conn.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT * FROM Customer c JOIN Account_Bank aB ON c.BankID = aB.ID WHERE c.CCCD = '" + id + "'" );
            ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT a WHERE a.USERID = '" + id + "'" );
            while(rs.next())
                resultAccount.setAccount(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("ROLE"), rs.getString("USERID"), rs.getInt("ACTIVATED"), rs.getTimestamp("DATEPUBLISHED").toLocalDateTime());            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultAccount;
    }
    public static boolean addAccountBank(Account a) {
        try {
            Class.forName(AccountInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(AccountInf.DB_URL); 
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Account_Bank VALUES (?, ?, 'User', 1, 1000000, NULL, ?)");
            stmt.setString(1, a.getUsername());
            stmt.setString(2, a.getPassword());
            stmt.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            stmt.executeUpdate();
            stmt = conn.prepareStatement("INSERT INTO Customer VALUES (?, ?)");
            stmt.setString(1, a.getUsername());
            stmt.setString(2, a.getUserid());
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean addAdminBank() {
        try {
            Class.forName(AccountInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(AccountInf.DB_URL); 
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Account_Bank VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Admin', 1, 0, NULL, ?)");
            stmt.setString(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
}
