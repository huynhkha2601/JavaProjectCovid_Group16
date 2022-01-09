/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;

import DbConnection.SQLConnection;
import covid19.AccountBankFrame;
import covid19.AccountFrame.SignInFrame;
import static covid19.AccountFrame.SignInFrame.getSHA;
import static covid19.AccountFrame.SignInFrame.toHexString;
import covid19.AccountFrame.SignUpFrame;
import covid19.AdminManagementPanel;
import covid19.MainFrame;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    Statement stmt = null, stmt2 = null;
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
        String sql = "SELECT USERNAME, PASSWORD, ROLE, USERID, ACTIVATED, DATEPUBLISHED FROM ACCOUNT WHERE USERNAME='" + usernameInput + "'";
        String sqlBank = "SELECT ID,PASSWORD,ROLE,ACTIVATED,BALANCE,USERID,DATEPUBLISHED FROM Account_Bank";
        try {
            String passwordHash = toHexString(getSHA(passwordInput));
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            stmtBank = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rsBank = stmtBank.executeQuery(sqlBank);
            if (rs.next()) {
                String user_name = rs.getString("USERNAME");
                String pass_word = rs.getString("PASSWORD");
                String Role = rs.getString("ROLE");
                if (pass_word == null) {
                    return "CREATE";
                }
                if (passwordHash.equals(pass_word)) {
                    LocalDateTime login = LocalDateTime.now();
                    String up = "INSERT INTO ACCOUNTHISTORY (USERNAME,RECORD_LOGIN,RECORD_LOGOUT) VALUES(?,?,?)";
                    PreparedStatement preparedStmt = conn.prepareStatement(up);
                    preparedStmt.setString(1, usernameInput);
                    ZonedDateTime zdt = login.atZone(ZoneId.of("GMT+07:00:00"));
                    preparedStmt.setTimestamp(2, new Timestamp(zdt.toInstant().toEpochMilli()));
                    preparedStmt.setTimestamp(3, new Timestamp(zdt.toInstant().toEpochMilli()));
                    preparedStmt.execute();
                    if (rs.getInt("ACTIVATED") == 0) {
                        return "LOCKED";
                    } else {
                        switch (Role) {
                            case "Admin" -> {
                                LocalDateTime ldt = LocalDateTime.parse(rs.getString("DATEPUBLISHED").replace(' ', 'T'));
                                this.setAccount(user_name, pass_word, Role, rs.getString("USERID"), rs.getInt("ACTIVATED"), ldt);
                                return "ADMIN";
                            }
                            case "Manager" -> {
                                LocalDateTime ldt = LocalDateTime.parse(rs.getString("DATEPUBLISHED").replace(' ', 'T'));
                                this.setAccount(user_name, pass_word, Role, rs.getString("USERID"), rs.getInt("ACTIVATED"), ldt);
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
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(SignInFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void signup(String idInput, String usernameInput, String passwordInput, String confirmpasswordInput) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            String sql3 = "INSERT INTO MANAGEDPERSON(ID,STATUS) VALUES (?,?)";
            PreparedStatement preparedStmt2 = conn.prepareStatement(sql3);
            preparedStmt2.setString(1, idInput);
            preparedStmt2.setString(2, "F3");
            preparedStmt2.execute();
            String sql1 = "INSERT INTO ACCOUNT(USERNAME,PASSWORD,ROLE,USERID,ACTIVATED,DATEPUBLISHED) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql1);
            String passwordHash = toHexString(getSHA(passwordInput));
            preparedStmt.setString(1, usernameInput);
            preparedStmt.setString(2, passwordHash);
            preparedStmt.setString(3, "User");
            preparedStmt.setString(4, idInput);
            preparedStmt.setInt(5, 1);
            LocalDateTime now = LocalDateTime.now();
            ZonedDateTime zdt = now.atZone(ZoneId.of("GMT+07:00:00"));
            preparedStmt.setTimestamp(6, new Timestamp(zdt.toInstant().toEpochMilli()));
            preparedStmt.execute();
        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(SignUpFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkUsername(String usernameInput) {
        String sql = "SELECT USERNAME, PASSWORD, ROLE, USERID, ACTIVATED, DATEPUBLISHED FROM ACCOUNT";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (usernameInput.equals(rs.getString("USERNAME"))) {
                    return false;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean checkID(String IDInput) {
        String sql2 = "SELECT USERID FROM ACCOUNT";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql2);
            while (rs.next()) {
                if (IDInput.equals(rs.getString("USERID"))) {
                    return false;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void createPassword(String usernameInput, String passwordInput, int roleInput) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            if (roleInput == 1) {
                String sql = "INSERT INTO ACCOUNT(USERNAME, PASSWORD, ROLE, ACTIVATED, DATEPUBLISHED) VALUES(?,?,?,?,?)";
                PreparedStatement preparedStmt2 = conn.prepareStatement(sql);
                preparedStmt2.setString(1, usernameInput);
                String passwordHash = toHexString(getSHA(passwordInput));
                preparedStmt2.setString(2, passwordHash);
                preparedStmt2.setString(3, "Admin");
                preparedStmt2.setInt(4, 1);
                LocalDateTime now = LocalDateTime.now();
                ZonedDateTime zdt = now.atZone(ZoneId.of("GMT+07:00:00"));
                preparedStmt2.setTimestamp(5, new Timestamp(zdt.toInstant().toEpochMilli()));
                preparedStmt2.execute();
            } else {
                String sql2 = "UPDATE ACCOUNT SET PASSWORD=? WHERE USERNAME=?";
                PreparedStatement preparedStmt = conn.prepareStatement(sql2);
                String passwordHash = toHexString(getSHA(passwordInput));
                preparedStmt.setString(1, passwordHash);
                preparedStmt.setString(2, usernameInput);
                preparedStmt.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePassword(String usernameInput, String passwordInput) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            String sql2 = "UPDATE ACCOUNT SET PASSWORD=? WHERE USERNAME=?";
            PreparedStatement preparedStmt = conn.prepareStatement(sql2);
            String passwordHash = toHexString(getSHA(passwordInput));
            preparedStmt.setString(1, passwordHash);
            preparedStmt.setString(2, usernameInput);
            preparedStmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            String sql2 = "UPDATE ACCOUNTHISTORY SET RECORD_LOGOUT=? WHERE USERNAME=? and RECORD_LOGIN=RECORD_LOGOUT";
            PreparedStatement preparedStmt = conn.prepareStatement(sql2);
            LocalDateTime now = LocalDateTime.now();
            ZonedDateTime zdt = now.atZone(ZoneId.of("GMT+07:00:00"));
            preparedStmt.setTimestamp(1, new Timestamp(zdt.toInstant().toEpochMilli()));
            preparedStmt.setString(2, this.username);
            preparedStmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int check() {
        int flag = 0;
        String sql = "SELECT USERNAME, PASSWORD, ROLE, USERID, ACTIVATED, DATEPUBLISHED FROM ACCOUNT";
        try {
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                flag = 1;
            } else {
                flag = 2;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
}
