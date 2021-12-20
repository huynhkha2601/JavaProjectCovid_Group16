/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Treatment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashbell
 */
public class TreatmentInf {
    static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Covid-19;user=sa;password=sa";
    public static List<Treatment> getAllPatient() {
        List<Treatment> result = new ArrayList<Treatment>();
        try {
            Class.forName(TreatmentInf.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(TreatmentInf.DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TREATMENTPLACE");
            while(rs.next()) {
                Treatment temp = new Treatment(rs.getString("ID"), rs.getString("NAME"), rs.getInt("CAPACITY"), rs.getInt("QUANTITY"));
                result.add(temp);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TreatmentInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TreatmentInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
