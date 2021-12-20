/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Patient;

import Profile.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashbell
 */
public class PatientInf {
    static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Covid-19;user=sa;password=sa";
    public static List<Profile> getAllPatient() {   
        List<Profile> result = new ArrayList<Profile>();
        try {
            Class.forName(PatientInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(PatientInf.DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MANAGEDPERSON ORDER BY REPLICATE(' ',6-LEN(id)) + id");
            while(rs.next()) {
                Profile temp = new Profile(rs.getString("ID"), rs.getString("FULLNAME"), rs.getInt("YEAROFBIRTH"), rs.getString("ADDRESS"), rs.getString("STATUS"), rs.getString("TREATMENT"), rs.getDouble("DEBT"));
                result.add(temp);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean addPatient(String id, String fullName, int YoB, String address, String status, String treatment) {
        try {
            Class.forName(PatientInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(PatientInf.DB_URL);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO MANAGEDPERSON VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, id);
            stmt.setString(2, fullName);
            stmt.setInt(3, YoB);
            stmt.setString(4, address);
            stmt.setString(5, status);
            stmt.setString(6, treatment);
            stmt.setDouble(7, 0);
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
    
    public static boolean updatePatient(String id, String fullName, int YoB, String address, String status) {
        try {
            Class.forName(PatientInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(PatientInf.DB_URL);
            PreparedStatement stmt = conn.prepareStatement("UPDATE MANAGEDPERSON SET FULLNAME = ?, YEAROFBIRTH = ?, ADDRESS = ?, STATUS = ? WHERE ID = ?");
            stmt.setString(1, fullName);
            stmt.setInt(2, YoB);
            stmt.setString(3, address);
            stmt.setString(4, status);
            stmt.setString(5, id);
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
    
    public static boolean deletePatient(String id) {
        try {
            Class.forName(PatientInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(PatientInf.DB_URL);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM MANAGEDPERSON WHERE ID = ?");
            stmt.setString(1, id);
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
    
    public static boolean transferPatient(String id, String to) {
        try {
            Class.forName(PatientInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(PatientInf.DB_URL);
            PreparedStatement stmt = conn.prepareStatement("UPDATE MANAGEDPERSON SET TREATMENT = ? WHERE ID = ?");
            stmt.setString(1, to);
            stmt.setString(2, id);
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
