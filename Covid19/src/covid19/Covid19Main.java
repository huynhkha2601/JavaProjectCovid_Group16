/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package covid19;

import DbConnection.SQLConnection;
import Packages.PackageInf;
import java.sql.Connection;
import java.sql.DriverManager;
import covid19.AccountFrame.SignInFrame;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Packages.Package;
/**
 *
 * @author PC
 */
public class Covid19Main {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SQLConnection();
//        Date d = new Date(2021,12,16);
//        System.out.println(PackageInf.updatePackages(new Package("6","H",30,new Date(2021,12,16), 250, 67)));
//        System.out.println(PackageInf.removePackages("6"));
//        List<Object> lst = PackageInf.statisticsPackages();
//        new SignInFrame().setVisible(true);
    }
    
}
