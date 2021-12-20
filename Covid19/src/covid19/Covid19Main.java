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
      new MainFrame().setVisible(true);
    
    }

}
