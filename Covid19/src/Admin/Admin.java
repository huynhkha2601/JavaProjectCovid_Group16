/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

/**
 *
 * @author ACER
 */


/*import needed package*/
import Account.*;
import Helper.MessageDialog;
import Record.ActivityHistory;
import Record.TransactionHistory;
import TreatmentPlace.TreatmentPlace;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Admin {
    /*Variables for make connection*/
    static Connection con = null;
    static PreparedStatement pstm = null;
    
    
    
    
    /*Make connection function*/
    public static Connection Make_connection(){
        Connection conn = null;
        String DB_name = "Covid-19";
        String DB_URL = ("jdbc:sqlserver://localhost:1433;"
                               + "databaseName="+ DB_name);
        try{
            //Load JDBC driver:
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //Open a connection to database:
            conn = DriverManager.getConnection(
                    DB_URL + ";username=sa;password=sa");
        }
        catch (ClassNotFoundException clsNF){
            System.out.println("Error: unable to load driver class.");
            JOptionPane.showMessageDialog(null, clsNF, 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
            conn = null;
        }
        catch (SQLException sqlex){
            System.out.println("Error: Cannot open a connection to database: "
                    + DB_name);
            JOptionPane.showMessageDialog(null, sqlex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            conn = null;
        }
        return conn;
    }
    
    
    
    
    /*Functions to execute the query*/
    /*Check date-time and convert to SQL date-time*/
    public static int checkDateTime(String datetime){
        int valid_date = 0;
        String[] tempdate = datetime.split("[- ]");
        int length = tempdate.length;
        if (length < 3 || length > 4){
            valid_date = -1;
        }
        else{
            for (int i = 0; i < 3; i++){
                if ((i < 2 && tempdate[i].length() != 2)
                    || i == 2 && tempdate[i].length() != 4){
                    valid_date = -1;
                }
                else{
                    try{
                        Integer.parseInt(tempdate[i]);
                    } catch (NumberFormatException ex){
                        valid_date = -1;
                        break;
                    }
                }
                if (valid_date == -1) break;
            }
            if (valid_date == 0 && length == 4){
                String[] temptime = tempdate[3].split(":");
                int len = temptime.length;
                if (len > 3) valid_date = -1;
                else{
                    valid_date = len;
                    for (int i = 0; i < len; i++){
                        if (temptime[i].length() != 2){
                                valid_date = -1;
                        }
                        else{
                            try{
                                Integer.parseInt(tempdate[i]);
                            } catch (NumberFormatException ex){
                                valid_date = -1;
                                break;
                            }
                        }
                        if (valid_date == -1) break;
                    }
                }
            }
        }
        return valid_date;
    }
    
    public static String toSQLDateTime(String datetime){
        String[] datetimearr = datetime.split("[- ]");
        String result = "";
        int length = datetimearr.length;
        result = result + datetimearr[2] + "-" + datetimearr[1] + "-" + datetimearr[0];
        if (length > 3) result = result + " " + datetimearr[3];
        return result;
    }
    
    
    /*Check condition*/
    public static String make_condition(String findwith){
        String condition = "";
        String[] temp = findwith.split(":");
        if (temp.length == 1){
            condition = "Error: Can not get any information to do the search.";
        }
        else{
            String attribute = temp[0];
            String input = temp[1];
            boolean valid_input = true;
            int valid_datetime = 0;
            if (attribute.equalsIgnoreCase("Bank ID")){
                attribute = "ID";
            }
            if (attribute.equalsIgnoreCase("Active")){
                attribute = "Activated";
                if (!input.equalsIgnoreCase("Active") 
                    && !input.equalsIgnoreCase("Disabled")
                    && !input.equalsIgnoreCase("1")
                    && !input.equalsIgnoreCase("0")){
                    condition = "Error: Attribute Active only accept 'Active' or '1' for 'Activated'"
                                + " and 'Disabled' or '0' for 'Disabled'";
                    valid_input = false;
                }
                else{
                    if (input.equalsIgnoreCase("Active")) input = "1";
                    else if (input.equalsIgnoreCase("Disabled")) input = "0";
                }
            }
            else if (attribute.equalsIgnoreCase("User ID")){
                attribute = "Userid";
            }
            else if (attribute.equalsIgnoreCase("Balance")){
                temp = input.split("~");
                int length = temp.length;
                if (length < 1 || length > 2){
                    valid_input = false;
                }
                else{
                    for (int i = 0; i < length; i++){
                        try{
                            Float.parseFloat(temp[i]);
                        } catch(NumberFormatException ex){
                            valid_input = false;
                            break;
                        }
                    }
                }
                if (valid_input == false){
                    condition = "Error: Attribute Balance only accept format (float)Balance or "
                          + "(float)Balance1~(float)Balance2 (means from Balance1 to Balance2";
                    temp = null;
                }
            }
            else if (attribute.equalsIgnoreCase("Date Published")){
                attribute = "Datepublished";
                input = input.split("\\.")[0];
                System.out.println(input);
                valid_datetime = checkDateTime(input);
                if (valid_datetime == -1){
                    condition = "Error: Attribute Date Published only accept format "
                          + "(int)dd-(int)MM-(int)-yyyy as date "
                          + "and hh:mm:ss as time (time is optional)";
                    valid_input = false;
                }
                else{
                    if (valid_datetime > 0){
                        String[] tempdt = input.split(":");
                        int length = tempdt.length;
                        if (length == 1) input = input + ":00:00";
                        else if(length == 2) input = input + ":00";
                    }
                }
            }
            if (valid_input == true){
                condition = "where ";
                if (attribute.equalsIgnoreCase("username") 
                    || attribute.equalsIgnoreCase("role")
                    || attribute.equalsIgnoreCase("ID")){
                    condition = (condition + attribute 
                                + " like '%" + input + "%'");
                }
                else if (attribute.equalsIgnoreCase("Activated")){
                    condition = (condition + attribute + "=" + input);
                }
                else if (attribute.equalsIgnoreCase("Userid")){
                    condition = (condition + attribute + "='" + input + "'");
                }
                else if (attribute.equalsIgnoreCase("Balance")){
                    if (temp != null && temp.length == 2){
                        condition = (condition + attribute 
                                    + ">=" + temp[0] + " and " + attribute 
                                    + "<=" + temp[1]);
                    }
                    else{
                        condition = (condition + attribute + "=" + temp[0]);
                    }
                }
                else{
                    String input1 = input + " 00:00:00";
                    String input2 = input + " 23:59:59.999";
                    if (valid_datetime > 0){
                        input1 = "'" + toSQLDateTime(input) + "'";
                        String temp1 = input1.replace(' ', 'T').replace("'", "");
                        if (valid_datetime == 1)
                            input2 = LocalDateTime.parse(temp1).plusHours(1).toString();
                        else if (valid_datetime == 2)
                            input2 = LocalDateTime.parse(temp1).plusMinutes(1).toString();
                        else input2 = LocalDateTime.parse(temp1).plusSeconds(1).toString();
                        input2 = input2.replace('T', ' ');
                        input2 = "'" + input2 + "'";
                    }
                    else{
                        input1 = "'" + toSQLDateTime(input1) + "'";
                        input2 = "'" + toSQLDateTime(input2) + "'";
                    }
                    condition = (condition + attribute + ">=" + input1
                               + " and " + attribute + "<=" + input2);
                }
            }
        }
        return condition;
    }
    
    
    
    /*Account table*/
    public static String checkInputAccountData(String activity, Account acc){
        String error = null;
        String Username = acc.getUsername();
        if (Username.length() == 0){
            error = "An account needs an unique username.";
        }
        else if (!activity.equalsIgnoreCase("Remove") && Username.length() < 5){
            error = "Username must have at least 5 characters.";
        }
        else{
            ArrayList<Account> listtemp = Admin.getAccounts(
                                        "where Username='" + Username + "'");
            if (!activity.equalsIgnoreCase("add") && listtemp.isEmpty()){
                error = "There is no account has username is '" + Username + "'";
            }
            else if(activity.equalsIgnoreCase("Add") && !listtemp.isEmpty()){
                error = "Username must be unique but username '" 
                        + Username + "' is duplicated.";
            }
            else{
                if (activity.equalsIgnoreCase("Remove")){
                    if (Username.equalsIgnoreCase("admin")){
                        error = "Cannot remove account 'admin'.";
                    }
                }
                else{
                    String Role = acc.getRole();
                    if (Role.equalsIgnoreCase("Admin") 
                        && !Username.equalsIgnoreCase("admin")){
                        error = "Only account with username is 'admin' "
                              + "can have role 'Admin'.";
                    }
                    else if (!Role.equalsIgnoreCase("Admin") 
                        && Username.equalsIgnoreCase("admin")){
                        error = "Account with username is 'admin' "
                              + "must have role 'Admin'.";
                    }
                    else{
                        String Userid = acc.getUserid();
                        if (!Role.equalsIgnoreCase("User") 
                            && Userid != null){
                            error = "Only account with 'User' role can have"
                                  + "an user's id.";
                        }
                        else{
                            listtemp = Admin.getAccounts(
                                       "where USERID='" + Userid + "'");
                            boolean isUnique = true;
                            if (activity.equalsIgnoreCase("Add")){
                                if (!listtemp.isEmpty()){
                                    error = "UserID must be unique";
                                    isUnique = false;
                                }
                            }
                            else{
                                if (!listtemp.isEmpty()){
                                    isUnique = false;
                                    for (Account temp : listtemp){
                                        if (temp.getUsername().equalsIgnoreCase(Username)){
                                            isUnique = true;
                                            break;
                                        }
                                    }
                                    if (isUnique == false){
                                        error = "UserID must be unique";
                                    }
                                }
                                if (isUnique == true){
                                    int Activated = acc.getState();
                                    if (Username.equalsIgnoreCase("admin")
                                        && Activated == 0){
                                        error = "'admin' account must always be activated";
                                    }
                                    else{
                                        String condition = "where Username='" + acc.getUsername() + "'";
                                        ArrayList<Account> list = new ArrayList<>(getAccounts(condition));
                                        acc.setAccount(acc.getUsername(), acc.getPassword(), 
                                                acc.getRole(), acc.getUserid(), acc.getState(), 
                                                list.get(0).getDatePublished());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return error;
    }
    
    
    /*Get data with condition (get all if there is no condition)*/
    public static ArrayList<Account> getAccounts(String condition){
        ArrayList<Account> listAcc = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from ACCOUNT ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        Account acc = new Account();
                        LocalDateTime ldt = LocalDateTime.parse(
                                            rs.getString("DATEPUBLISHED")
                                                    .replace(' ', 'T'));
                        acc.setAccount(rs.getString("USERNAME"), 
                                        rs.getString("PASSWORD"), 
                                        rs.getString("ROLE"), 
                                        rs.getString("USERID"), 
                                        rs.getInt("ACTIVATED"),
                                        ldt);
                        listAcc.add(acc);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listAcc;
    }
    
    
    /*Insert data into ACCOUNT table*/
    public static String addAccount(Account acc){
        String mess = checkInputAccountData("Add", acc);
        if (mess == null){
            int result;
            con = Make_connection();
            if (con != null){
                try {
                    String query = "insert into ACCOUNT(USERNAME, PASSWORD, ROLE, "
                                + "USERID, ACTIVATED, DATEPUBLISHED) "
                                + "VALUES(?, ?, ?, ?, ?, ?)";
                    System.out.println(query);
                    pstm = con.prepareStatement(query);
                    pstm.setString(1, acc.getUsername());
                    pstm.setString(2, acc.getPassword());
                    pstm.setString(3, acc.getRole());
                    pstm.setString(4, acc.getUserid());
                    pstm.setInt(5, acc.getState());
                    ZonedDateTime zdt = acc.getDatePublished().atZone(ZoneId.of("GMT+07:00:00"));
                    pstm.setTimestamp(6, new Timestamp(zdt.toInstant().toEpochMilli()));
                    result = pstm.executeUpdate();
                    if (result < 0){
                        mess = "Failed to insert data";
                    }
                } catch (SQLException ex) {
                    System.out.println("Error while adding data.");
                    System.out.println("Error: " + ex);
                    mess = ex.getMessage();
                } finally{
                    try{
                        if (pstm != null){
                            pstm.close();
                        }
                        con.close();
                    } catch (SQLException ex) {}
                }
            }
        }
        return mess;
    }
    
    
    /*Update data in ACCOUNT table*/
    public static String editAccount(Account acc){
        String mess = checkInputAccountData("Edit", acc);
        con = Make_connection();
        if (mess == null){
            int result;
            try {
                String query = "update ACCOUNT set "
                            + "ROLE=?, USERID=?, ACTIVATED=? "
                            + "where USERNAME=?";
                System.out.println(query);
                pstm = con.prepareStatement(query);
                pstm.setString(1, acc.getRole());
                pstm.setString(2, acc.getUserid());
                pstm.setInt(3, acc.getState());
                pstm.setString(4, acc.getUsername());
                result = pstm.executeUpdate();
                if (result < 0){
                    mess = "Failed to update data";
                }
            } catch (SQLException ex) {
                System.out.println("Error while updating data.");
                System.out.println("Error: " + ex);
                mess = ex.getMessage();
            } finally{
                try {
                    if (pstm != null){
                        pstm.close();
                    }
                    con.close();
                } catch (SQLException ex) {}
            }
        }
        return mess;
    }
    
    
    /*Remove data in ACCOUNT table*/
    public static String removeAccount(Account acc){
        String mess = checkInputAccountData("Remove", acc);
        if (mess == null){
            int result;
            con = Make_connection();
            if (con != null){
                try {
                    String query = "delete from ACCOUNT where USERNAME=?";
                    System.out.println(query);
                    pstm = con.prepareStatement(query);
                    pstm.setString(1, acc.getUsername());
                    result = pstm.executeUpdate();
                    if (result < 0){
                        mess = "Failed to remove data";
                    }
                } catch (SQLException ex) {
                    System.out.println("Error while removing data.");
                    System.out.println("Error: " + ex);
                    mess = ex.getMessage();
                } finally{
                    try {
                        if (pstm != null){
                            pstm.close();
                        }
                        con.close();
                    } catch (SQLException ex) {}
                }
            }
        }
        return mess;
    }
    
    
    
    /*Account bank table*/
    public static String checkInputAccountBankData(String activity, AccountBank accbnk){
        String error = null;
        String ID = accbnk.getBankid();
        if (ID.length() == 0){
            error = "An account bank needs an unique id.";
        }
        else if (ID.length() < 5){
            error = "Username must have at least 5 characters.";
        }
        else{
            ArrayList<AccountBank> listtemp = Admin.getAccountsBank(
                                        "where ID='" + ID + "'");
            if (!activity.equalsIgnoreCase("add") && listtemp.isEmpty()){
                error = "There is no account bank has ID is '" + ID + "'";
            }
            else if(activity.equalsIgnoreCase("Add") && !listtemp.isEmpty()){
                error = "Bank ID must be unique but ID '" + ID + "' is duplicated.";
            }
            else{
                if (activity.equalsIgnoreCase("Remove")){
                    if (ID.equalsIgnoreCase("admin")){
                        error = "Cannot remove account 'admin'.";
                    }
                }
                else{
                    String Password = accbnk.getPassword();
                    if (Password.length() == 0){
                        error = "Password is used to keep an account private."
                              + "\nPlease input it.";
                    }
                    else{
                        String Role = accbnk.getRole();
                        if (Role.equalsIgnoreCase("Admin") 
                            && !ID.equalsIgnoreCase("admin")){
                            error = "Only account bank with ID is 'admin' "
                                  + "can have role 'Admin'.";
                        }
                        else if (!Role.equalsIgnoreCase("Admin") 
                            && ID.equalsIgnoreCase("admin")){
                            error = "Account bank with ID is 'admin' "
                                  + "must have role 'Admin'.";
                        }
                        else{
                            String Userid = accbnk.getUserid();
                            if (!Role.equalsIgnoreCase("User") 
                                && Userid != null){
                                error =  "Only account with 'User' role "
                                       + "needs an user's id.";
                            }
                            else{
                                listtemp = Admin.getAccountsBank(
                                           "where USERID='" + Userid + "'");
                                boolean isUnique = true;
                                if (activity.equalsIgnoreCase("Add")){
                                    if (!listtemp.isEmpty()){
                                        error =  "UserID must be unique.";
                                        isUnique = false;
                                    }
                                }
                                else{
                                    if (!listtemp.isEmpty()){
                                        isUnique = false;
                                        for (AccountBank temp : listtemp){
                                            if (temp.getBankid().equalsIgnoreCase(ID)){
                                                isUnique = true;
                                                break;
                                            }
                                        }
                                        if (isUnique == false){
                                            error =  "UserID must be unique.";
                                        }
                                    }
                                    if (isUnique == true){
                                        int Activated = accbnk.getState();
                                        if (ID.equalsIgnoreCase("admin")
                                            && Activated == 0){
                                            error = "'admin' account must always be activated";
                                        }
                                        else{
                                            String condition = "where ID='" + accbnk.getBankid() + "'";
                                            ArrayList<AccountBank> list = new ArrayList<>(getAccountsBank(condition));
                                            accbnk.setAccountBank(accbnk.getBankid(), accbnk.getPassword(), 
                                                    accbnk.getRole(), accbnk.getState(), accbnk.getBalance(), 
                                                    accbnk.getUserid(), list.get(0).getDatePublished());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return error;
    }
    
    
    /*Get data with condition (get all if there is no condition)*/
    public static ArrayList<AccountBank> getAccountsBank(String condition){
        ArrayList<AccountBank> listAccBnk = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from Account_Bank ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        AccountBank accbnk = new AccountBank();
                        LocalDateTime ldt = LocalDateTime.parse(
                                            rs.getString("DATEPUBLISHED")
                                                    .replace(' ', 'T'));
                        accbnk.setAccountBank(rs.getString("ID"), 
                                        rs.getString("PASSWORD"), 
                                        rs.getString("ROLE"),
                                        rs.getInt("Activated"),
                                        rs.getFloat("Balance"),
                                        rs.getString("USERID"), 
                                        ldt);
                        listAccBnk.add(accbnk);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listAccBnk;
    }
    
    
    /*Insert data into Account_Bank table*/
    public static String addAccountBank(AccountBank accbank){
        String mess = checkInputAccountBankData("Add", accbank);
        if (mess == null){
            int result;
            con = Make_connection();
            if (con != null){
                try {
                    String query = "insert into Account_Bank(ID, PASSWORD, ROLE, "
                                + "ACTIVATED, Balance, UserID, DATEPUBLISHED) "
                                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
                    System.out.println(query);
                    pstm = con.prepareStatement(query);
                    pstm.setString(1, accbank.getBankid());
                    pstm.setString(2, accbank.getPassword());
                    pstm.setString(3, accbank.getRole());
                    pstm.setInt(4, accbank.getState());
                    pstm.setFloat(5, accbank.getBalance());
                    pstm.setString(6, accbank.getUserid());
                    ZonedDateTime zdt = accbank.getDatePublished().atZone(ZoneId.of("GMT+07:00:00"));
                    pstm.setTimestamp(7, new Timestamp(zdt.toInstant().toEpochMilli()));
                    result = pstm.executeUpdate();
                    if (result < 0){
                        mess = "Failed to insert data";
                    }
                } catch (SQLException ex) {
                    System.out.println("Error while adding data.");
                    System.out.println("Error: " + ex);
                    mess = ex.getMessage();
                } finally{
                    try {
                        if (pstm != null){
                            pstm.close();
                        }
                        con.close();
                    } catch (SQLException ex) {}
                }
            }
        }
        return mess;
    }
    
    
    /*Update data in Account_Bank table*/
    public static String editAccountBank(AccountBank accbank){
        String mess = checkInputAccountBankData("Edit", accbank);
        if (mess == null){
            int result;
            con = Make_connection();
            if (con != null){
                try {
                    String query = "update Account_Bank set PASSWORD=?, "
                                + "ROLE=?, Activated=?, Balance=?, USERID=? "
                                + "where ID=?";
                    System.out.println(query);
                    pstm = con.prepareStatement(query);
                    pstm.setString(1, accbank.getPassword());
                    pstm.setString(2, accbank.getRole());
                    pstm.setInt(3, accbank.getState());
                    pstm.setFloat(4, accbank.getBalance());
                    pstm.setString(5, accbank.getUserid());
                    pstm.setString(6, accbank.getBankid());
                    result = pstm.executeUpdate();
                    if (result < 0){
                        mess = "Failed to update data";
                    }
                } catch (SQLException ex) {
                    System.out.println("Error while updating data.");
                    System.out.println("Error: " + ex);
                    mess = ex.getMessage();
                } finally{
                    try {
                        if (pstm != null){
                            pstm.close();
                        }
                        con.close();
                    } catch (SQLException ex) {}
                }
            }
        }
        return mess;
    }
    
    
    /*Remove data in Account_Bank table*/
    public static String removeAccountBank(AccountBank accbank){
        String mess = checkInputAccountBankData("Remove", accbank);
        if (mess == null){
            int result;
            con = Make_connection();
            if (con != null){
                try {
                    String query = "delete from Account_bank where ID=?";
                    System.out.println(query);
                    pstm = con.prepareStatement(query);
                    pstm.setString(1, accbank.getBankid());
                    result = pstm.executeUpdate();
                    if (result < 0){
                        mess = "Failed to remove data.";
                    }
                } catch (SQLException ex) {
                    System.out.println("Error while removing data.");
                    System.out.println("Error: " + ex);
                    mess = ex.getMessage();
                } finally{
                    try {
                        if (pstm != null){
                            try {
                                pstm.close();
                            } catch (SQLException ex) {}
                        }
                         con.close();
                    } catch (SQLException ex) {}
                }
            }
        }
        return mess;
    }
    
    
    
    /*Account activity table*/
    public static ArrayList<ActivityHistory> getActivityHistory(String condition){
        ArrayList<ActivityHistory> listAH = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from ACCOUNTHISTORY ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        ActivityHistory ah = new ActivityHistory();
                        LocalDateTime ldtlogin = LocalDateTime.parse(
                                            rs.getString("RECORD_LOGIN")
                                                    .replace(' ', 'T'));
                        LocalDateTime ldtlogout = LocalDateTime.parse(
                                            rs.getString("RECORD_LOGOUT")
                                                    .replace(' ', 'T'));
                        ah.setActivityHis(rs.getString("USERNAME"), ldtlogin, ldtlogout);
                        listAH.add(ah);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listAH;
    }
    
    
    /*Account activity table*/
    public static ArrayList<TransactionHistory> getTransactionHistory(String condition){
        ArrayList<TransactionHistory> listTH = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from [Transaction] ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        TransactionHistory th = new TransactionHistory();
                        LocalDateTime date = LocalDateTime.parse(
                                            rs.getString("Record")
                                                    .replace(' ', 'T'));
                        th.setActivityHis(rs.getString("Customer_ID"), 
                                        rs.getFloat("Money"), rs.getString("Content_"),
                                        date);
                        listTH.add(th);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listTH;
    }
    
    
    
    /*Treatment Management*/
    public static String checkInputTreatmentPlaceData(String activity, TreatmentPlace tmp){
        String error = null;
        String ID = tmp.getID();
        if (ID.length() == 0){
            error = "A treatment place needs an unique id.";
        }
        else{
            ArrayList<TreatmentPlace> listtemp = Admin.getTreatmentPlace(
                                        "where ID='" + ID + "'");
            if (!activity.equalsIgnoreCase("add") && listtemp.isEmpty()){
                error = "There is no treatment place has ID is '" + ID + "'";
            }
            else if(activity.equalsIgnoreCase("Add")){
                if (!listtemp.isEmpty()){
                    error = "ID of treatment place must be unique but ID '" 
                        + ID + "' is duplicated.";
                }
                else if (tmp.getName().length() == 0){
                    error = "A treatment place needs a name.";
                }
            }
        }
        return error;
    }
    
    
    public static ArrayList<TreatmentPlace> getTreatmentPlace(String condition){
        ArrayList<TreatmentPlace> listTMP = new ArrayList<>();
        ResultSet rs = null;
        con = Make_connection();
        if (con != null){
            try {
                String query = "select * from TREATMENTPLACE ";
                /*IF there is a condition*/
                if (condition.length() > 0){
                    query = (query + condition);
                }
                System.out.println(query);
                pstm = con.prepareStatement(query);
                rs = pstm.executeQuery();
                if (rs == null){
                    JOptionPane.showMessageDialog(null, "Failed to get data", 
                                         "Class Error", 
                                         JOptionPane.ERROR_MESSAGE);
                }
                else{
                    while(rs.next()){
                        TreatmentPlace tmp = new TreatmentPlace();
                        tmp.setTreatmentPlace(rs.getString("ID"), 
                                        rs.getString("Name"), 
                                        rs.getInt("Capacity"),
                                        rs.getInt("Quantity"));
                        listTMP.add(tmp);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error while getting data.");
                JOptionPane.showMessageDialog(null, ex, 
                                         "SQL Error", 
                                         JOptionPane.ERROR_MESSAGE);
            } finally{
                if (rs!= null){
                    try {
                        rs.close();
                    } catch (SQLException ex) {}
                }
                if (pstm != null){
                    try {
                        pstm.close();
                    } catch (SQLException ex) {}
                }
                try {
                       con.close();
                } catch (SQLException ex) {}
            }
        }
        return listTMP;
    }
    
    
    public static String addTreatmentPlace(TreatmentPlace tmp){
        String mess = checkInputTreatmentPlaceData("Add", tmp);
        if (mess == null){
            int result;
            con = Make_connection();
            if (con != null){
                try {
                    String query = "insert into TREATMENTPLACE(ID, NAME, CAPACITY, QUANTITY) "
                                + "VALUES(?, ?, ?, ?)";
                    System.out.println(query);
                    pstm = con.prepareStatement(query);
                    pstm.setString(1, tmp.getID());
                    pstm.setString(2, tmp.getName());
                    pstm.setInt(3, tmp.getCapacity());
                    pstm.setInt(4, tmp.getQuantity());
                    result = pstm.executeUpdate();
                    if (result < 0){
                        mess = "Failed to insert data";
                    }
                } catch (SQLException ex) {
                    System.out.println("Error while adding data.");
                    System.out.println("Error: " + ex);
                    mess = ex.getMessage();
                } finally{
                    try {
                        if (pstm != null){
                            pstm.close();
                        }
                        con.close();
                    } catch (SQLException ex) {}
                }
            }
        }
        return mess;
    }
    
    
    public static String editTreatmentPlace(TreatmentPlace tmp){
        String mess = checkInputTreatmentPlaceData("Edit", tmp);
        if (mess == null){
            int result;
            con = Make_connection();
            if (con != null){
                try {
                    String query = "update TREATMENTPLACE set NAME=?, CAPACITY=?, QUANTITY=? "
                                + "where ID=?";
                    System.out.println(query);
                    pstm = con.prepareStatement(query);
                    pstm.setString(1, tmp.getName());
                    pstm.setInt(2, tmp.getCapacity());
                    pstm.setInt(3, tmp.getQuantity());
                    pstm.setString(4, tmp.getID());
                    result = pstm.executeUpdate();
                    if (result < 0){
                        mess = "Failed to update data.";
                    }
                } catch (SQLException ex) {
                    System.out.println("Error while updating data.");
                    System.out.println("Error: " + ex);
                    mess = ex.getMessage();
                } finally{
                    try {
                        if (pstm != null){
                            pstm.close();
                        }
                        con.close();
                    } catch (SQLException ex) {}
                }
            }
        }
        return mess;
    }
    
    
    public static String removeTreatmentPlace(TreatmentPlace tmp){
        String mess = checkInputTreatmentPlaceData("Remove", tmp);
        if (mess == null){
            int result;
            int numpatient = 0;
            ResultSet rs = null;
            con = Make_connection();
            if (con != null){
                try {
                    String id = tmp.getID();
                    String query = "select count(ID) as Num from MANAGEDPERSON "
                                    + "where TREATMENT=?";
                    pstm = con.prepareStatement(query);
                    pstm.setString(1, id);
                    rs = pstm.executeQuery();
                    while (rs.next()){
                        numpatient = rs.getInt("Num");
                    }
                    if (numpatient > 0){
                        mess = "Cannot remove this treatment place because some "
                                + "patients still being treated at here.";
                    }
                    else{
                        query = "delete from TREATMENTPLACE where ID=?";
                        System.out.println(query);
                        pstm = con.prepareStatement(query);
                        pstm.setString(1, id);
                        result = pstm.executeUpdate();
                        if (result < 0){
                            mess = "Failed to delete data.";
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Error while removing data.");
                    System.out.println("Error: " + ex);
                    mess = ex.getMessage();
                } finally{
                    try {
                        if (pstm != null){
                            pstm.close();
                        }
                        con.close();
                    } catch (SQLException ex) {}
                }
            }
        }
        return mess;
    }
}
