/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;

import DbConnection.SQLConnection;
import covid19.AccountBankFrame;
import covid19.AccountFrame.SignInFrame;
import covid19.AdminManagementPanel;
import covid19.MainFrame;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Thong
 */
public class Account {

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
    
    Connection conn = null;
    Statement stmt = null;
    Statement stmtBank = null;
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Covid-19;user=sa;password=sa";

    String username, password, role, userid;
    int activated;
    LocalDateTime datepublished;

    public Account() {
        username = "";
        password = "";
        role = "";
        userid = "";
        activated = 0;
        datepublished = null;
    }

    public void show() {
        System.out.println(username);
        System.out.println(password);
        System.out.println(role);
        System.out.println(userid);
        System.out.println(activated);
    }

    public void setAccount(String Username, String Password, String Role,
            String Userid, int Activated, LocalDateTime DatePublished) {
        username = Username;
        password = Password;
        role = Role;
        userid = Userid;
        activated = Activated;
        datepublished = DatePublished;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUserid() {
        return userid;
    }

    public int getState() {
        return activated;
    }

    public LocalDateTime getDatePublished() {
        return datepublished;
    }

    public String signin(String usernameInput, String passwordInput, AccountBank b) {
        String sql = "SELECT USERNAME, PASSWORD, ROLE, USERID, ACTIVATED, DATEPUBLISHED FROM ACCOUNT";
        String sqlBank = "SELECT ID,PASSWORD,ROLE,ACTIVATED,BALANCE,USERID,DATEPUBLISHED FROM Account_Bank";
        try {
            String passwordHash = toHexString(getSHA(passwordInput));
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            stmtBank = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rsBank = stmtBank.executeQuery(sqlBank);
            while (rs.next()) {
                String user_name = rs.getString("USERNAME");
                String pass_word = rs.getString("PASSWORD");
                String Role = rs.getString("ROLE");
                if (usernameInput.equals(user_name) && passwordHash.equals(pass_word)) {
                    switch (Role) {
                        case "ADMIN" -> {
                            return "ADMIN";
                        }
                        case "Manager" -> {
                            return "Manager";
                        }
                        case "User" -> {
                            LocalDateTime ldt = LocalDateTime.parse(rs.getString("DATEPUBLISHED").replace(' ', 'T'));
                            this.setAccount(user_name, pass_word, Role, rs.getString("USERID"), rs.getInt("ACTIVATED"), ldt);
                            return "User";
                        }
                    }
                }
            }
            while (rsBank.next()) {
                String id = rsBank.getString("ID");
                String pass_word = rsBank.getString("PASSWORD");
                if (usernameInput.equals(id) && passwordHash.equals(pass_word)) {
                    LocalDateTime ldt = LocalDateTime.parse(rsBank.getString("DATEPUBLISHED").replace(' ', 'T'));
                    b.setAccountBank(id, pass_word, rsBank.getString("ROLE"), rsBank.getInt("ACTIVATED"), rsBank.getFloat("Balance"), rsBank.getString("UserID"), ldt);
                    return "Bank";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignInFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignInFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public void signup(){
        
    }
    public int check(){
        int flag = 0;
        String sql = "SELECT USERNAME, PASSWORD, ROLE, USERID, ACTIVATED, DATEPUBLISHED FROM ACCOUNT";
        try {
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                flag = 1;
            }
            else{
                flag = 2;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
}
