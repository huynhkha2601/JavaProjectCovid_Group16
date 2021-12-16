/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package covid19;

import DbConnection.SQLConnection;
import ManagedHistory.ManagedHistory;
import ManagedHistory.ManagedHistoryInf;
import Packages.Package;
import Packages.PackageInf;
import covid19.AccountFrame.SignInFrame;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
        
//      Testing Package
//        Date d = new Date(2021,12,16);
//        System.out.println(PackageInf.updatePackages(new Package("6","H",30,new Date(2021,12,16), 250, 67)));
//        System.out.println(PackageInf.removePackages("6"));
//        List<Object> lst = PackageInf.statisticsPackages();


//      Testing managedhistory
//        List<ManagedHistory> list = ManagedHistoryInf.getAllManagedHistorys();
//        System.out.println(ManagedHistoryInf.addManagedHistory(new ManagedHistory("3", "F1","F2", new Date(2021, 12, 16, 10, 39, 59))));
        List<ManagedHistory> lst = ManagedHistoryInf.filterManageHistory(new Date(121,10,15,00 ,00, 00), new Date(121,10,22,23 ,59, 59));


//        new SignInFrame().setVisible(true);
    }

}
