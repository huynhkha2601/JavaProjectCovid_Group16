/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DbConnection;

import covid19.Covid19Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class SQLConnection {

    
    public static Connection conn;
    
    public static Connection getConnection(){
         try {
            final String url = "jdbc:sqlserver://localhost:1433;databaseName=Covid-19"; 
            final String username = "sa";
            final String password = "sa";
            Connection conn = DriverManager.getConnection(url,username,password);
            return conn;
        } catch (SQLException ex) {
            System.out.println("Can't connect to database");
        }
         return null;
    }
    
}
