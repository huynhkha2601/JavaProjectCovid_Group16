/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package covid19;

import DbConnection.SQLConnection;
import Helper.DateFormatter;
import Helper.Validator;
import ManagedHistory.ManagedHistory;
import ManagedHistory.ManagedHistoryInf;
import Packages.Packages;
import Packages.PackageInf;
import covid19.AccountFrame.SignInFrame;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                new SQLConnection().setConn();
                new MainFrame().setVisible(true);
            }
        });
        th.start();

//      Testing Packages
//        Date d = new Date(2021,12,16);
//        System.out.println(PackageInf.removePackages("6"));
//        List<Object> lst = PackageInf.statisticsPackages();
//        java.sql.Date sql = java.sql.Date.valueOf(d);
//        System.out.println(sql.toString());
//        
//        LocalDate dw = sql.toLocalDate();
//        System.out.println(dw);
//      Testing managedhistory
//        System.out.println(dateString);
//        new SignInFrame().setVisible(true);
//        List<ManagedHistory> list = ManagedHistoryInf.getAllManagedHistorys();
//        System.out.println(ManagedHistoryInf.searchManageHistory(new ManagedHistory("3", "F1","F2", new Date(2021, 12, 16, 10, 39, 59))));
//        List<ManagedHistory> lst = ManagedHistoryInf.filterManageHistory(new Date(121,10,15,00 ,00, 00), new Date(121,10,22,23 ,59, 59));
    }

}
