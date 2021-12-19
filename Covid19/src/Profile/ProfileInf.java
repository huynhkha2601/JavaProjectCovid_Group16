/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Profile;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;


/**
 *
 * @author Ashbell
 */
public class ProfileInf {
    static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Covid-19;user=sa;password=sa";
    public static Profile getProfile(String id) {
        Profile resultProfile = new Profile();
        try {
            Class.forName(ProfileInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(ProfileInf.DB_URL); 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MANAGEDPERSON MP JOIN TREATMENTPLACE TP ON MP.TREATMENT = TP.ID WHERE MP.ID = '" + id + "'" );
            while(rs.next())
                resultProfile = new Profile(rs.getString("ID"), rs.getString("FULLNAME"), rs.getInt("YEAROFBIRTH"), rs.getString("ADDRESS"), rs.getString("STATUS"), rs.getString("NAME"), rs.getDouble("DEBT"));            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfileInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultProfile;
    }
    public static boolean updateProfile(String id, String fullName, String address, int YoB) {
        try {
            Class.forName(ProfileInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(ProfileInf.DB_URL);
            PreparedStatement stmt = conn.prepareStatement("UPDATE MANAGEDPERSON SET FULLNAME =?, YEAROFBIRTH =?, ADDRESS =? WHERE ID = '" + id + "'");
            stmt.setString(1, fullName);
            stmt.setInt(2, YoB);
            stmt.setString(3, address);
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfileInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
