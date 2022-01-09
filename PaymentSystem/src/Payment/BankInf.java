/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Payment;
import Account.AccountBank;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ashbell
 */
public class BankInf {
    static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Covid-19;user=sa;password=sa";
    public static AccountBank getAccountBank(String id) {
        AccountBank resultAccount = new AccountBank();
        try {
            Class.forName(BankInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(BankInf.DB_URL); 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer c JOIN Account_Bank aB ON c.BankID = aB.ID WHERE c.CCCD = '" + id + "'" );
            while(rs.next())
                resultAccount.setAccountBank(rs.getString("ID"), rs.getString("Password"), rs.getString("Role"), rs.getInt("Active"), rs.getFloat("Balance"), rs.getString("UserID"), rs.getTimestamp("DATEPUBLISHED").toLocalDateTime());            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BankInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultAccount;
    }
    public static AccountBank getAdminAccountBank() {
        AccountBank resultAccount = new AccountBank();
        try {
            Class.forName(BankInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(BankInf.DB_URL); 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Account_Bank aB WHERE ab.Role = 'Admin'");
            while(rs.next())
                resultAccount.setAccountBank(rs.getString("ID"), rs.getString("Password"), rs.getString("Role"), rs.getInt("Active"), rs.getFloat("Balance"), rs.getString("UserID"), rs.getTimestamp("DATEPUBLISHED").toLocalDateTime());            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BankInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultAccount;
    }
    public static List<Transaction> getTransaction(String id) {
        List<Transaction> result = new ArrayList<Transaction>();
        try {
            Class.forName(BankInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(BankInf.DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = null;
            if (!id.equals("")) 
                rs = stmt.executeQuery("SELECT * FROM [Transaction] WHERE Customer_ID = '" + id + "'" );
            else
                rs = stmt.executeQuery("SELECT * FROM [Transaction]");
            while(rs.next()) {
                Transaction temp = new Transaction(rs.getString("Customer_ID"), rs.getFloat("money"), rs.getString("Content_"), rs.getTimestamp("Record").toLocalDateTime());
                result.add(temp);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BankInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static boolean addTransaction(String id, float money, String content) {
        try {
            Class.forName(BankInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(BankInf.DB_URL);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO [Transaction] VALUES (?, ?, ?, ?)");
            stmt.setString(1, id);
            stmt.setFloat(2, money);
            stmt.setString(3, content);
            stmt.setString(4, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BankInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
