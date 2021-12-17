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

    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=Covid-19"; 
    private static final String username = "sa";
    private static final String password = "sa";
    public static Connection conn;
    
    public static void getConnection(){
         try {
            SQLConnection.conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException ex) {
            System.out.println("Can't connect to database");
        }
    }
    
}
