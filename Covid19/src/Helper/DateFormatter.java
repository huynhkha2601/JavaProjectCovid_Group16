/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class DateFormatter {
    
    public static String formatToSQLDate(String dateString){             
        try {
            dateString = dateString.trim().replaceAll("[^\\d]+", "-").
                replaceAll("^\\D+|\\D+$", "");
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            return (new SimpleDateFormat("yyyy-MM-dd").format(date));
            
        } catch (ParseException ex) {
            Logger.getLogger(DateFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String formatToSQLDateTime(String dateString){             
        dateString = dateString.trim().replaceAll("^\\D+|\\D+$", "");
        String[] d = dateString.split("\\D+");
        return d[0]+"-"+d[1]+"-"+d[2]+" "+d[3]+":"+d[4]+":"+d[5];
    }
    
    public static String parse(LocalDate date){        
       return  date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
    public static String parse(LocalDateTime date){             
        return  date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
    
    
    
}
