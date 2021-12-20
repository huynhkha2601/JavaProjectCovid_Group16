/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package covid19;

import covid19.AccountFrame.SignInFrame;
import Account.*;
import Admin.Admin;
import Helper.DateFormatter;
import Record.ActivityHistory;
import Record.TransactionHistory;
import TreatmentPlace.TreatmentPlace;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class AdminManagementPanel extends javax.swing.JFrame {
    /*Variables for connection to database*/
    private final String DB_name = "Covid-19";
    private final String DB_URL = ("jdbc:sqlserver://localhost:1433;"
                               + "databaseName="+ DB_name);
    private ArrayList<Account> listacc = new ArrayList<>(Admin.getAccounts(""));
    private Account tempacc = null;
    
    private ArrayList<AccountBank> listaccbank = new ArrayList<>(Admin.getAccountsBank(""));
    private AccountBank tempaccbank = null;
    
    private ArrayList<ActivityHistory> listah = new ArrayList<>(Admin.getActivityHistory(""));
    private ArrayList<TransactionHistory> listth = new ArrayList<>(Admin.getTransactionHistory(""));
    
    private ArrayList<TreatmentPlace> listTMP = new ArrayList<>(Admin.getTreatmentPlace(""));
    private TreatmentPlace temptmp = null;
 
    
    /*Show data to table of GUI*/
    public void showTableAccount(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "Username", "Password", "Role", "UserID", "Activated", 
            "Data Pulished"
        });
        for (Account acc : listacc){
            model.addRow(new Object[]{
                acc.getUsername(), acc.getPassword(), acc.getRole(), 
                acc.getUserid(), acc.getState(), 
                DateFormatter.parse(acc.getDatePublished())
            });
        }
        tblList.setModel(model);
    }
    
    public void showTableAccountBank(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "BankID", "Password", "Role", "Activated", "Balance", "User ID", 
            "Data Pulished"
        });
        for (AccountBank accbnk : listaccbank){
            model.addRow(new Object[]{
                accbnk.getBankid(), accbnk.getPassword(), accbnk.getRole(), 
                accbnk.getState(), accbnk.getBalance(), accbnk.getUserid(), 
                DateFormatter.parse(accbnk.getDatePublished())
            });
        }
        tblBankList.setModel(model);
    }
    
    public void showTableActivityHistory(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "Username", "Login record", "Logout record"
        });
        for (ActivityHistory ah : listah){
            model.addRow(new Object[]{
                ah.getUsername(), DateFormatter.parse(ah.getLoginDT()), 
                DateFormatter.parse(ah.getLogoutDT())
            });
        }
        tblAccountHistory.setModel(model);
    }
    
    public void showTableTransactionHistory(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "Bank ID", "Money", "Content", "Date"
        });
        for (TransactionHistory th : listth){
            model.addRow(new Object[]{
                th.getBankid(), th.getMoney(), th.getContent(), 
                DateFormatter.parse(th.getDate())
            });
        }
        tblTransactionHistory.setModel(model);
    }
    
    public void showTableTreamentPlace(){
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "ID", "Name", "Capacity", "Quantity"
        });
        for (TreatmentPlace tmp : listTMP){
            model.addRow(new Object[]{
                tmp.getID(), tmp.getName(), tmp.getCapacity(), tmp.getQuantity()
            });
        }
        tblTreatment.setModel(model);
    }
    
    
    /*Get and check if input is valid for Account*/
    public Account getInputAccountData(String activity){
        Account acc = null;
        String Username = txtfUsername.getText().trim();
        if (Username.length() == 0){
            JOptionPane.showMessageDialog(null, 
                                "An account need an unique username.", 
                                "Input Error", 
                                JOptionPane.ERROR_MESSAGE);
        }
        else{
            ArrayList<Account> listtemp = Admin.getAccounts(
                                        "where Username='" + Username + "'");
            if (!activity.equalsIgnoreCase("add") && listtemp.isEmpty()){
                JOptionPane.showMessageDialog(null, 
                            "There is no account has username is '" + Username + "'", 
                            "Input Error", 
                            JOptionPane.ERROR_MESSAGE);
            }
            else if(activity.equalsIgnoreCase("Add") && !listtemp.isEmpty()){
                JOptionPane.showMessageDialog(null, "Username must be "
                                        + "unique but username '"
                                        + Username + "' is duplicated.", 
                                        "SQL Error", 
                                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (activity.equalsIgnoreCase("Remove")){
                    if (Username.equalsIgnoreCase("admin")){
                        JOptionPane.showMessageDialog(null, 
                                        "Cannot remove account 'admin'.", 
                                        "Input Error", 
                                        JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        acc = new Account();
                        acc.setAccount(Username, null, null, null, 0, null);
                    }
                }
                else{
                    String Password = txtfPassword.getText();
                    if (Password.length() == 0){
                        JOptionPane.showMessageDialog(null, 
                                        "Password is used to keep an account private."
                                      + "\nPlease input it.", 
                                        "Input Error", 
                                        JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String Role = cbbRole.getSelectedItem().toString();
                        if (Role.equalsIgnoreCase("Admin") 
                            && !Username.equalsIgnoreCase("admin")){
                            JOptionPane.showMessageDialog(null, 
                                    "Only account with username is 'admin' "
                                  + "can have role 'Admin'", 
                                    "Input Error", 
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else if (!Role.equalsIgnoreCase("Admin") 
                            && Username.equalsIgnoreCase("admin")){
                            JOptionPane.showMessageDialog(null, 
                                    "Account with username is 'admin' "
                                  + "must have role 'Admin'", 
                                    "Input Error", 
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            String Userid = txtfUserID.getText().trim();
                            if (!Role.equalsIgnoreCase("User") 
                                && Userid.length() > 0){
                                JOptionPane.showMessageDialog(null, 
                                        "Only account with 'User' role "
                                      + "needs an user's id.", 
                                        "Input Error", 
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                if (Userid.length() == 0) Userid = null;
                                listtemp = Admin.getAccounts(
                                           "where USERID='" + Userid + "'");
                                boolean isUnique = true;
                                if (activity.equalsIgnoreCase("Add")){
                                    if (!listtemp.isEmpty()){
                                        JOptionPane.showMessageDialog(null, 
                                                    "UserID must be unique", 
                                                    "Input Error", 
                                                    JOptionPane.ERROR_MESSAGE);
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
                                            JOptionPane.showMessageDialog(null, 
                                                        "UserID must be unique", 
                                                        "Input Error", 
                                                        JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                                if (isUnique == true){
                                    acc = new Account();
                                    acc.setAccount(Username, Password, Role, 
                                                    Userid, 0, null);
                                }
                            }
                        }
                    }
                }
            }
        }
        return acc;
    }
    
    /*Get and check if input is valid for Account Bank*/
    public AccountBank getInputAccountBankData(String activity){
        AccountBank accbnk = null;
        String ID = txtfBankID.getText().trim();
        if (ID.length() == 0){
            JOptionPane.showMessageDialog(null, 
                                "An account bank need an unique id.", 
                                "Input Error", 
                                JOptionPane.ERROR_MESSAGE);
        }
        else{
            ArrayList<AccountBank> listtemp = Admin.getAccountsBank(
                                        "where ID='" + ID + "'");
            if (!activity.equalsIgnoreCase("add") && listtemp.isEmpty()){
                JOptionPane.showMessageDialog(null, 
                            "There is no account bank has ID is '" + ID + "'", 
                            "Input Error", 
                            JOptionPane.ERROR_MESSAGE);
            }
            else if(activity.equalsIgnoreCase("Add") && !listtemp.isEmpty()){
                JOptionPane.showMessageDialog(null, "Bank ID must be "
                                        + "unique but ID '"
                                        + ID + "' is duplicated.", 
                                        "SQL Error", 
                                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (activity.equalsIgnoreCase("Remove")){
                    if (ID.equalsIgnoreCase("admin")){
                        JOptionPane.showMessageDialog(null, 
                                        "Cannot remove account 'admin'.", 
                                        "Input Error", 
                                        JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        accbnk = new AccountBank();
                        accbnk.setAccountBank(ID, null, null, 0, 0, null, null);
                    }
                }
                else{
                    String Password = txtfBankPassword.getText();
                    if (Password.length() == 0){
                        JOptionPane.showMessageDialog(null, 
                                        "Password is used to keep an account private."
                                      + "\nPlease input it.", 
                                        "Input Error", 
                                        JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String Role = cbbBankRole.getSelectedItem().toString();
                        if (Role.equalsIgnoreCase("Admin") 
                            && !ID.equalsIgnoreCase("admin")){
                            JOptionPane.showMessageDialog(null, 
                                    "Only account bank with ID is 'admin' "
                                  + "can have role 'Admin'", 
                                    "Input Error", 
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else if (!Role.equalsIgnoreCase("Admin") 
                            && ID.equalsIgnoreCase("admin")){
                            JOptionPane.showMessageDialog(null, 
                                    "Account bank with ID is 'admin' "
                                  + "must have role 'Admin'", 
                                    "Input Error", 
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            String Userid = txtfBankUserID.getText().trim();
                            if (!Role.equalsIgnoreCase("User") 
                                && Userid.length() > 0){
                                JOptionPane.showMessageDialog(null, 
                                        "Only account with 'User' role "
                                      + "needs an user's id.", 
                                        "Input Error", 
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                if (Userid.length() == 0) Userid = null;
                                listtemp = Admin.getAccountsBank(
                                           "where USERID='" + Userid + "'");
                                boolean isUnique = true;
                                if (activity.equalsIgnoreCase("Add")){
                                    if (!listtemp.isEmpty()){
                                        JOptionPane.showMessageDialog(null, 
                                                    "UserID must be unique", 
                                                    "Input Error", 
                                                    JOptionPane.ERROR_MESSAGE);
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
                                            JOptionPane.showMessageDialog(null, 
                                                        "UserID must be unique", 
                                                        "Input Error", 
                                                        JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                                if (isUnique == true){
                                    String Balance = txtfBankBalanced.getText();
                                    if (Balance.length() == 0) Balance = null;
                                    try{
                                        float balance = 0;
                                        if (Balance != null){
                                            balance = Float.parseFloat(Balance);
                                        }
                                        accbnk = new AccountBank();
                                        accbnk.setAccountBank(ID, Password, Role, 
                                                    0, balance, Userid, null);
                                    } catch (NumberFormatException ex){
                                        JOptionPane.showMessageDialog(null, 
                                                "Balance must be a float number", 
                                                "Input Error", 
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return accbnk;
    }
    
    /*Get and check if input is valid for Treatment Place*/
    public TreatmentPlace getInputTreamentPlaceData(String activity){
        TreatmentPlace tmp = null;
        String ID = txtfTreatmentID.getText().trim();
        String mess = "";
        if (ID.length() == 0){
            JOptionPane.showMessageDialog(null, 
                                "A treatment place need an unique id.", 
                                "Input Error", 
                                JOptionPane.ERROR_MESSAGE);
        }
        else{
            ArrayList<TreatmentPlace> listtemp = Admin.getTreatmentPlace(
                                        "where ID='" + ID + "'");
            if (!activity.equalsIgnoreCase("add") && listtemp.isEmpty()){
                JOptionPane.showMessageDialog(null, 
                            "There is no treatment place has ID is '" + ID + "'", 
                            "Input Error", 
                            JOptionPane.ERROR_MESSAGE);
            }
            else if(activity.equalsIgnoreCase("Add") && !listtemp.isEmpty()){
                JOptionPane.showMessageDialog(null, "ID of treatment place must be "
                                        + "unique but ID '"
                                        + ID + "' is duplicated.", 
                                        "SQL Error", 
                                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                boolean valid_input = true;
                String Name = txtfTreatmentName.getText().trim();
                if (Name.length() == 0) Name = null;
                int capacity = 0, quantity = 0;
                if (!activity.equalsIgnoreCase("Remove")){
                    String Capacity = txtfTreatmentCapacity.getText().trim();
                    String Quantity = txtfTreatmentQuantity.getText().trim();
                    if (Capacity.length() > 0){
                        try{
                            capacity = Integer.parseInt(Capacity);
                            if (capacity < 0){
                                mess = "Capacity must be a non-negative number";
                                valid_input = false;
                            }
                        } catch(NumberFormatException ex){
                            mess = "Capacity must be a non-negative number";
                            valid_input = false;
                        }
                    }
                    if (valid_input == true){
                        if (Quantity.length() > 0){
                            try{
                                quantity = Integer.parseInt(Quantity);
                                if (quantity < 0){
                                    mess = "Quantity must be a non-negative number";
                                    valid_input = false;
                                }
                            } catch(NumberFormatException ex){
                                mess = "Quantity must be a non-negative number";
                                valid_input = false;
                            }
                        }
                    }
                }
                if (valid_input == true){
                    tmp = new TreatmentPlace();
                    tmp.setTreatmentPlace(ID, Name, capacity, quantity);
                }
                else{
                    JOptionPane.showMessageDialog(null, mess, 
                                        "SQL Error", 
                                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return tmp;
    }
    
    public int checkDateTime(String datetime){
        int valid_date = 0;
        String[] tempdate = datetime.split(" ");
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
    
    public String toSQLDateTime(String datetime){
        String[] datetimearr = datetime.split(" ");
        String result = "";
        int length = datetimearr.length;
        result = result + datetimearr[2] + "-" + datetimearr[1] + "-" + datetimearr[0];
        if (length > 3) result = result + " " + datetimearr[3];
        return result;
    }
    
    /*Add needed action performed for buttons*/
    void AddActionPerformed(){
        /*For account tab*/
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        
        /*For account bank tab*/
        btnBankFind.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBankFindActionPerformed(evt);
            }
        });
        tblBankList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBankListMouseClicked(evt);
            }
        });
        btnBankRemove.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBankRemoveActionPerformed(evt);
            }
        });
        
        /*For activity and transaction history tab*/
        btnActivityFindbyUsername.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivityFindbyUsernameActionPerformed(evt);
            }
        });
        btnActivityFindbyDate.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivityFindbyDateActionPerformed(evt);
            }
        });
        
        /*For activity and transaction history tab*/
        btnAcitivyFindbyBankID.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivityFindbyBankIDActionPerformed(evt);
            }
        });
        btnActivityBankFindbyDate.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivityBankFindbyDateActionPerformed(evt);
            }
        });
        
        /*For treatment management tab*/
        btnTreatmentRefresh.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTreamntRefreshActionPerformed(evt);
            }
        });
        btnTreatmentFind.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTreatmentFindActionPerformed(evt);
            }
        });
        tblTreatment.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTreatmentMouseClicked(evt);
            }
        });
        btnTreatmentEdit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTreatmentEditActionPerformed(evt);
            }
        });
        btnTreatmentRemove.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTreatmentRemoveActionPerformed(evt);
            }
        });
    }
    
    public void btnRemoveActionPerformed(java.awt.event.ActionEvent evt){
        tempacc = getInputAccountData("Remove");
        if (tempacc != null){
            boolean removers = Admin.removeAccount(tempacc.getUsername());
            if (removers == true){
                txtfUsername.setText("");
                txtfPassword.setText("");
                cbbRole.setSelectedIndex(0);
                txtfUserID.setText("");
                cbbState.setSelectedIndex(0);
                for (Account acc : listacc){
                    if (acc.getUsername().equalsIgnoreCase(tempacc.getUsername())){
                        listacc.remove(acc);
                        showTableAccount();
                        break;
                    }
                }
            }
        }
        tempacc = null;
    }
    
    public void tblListMouseClicked(java.awt.event.MouseEvent evt){
        int row = tblList.getSelectedRow();
        String Username = tblList.getValueAt(row, 0).toString();
        txtfUsername.setText(Username);
        String Password = tblList.getValueAt(row, 1).toString();
        txtfPassword.setText(Password);
        String Role = tblList.getValueAt(row, 2).toString();
        if (Role.equalsIgnoreCase("ADMIN")) Role = "Admin";
        cbbRole.setSelectedItem(Role);
        String Userid = null;
        if (tblList.getValueAt(row, 3) != null){
            Userid = tblList.getValueAt(row, 3).toString();
        }
        txtfUserID.setText(Userid);
        cbbState.setSelectedIndex(
                1 - Integer.parseInt(tblList.getValueAt(row, 4).toString()));
    }
    
    public void btnFindActionPerformed(java.awt.event.ActionEvent evt){
        String input = txtfAttribute.getText().trim();
        if (input.length() == 0){
            JOptionPane.showMessageDialog(this, "Please input "
                                        + "some informations to do the search", 
                                         "Input Error", 
                                         JOptionPane.ERROR_MESSAGE);
        }
        else{
            String attribute = cbbAttribute.getSelectedItem().toString().trim();
            boolean valid_input = true;
            int valid_datetime = 0;
            if (attribute.equalsIgnoreCase("Active")){
                attribute = "Activated";
                if (!input.equalsIgnoreCase("Active") 
                    && !input.equalsIgnoreCase("Disabled")
                    && !input.equalsIgnoreCase("1")
                    && !input.equalsIgnoreCase("0")){
                    JOptionPane.showMessageDialog(this, "Attribute Active "
                                        + "only accept "
                                        + "'Active' or '1' for 'Activated' "
                                        + "and"
                                        + "'Disabled' or '0' for 'Disabled'", 
                                         "Input Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    valid_input = false;
                }
                else{
                    if (input.equalsIgnoreCase("Active")) input = "1";
                    else if (input.equalsIgnoreCase("Disabled")) input = "0";
                }
            }
            else if (attribute.equalsIgnoreCase("Date Published")){
                attribute = "Datepublished";
                input = input.split("\\.")[0];
                System.out.println(input);
                valid_datetime = checkDateTime(input);
                if (valid_datetime == -1){
                    JOptionPane.showMessageDialog(this, "Attribute Date Published "
                            + "only accept format "
                            + "(int)dd (int)MM (int) yyy as date "
                            + "and hh:mm:ss as time (time is optional)", 
                              "Input Error", 
                              JOptionPane.ERROR_MESSAGE);
                    valid_input = false;
                }
                else{
                    if (valid_datetime > 0 && valid_datetime < 4){
                        String[] temp = input.split(":");
                        int length = temp.length;
                        if (length == 1) input = input + ":00:00";
                        else if(length == 2) input = input + ":00";
                    }
                }
            }
            if (valid_input == true){
                String condition = "where ";
                if (attribute.equalsIgnoreCase("username") 
                    || attribute.equalsIgnoreCase("role")){
                    condition = (condition + attribute 
                                + " like '%" + input + "%'");
                }
                else if (attribute.equalsIgnoreCase("Activated")){
                    condition = (condition + attribute + "=" + input);
                }
                else if (attribute.equalsIgnoreCase("Userid")){
                    condition = (condition + attribute + "='" + input + "'");
                }
                else{
                    String input1 = input + " 00:00:00";
                    String input2 = input + " 23:59:59.999";
                    if (valid_datetime > 0){
                        String[] temp = input.split("\\.");
                        if (temp.length == 0) input1 = toSQLDateTime(input);
                        else input1 = toSQLDateTime(temp[0]);
                        String temp1 = input1.replace(' ', 'T').replace("'", "");
                        if (valid_datetime == 1) input2 = LocalDateTime.parse(temp1)
                                                            .plusHours(1).toString();
                        else if (valid_datetime == 2) input2 = LocalDateTime.parse(temp1)
                                                            .plusMinutes(1).toString();
                        else input2 = LocalDateTime.parse(temp1).plusSeconds(1).toString();
                        input2 = input2.replace('T', ' ');
                        input2 = "'" + input2 + "'";
                    }
                    else{
                        input1 = toSQLDateTime(input1);
                        input2 = toSQLDateTime(input2);
                    }
                    condition = (condition + attribute + ">=" + input1
                               + " and " + attribute + "<=" + input2);
                }
                listacc = Admin.getAccounts(condition);
                showTableAccount();
            }
        }
    }
    
    
    public void btnBankRemoveActionPerformed(java.awt.event.ActionEvent evt){
        tempaccbank = getInputAccountBankData("Remove");
        if (tempaccbank != null){
            boolean removers = Admin.removeAccountBank(tempaccbank.getBankid());
            if (removers == true){
                txtfBankID.setText("");
                txtfBankPassword.setText("");
                cbbBankRole.setSelectedIndex(0);
                cbbBankState.setSelectedIndex(0);
                txtfBankBalanced.setText("");
                txtfBankUserID.setText("");
                for (AccountBank accbank : listaccbank){
                    if (accbank.getBankid().equalsIgnoreCase(tempaccbank.getBankid())){
                        listaccbank.remove(accbank);
                        showTableAccountBank();
                        break;
                    }
                }
            }
        }
        tempaccbank = null;
    }
    
    public void tblBankListMouseClicked(java.awt.event.MouseEvent evt){
        int row = tblBankList.getSelectedRow();
        String ID = tblBankList.getValueAt(row, 0).toString();
        txtfBankID.setText(ID);
        String Password = tblBankList.getValueAt(row, 1).toString();
        txtfBankPassword.setText(Password);
        String Role = tblBankList.getValueAt(row, 2).toString();
        if (Role.equalsIgnoreCase("ADMIN")) Role = "Admin";
        cbbBankRole.setSelectedItem(Role);
        cbbBankState.setSelectedIndex(
                1 - Integer.parseInt(tblBankList.getValueAt(row, 3).toString()));
        String Balance = tblBankList.getValueAt(row, 4).toString();
        txtfBankBalanced.setText(Balance);
        String Userid = null;
        if (tblBankList.getValueAt(row, 5) != null){
            Userid = tblBankList.getValueAt(row, 5).toString();
        }
        txtfBankUserID.setText(Userid);
    }
    
    public void btnBankFindActionPerformed(java.awt.event.ActionEvent evt){
        String input = txtfBankAttribute.getText().trim();
        String[] temp = null;
        if (input.length() == 0){
            JOptionPane.showMessageDialog(this, "Please input "
                                        + "some informations to do the search", 
                                         "Input Error", 
                                         JOptionPane.ERROR_MESSAGE);
        }
        else{
            String attribute = cbbBankAttribute.getSelectedItem().toString().trim();
            boolean valid_input = true;
            int valid_datetime = 0;
            if (attribute.equalsIgnoreCase("Bank ID")){
                attribute = "ID";
            }
            else if (attribute.equalsIgnoreCase("Active")){
                if (!input.equalsIgnoreCase("Active") 
                    && !input.equalsIgnoreCase("Disabled")
                    && !input.equalsIgnoreCase("1")
                    && !input.equalsIgnoreCase("0")){
                    JOptionPane.showMessageDialog(this, "Attribute Active "
                                        + "only accept "
                                        + "'Active' or '1' for 'Activated' "
                                        + "and"
                                        + "'Disabled' or '0' for 'Disabled'", 
                                         "Input Error", 
                                         JOptionPane.ERROR_MESSAGE);
                    valid_input = false;
                }
                else{
                    if (input.equalsIgnoreCase("Active")) input = "1";
                    else if (input.equalsIgnoreCase("Disabled")) input = "0";
                }
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
                    JOptionPane.showMessageDialog(this, "Attribute Balance "
                        + "only accept format (float)Balance or "
                        + "(float)Balance1~(float)Balance2", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                    temp = null;
                }
            }
            else if (attribute.equalsIgnoreCase("User ID")){
                attribute = "UserID";
            }
            else if (attribute.equalsIgnoreCase("Date Published")){
                attribute = "Datepublished";
                input = input.split("\\.")[0];
                System.out.println(input);
                valid_datetime = checkDateTime(input);
                if (valid_datetime == -1){
                    JOptionPane.showMessageDialog(this, "Attribute Date Published "
                            + "only accept format "
                            + "(int)dd (int)MM (int) yyy as date "
                            + "and hh:mm:ss as time (time is optional)", 
                              "Input Error", 
                              JOptionPane.ERROR_MESSAGE);
                    valid_input = false;
                }
                else{
                    if (valid_datetime > 0 && valid_datetime < 4){
                        temp = input.split(":");
                        int length = temp.length;
                        if (length == 1) input = input + ":00:00";
                        else if(length == 2) input = input + ":00";
                    }
                }
            }
            if (valid_input == true){
                String condition = "where ";
                if (attribute.equalsIgnoreCase("ID") 
                    || attribute.equalsIgnoreCase("role")){
                    condition = (condition + attribute 
                                + " like '%" + input + "%'");
                }
                else if (attribute.equalsIgnoreCase("Active")){
                    condition = (condition + attribute + "=" + input);
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
                else if (attribute.equalsIgnoreCase("Userid")){
                    condition = (condition + attribute + "='" + input + "'");
                }
                else{
                    String input1 = input + " 00:00:00";
                    String input2 = input + " 23:59:59.999";
                    System.out.println(input);
                    if (valid_datetime > 0){
                        input1 = toSQLDateTime(input);
                        String temp1 = input1.replace(' ', 'T');
                        if (valid_datetime == 1) input2 = LocalDateTime.parse(temp1)
                                                            .plusHours(1).toString();
                        else if (valid_datetime == 2) input2 = LocalDateTime.parse(temp1)
                                                            .plusMinutes(1).toString();
                        else input2 = LocalDateTime.parse(temp1).plusSeconds(1).toString();
                        input2 = input2.replace('T', ' ');
                        input2 = "'" + input2 + "'";
                    }
                    else{
                        input1 = toSQLDateTime(input1);
                        input2 = toSQLDateTime(input2);
                    }
                    condition = (condition + attribute + ">=" + input1
                               + " and " + attribute + "<=" + input2);
                }
                listaccbank = Admin.getAccountsBank(condition);
                showTableAccountBank();
            }
        }
    }
    
    
    public void btnActivityFindbyUsernameActionPerformed(java.awt.event.ActionEvent evt){
        String input = txtfActivityUsername.getText().trim();
        String condition = "";
        if (input.length() > 0){
            condition = "where USERNAME like '%" + input + "%'";
        }
        listah = Admin.getActivityHistory(condition);
        showTableActivityHistory();
    }
    public void btnActivityFindbyDateActionPerformed(java.awt.event.ActionEvent evt){
        String dateStart = txtfActivityDateStart.getText();
        String dateEnd = txtfActivityDateEnd.getText();
        String condition = "where ";
        String mess = "only accept format (int)dd (int)MM (int) yyy as date "
                    + "and hh:mm:ss as time (time is optional)";
        if (dateStart.length() == 0 && dateEnd.length() == 0){
            condition = "";
        }
        else{
            int validDS = 0, validDE = 0;
            String[] temp;
            if (dateStart.length() == 0) dateStart = null;
            else{
                dateStart = dateStart.split("\\.")[0];
                validDS = checkDateTime(dateStart);
                if (validDS == -1){
                    JOptionPane.showMessageDialog(this, "Date Start " + mess, 
                              "Input Error", 
                              JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    if (validDS > 0){
                        temp = dateStart.split(":");
                        int length = temp.length;
                        if (length == 1) dateStart = dateStart + ":00:00";
                        else if(length == 2) dateStart = dateStart + ":00";
                    }
                    else dateStart = dateStart + " 00:00:00";
                    System.out.println(dateStart);
                    dateStart = toSQLDateTime(dateStart).replace(' ', 'T');
                    System.out.println(dateStart);
                    dateStart = LocalDateTime.parse(dateStart).toString().replace('T', ' ');
                    dateStart = "'" + dateStart + "'";
                }
            }
            if (dateEnd.length() == 0) dateEnd = null;
            else{
                dateEnd = dateEnd.split("\\.")[0];
                validDE = checkDateTime(dateEnd);
                if (validDE == -1){
                    JOptionPane.showMessageDialog(this, "Date End " + mess, 
                              "Input Error", 
                              JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    if (validDE > 0){
                        temp = dateEnd.split(":");
                        int length = temp.length;
                        if (length == 1) dateEnd = dateEnd + ":00:00";
                        else if(length == 2) dateEnd = dateEnd + ":00";
                    }
                    else dateEnd = dateEnd + " 00:00:00";
                    dateEnd = toSQLDateTime(dateEnd).replace(' ', 'T');
                    dateEnd = LocalDateTime.parse(dateEnd).plusSeconds(1).toString().replace('T', ' ');
                    dateEnd = "'" + dateEnd + "'";
                }
            }
            temp = cbbActivityLogIn.getSelectedItem().toString().split(" ");
            String activity = "Record_" + temp[0] + temp[1];
            if (dateStart != null && dateEnd == null){
                condition = (condition + activity + ">=" + dateStart);
            }
            else if (dateStart == null && dateEnd != null){
                condition = (condition + activity + "<" + dateEnd);
            }
            else{
                condition = (condition + activity + ">=" + dateStart + 
                            " and " + activity + "<" + dateEnd);
            }
        }
        listah = Admin.getActivityHistory(condition);
        showTableActivityHistory();
    }
    
    public void btnActivityFindbyBankIDActionPerformed(java.awt.event.ActionEvent evt){
        String input = txtfActivityBankID.getText().trim();
        String condition = "";
        if (input.length() > 0){
            condition = "where Customer_ID like '%" + input + "%'";
        }
        listth = Admin.getTransactionHistory(condition);
        showTableTransactionHistory();
    }
    public void btnActivityBankFindbyDateActionPerformed(java.awt.event.ActionEvent evt){
        String dateStart = txtfActivityBankDateStart.getText();
        String dateEnd = txtfActivityBankDateEnd.getText();
        String condition = "where ";
        if (dateStart.length() == 0 && dateEnd.length() == 0){
            condition = "";
        }
        else{
            int validDS = 0, validDE = 0;
            String mess = "only accept format (int)dd (int)MM (int) yyy as date "
                        + "and hh:mm:ss as time (time is optional)";
            String[] temp;
            if (dateStart.length() == 0) dateStart = null;
            else{
                dateStart = dateStart.split("\\.")[0];
                validDS = checkDateTime(dateStart);
                if (validDS == -1){
                    JOptionPane.showMessageDialog(this, "Date Start " + mess, 
                              "Input Error", 
                              JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    if (validDS > 0){
                        temp = dateStart.split(":");
                        int length = temp.length;
                        if (length == 1) dateStart = dateStart + ":00:00";
                        else if(length == 2) dateStart = dateStart + ":00";
                    }
                    else dateStart = dateStart + " 00:00:00";
                    System.out.println(dateStart);
                    dateStart = toSQLDateTime(dateStart).replace(' ', 'T');
                    System.out.println(dateStart);
                    dateStart = LocalDateTime.parse(dateStart).toString().replace('T', ' ');
                    dateStart = "'" + dateStart + "'";
                }
            }
            if (dateEnd.length() == 0) dateEnd = null;
            else{
                dateEnd = dateEnd.split("\\.")[0];
                validDE = checkDateTime(dateEnd);
                if (validDE == -1){
                    JOptionPane.showMessageDialog(this, "Date End " + mess, 
                              "Input Error", 
                              JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    if (validDE > 0){
                        temp = dateEnd.split(":");
                        int length = temp.length;
                        if (length == 1) dateEnd = dateEnd + ":00:00";
                        else if(length == 2) dateEnd = dateEnd + ":00";
                    }
                    else dateEnd = dateEnd + " 00:00:00";
                    dateEnd = toSQLDateTime(dateEnd).replace(' ', 'T');
                    dateEnd = LocalDateTime.parse(dateEnd).plusSeconds(1).toString().replace('T', ' ');
                    dateEnd = "'" + dateEnd + "'";
                }
            }
            String column = "Record";
            if (dateStart != null && dateEnd == null){
                condition = (condition + column + ">=" + dateStart);
            }
            else if (dateStart == null && dateEnd != null){
                condition = (condition + column + "<" + dateEnd);
            }
            else{
                condition = (condition + column + ">=" + dateStart + 
                            " and " + column + "<" + dateEnd);
            }
        }
        listth = Admin.getTransactionHistory(condition);
        showTableTransactionHistory();
    }
    
    
    public void btnTreamntRefreshActionPerformed(java.awt.event.ActionEvent evt){
        txtfTreatmentID.setText("");
        txtfTreatmentName.setText("");
        txtfTreatmentCapacity.setText("");
        txtfTreatmentQuantity.setText("");
        cbbTreatment.setSelectedIndex(0);
        listTMP = Admin.getTreatmentPlace("");
        showTableTreamentPlace();
    }
    
    public void btnTreatmentFindActionPerformed(java.awt.event.ActionEvent evt){
        String attribute = cbbTreatment.getSelectedItem().toString().trim();
        String input = "", condition = "where ", mess = "";
        boolean valid_input = true;
        if (attribute.equalsIgnoreCase("ID")){
            input = txtfTreatmentID.getText().trim();
            condition = condition + "ID='" + input + "'";
        }
        else if (attribute.equalsIgnoreCase("Name")){
            input = txtfTreatmentName.getText().trim();
            condition = condition + "Name like '%" + input + "%'";
        }
        else{
            if (attribute.equalsIgnoreCase("Capacity")){
                input = txtfTreatmentCapacity.getText().trim();
            }
            else input = txtfTreatmentQuantity.getText().trim();
            if (input.length() > 0){
                int inputnum;
                try {
                    inputnum = Integer.parseInt(input);
                    if (inputnum < 0) {
                        mess = attribute + " cannot be negative";
                        valid_input = false;
                    }
                    else{
                        condition = condition + attribute + "=" + input;
                    }
                } catch (NumberFormatException ex){
                    mess = attribute + " must be a non-negative integer";
                    valid_input = false;
                }
            }
        }
        if (input.length() == 0){
            mess = "Please input the " + attribute + " you want to search";
            valid_input = false;
        }
        if (valid_input == true){
            listTMP = Admin.getTreatmentPlace(condition);
            showTableTreamentPlace();
        }
        else{
            JOptionPane.showMessageDialog(this, mess, "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tblTreatmentMouseClicked(java.awt.event.MouseEvent evt){
        int row = tblTreatment.getSelectedRow();
        String ID = tblTreatment.getValueAt(row, 0).toString();
        txtfTreatmentID.setText(ID);
        String Name = "";
        if (tblTreatment.getValueAt(row, 1) != null){
            Name = tblTreatment.getValueAt(row, 1).toString();
        }
        txtfTreatmentName.setText(Name);
        String Capacity = tblTreatment.getValueAt(row, 2).toString();
        txtfTreatmentCapacity.setText(Capacity);
        String Quantity = tblTreatment.getValueAt(row, 3).toString();
        txtfTreatmentQuantity.setText(Quantity);
    }
    
    public void btnTreatmentEditActionPerformed(java.awt.event.ActionEvent evt){
        temptmp = getInputTreamentPlaceData("Edit");
        if (temptmp != null){
            boolean editresult = Admin.editTreatmentPlace(temptmp);
            if (editresult == true){
                for (TreatmentPlace tmp : listTMP){
                    if (tmp.getID().equalsIgnoreCase(temptmp.getID())){ 
                        listTMP.set(listTMP.indexOf(tmp), temptmp);
                        showTableTreamentPlace();
                        break;
                    }
                }
                listTMP.add(temptmp);
                showTableTreamentPlace();
            }
        }
        temptmp = null;
    }
    
    public void btnTreatmentRemoveActionPerformed(java.awt.event.ActionEvent evt){
        temptmp = getInputTreamentPlaceData("Remove");
        if (temptmp != null){
            boolean removers = Admin.removeTreatmentPlace(temptmp.getID());
            if (removers == true){
                txtfTreatmentID.setText("");
                txtfTreatmentName.setText("");
                txtfTreatmentCapacity.setText("");
                txtfTreatmentQuantity.setText("");
                for (TreatmentPlace tmp : listTMP){
                    if (tmp.getID().equalsIgnoreCase(temptmp.getID())){
                        listTMP.remove(tmp);
                        showTableTreamentPlace();
                        break;
                    }
                }
            }
        }
        temptmp = null;
    }
    
    /**
     * Creates new form GUIAdmin
     */
    public AdminManagementPanel() {
        initComponents();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        showTableAccount();
        showTableAccountBank();
        showTableActivityHistory();
        showTableTransactionHistory();
        showTableTreamentPlace();
        AddActionPerformed();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tbdpAdministratorManagement = new javax.swing.JTabbedPane();
        pnlAccountManagement = new javax.swing.JPanel();
        pnlAccountInfor = new javax.swing.JPanel();
        pnlInfor = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        txtfUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        lblState = new javax.swing.JLabel();
        txtfPassword = new javax.swing.JTextField();
        cbbState = new javax.swing.JComboBox<>();
        lblUserID = new javax.swing.JLabel();
        txtfUserID = new javax.swing.JTextField();
        cbbRole = new javax.swing.JComboBox<>();
        pnlButtonInfor = new javax.swing.JPanel();
        btnRemove = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        pnlAccountList = new javax.swing.JPanel();
        srlpList = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        pnlAttribute = new javax.swing.JPanel();
        lblAttribute = new javax.swing.JLabel();
        txtfAttribute = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        cbbAttribute = new javax.swing.JComboBox<>();
        pnlBankAccountManagement = new javax.swing.JPanel();
        pnlAccountBankInfor = new javax.swing.JPanel();
        pnlBankInfor = new javax.swing.JPanel();
        lblBankID = new javax.swing.JLabel();
        txtfBankID = new javax.swing.JTextField();
        lblBankPassword = new javax.swing.JLabel();
        lblBankRole = new javax.swing.JLabel();
        lblBankState = new javax.swing.JLabel();
        txtfBankPassword = new javax.swing.JTextField();
        cbbBankState = new javax.swing.JComboBox<>();
        lblBankUserID = new javax.swing.JLabel();
        txtfBankUserID = new javax.swing.JTextField();
        cbbBankRole = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        lblBankBalanced = new javax.swing.JLabel();
        txtfBankBalanced = new javax.swing.JTextField();
        pnlBankAccount = new javax.swing.JPanel();
        btnBankRemove = new javax.swing.JButton();
        btnBankEdit = new javax.swing.JButton();
        btnBankAdd = new javax.swing.JButton();
        btnBankRefresh = new javax.swing.JButton();
        pnlBankAccountList = new javax.swing.JPanel();
        srlpBanklList = new javax.swing.JScrollPane();
        tblBankList = new javax.swing.JTable();
        pnlBankAccountListAttribute = new javax.swing.JPanel();
        lblBankAttribute = new javax.swing.JLabel();
        txtfBankAttribute = new javax.swing.JTextField();
        btnBankFind = new javax.swing.JButton();
        cbbBankAttribute = new javax.swing.JComboBox<>();
        pnlActivityandTransaction = new javax.swing.JPanel();
        pnlActivityUserDetail = new javax.swing.JPanel();
        lblActivityUsername = new javax.swing.JLabel();
        txtfActivityUsername = new javax.swing.JTextField();
        btnActivityFindbyUsername = new javax.swing.JButton();
        btnActivityFindbyDate = new javax.swing.JButton();
        lblActivityDateStart = new javax.swing.JLabel();
        cbbActivityLogIn = new javax.swing.JComboBox<>();
        txtfActivityDateStart = new javax.swing.JTextField();
        lblActivityDateEnd = new javax.swing.JLabel();
        txtfActivityDateEnd = new javax.swing.JTextField();
        pnlAccountHistory = new javax.swing.JPanel();
        srlpAccountHistory = new javax.swing.JScrollPane();
        tblAccountHistory = new javax.swing.JTable();
        pnlTransactionHistory = new javax.swing.JPanel();
        srlTransactionHistory = new javax.swing.JScrollPane();
        tblTransactionHistory = new javax.swing.JTable();
        pnlAcitivyBankDetail = new javax.swing.JPanel();
        lblActivityBankID = new javax.swing.JLabel();
        txtfActivityBankID = new javax.swing.JTextField();
        btnAcitivyFindbyBankID = new javax.swing.JButton();
        btnActivityBankFindbyDate = new javax.swing.JButton();
        lblActivityBankDateStart = new javax.swing.JLabel();
        txtfActivityBankDateStart = new javax.swing.JTextField();
        lblActivityBankDateEnd = new javax.swing.JLabel();
        txtfActivityBankDateEnd = new javax.swing.JTextField();
        pnlTreatmentManagement = new javax.swing.JPanel();
        pnlTreatment = new javax.swing.JPanel();
        pnlTreamentDetail = new javax.swing.JPanel();
        srlpTreatment = new javax.swing.JScrollPane();
        tblTreatment = new javax.swing.JTable();
        pnlTreatmentInput = new javax.swing.JPanel();
        lblTreatmentID = new javax.swing.JLabel();
        txtfTreatmentID = new javax.swing.JTextField();
        lblTreatmentName = new javax.swing.JLabel();
        txtfTreatmentName = new javax.swing.JTextField();
        lblTreatmentCapacity = new javax.swing.JLabel();
        txtfTreatmentCapacity = new javax.swing.JTextField();
        lblTreatmentQuantity = new javax.swing.JLabel();
        txtfTreatmentQuantity = new javax.swing.JTextField();
        pnlTreatmentButton = new javax.swing.JPanel();
        btnTreatmentRefresh = new javax.swing.JButton();
        btnTreatmentRemove = new javax.swing.JButton();
        btnTreatmentAdd = new javax.swing.JButton();
        btnTreatmentEdit = new javax.swing.JButton();
        cbbTreatment = new javax.swing.JComboBox<>();
        btnTreatmentFind = new javax.swing.JButton();
        pnlTitle = new javax.swing.JPanel();
        btnLogOut = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Project App Covid19");
        setMinimumSize(null);

        tbdpAdministratorManagement.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        pnlAccountInfor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblUsername.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUsername.setText("Username:");

        txtfUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPassword.setText("Password:");

        lblRole.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblRole.setText("Role:");

        lblState.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblState.setText("State:");

        txtfPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfPasswordjTextField1ActionPerformed(evt);
            }
        });

        cbbState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Disabled" }));

        lblUserID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblUserID.setText("User ID:");

        txtfUserID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfUserIDjTextField1ActionPerformed(evt);
            }
        });

        cbbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Manager", "User" }));
        cbbRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInforLayout = new javax.swing.GroupLayout(pnlInfor);
        pnlInfor.setLayout(pnlInforLayout);
        pnlInforLayout.setHorizontalGroup(
            pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInforLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblUserID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfUserID, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(txtfPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtfUsername, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblState, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbRole, 0, 220, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        pnlInforLayout.setVerticalGroup(
            pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInforLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(lblState, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlInforLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbRole, cbbState, lblPassword, lblRole, lblState, lblUserID, txtfPassword, txtfUsername});

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sub.png"))); // NOI18N
        btnRemove.setText("Remove");

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add-user.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonInforLayout = new javax.swing.GroupLayout(pnlButtonInfor);
        pnlButtonInfor.setLayout(pnlButtonInforLayout);
        pnlButtonInforLayout.setHorizontalGroup(
            pnlButtonInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonInforLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlButtonInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlButtonInforLayout.setVerticalGroup(
            pnlButtonInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonInforLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlButtonInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAccountInforLayout = new javax.swing.GroupLayout(pnlAccountInfor);
        pnlAccountInfor.setLayout(pnlAccountInforLayout);
        pnlAccountInforLayout.setHorizontalGroup(
            pnlAccountInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountInforLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlButtonInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlAccountInforLayout.setVerticalGroup(
            pnlAccountInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlButtonInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlAccountList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Password", "Role", "Active", "User ID", "Date published"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList.setPreferredSize(new java.awt.Dimension(1019, 160));
        srlpList.setViewportView(tblList);

        lblAttribute.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAttribute.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAttribute.setText("Choose attribute:");

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnFind.setText("Find");

        cbbAttribute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Username", "Role", "Active", "UserID", "Date Published" }));
        cbbAttribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbAttributeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAttributeLayout = new javax.swing.GroupLayout(pnlAttribute);
        pnlAttribute.setLayout(pnlAttributeLayout);
        pnlAttributeLayout.setHorizontalGroup(
            pnlAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAttributeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAttribute)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAttributeLayout.setVerticalGroup(
            pnlAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAttributeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbAttribute)
                    .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(lblAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtfAttribute))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAccountListLayout = new javax.swing.GroupLayout(pnlAccountList);
        pnlAccountList.setLayout(pnlAccountListLayout);
        pnlAccountListLayout.setHorizontalGroup(
            pnlAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(srlpList))
                .addContainerGap())
        );
        pnlAccountListLayout.setVerticalGroup(
            pnlAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccountListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(srlpList, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAccountManagementLayout = new javax.swing.GroupLayout(pnlAccountManagement);
        pnlAccountManagement.setLayout(pnlAccountManagementLayout);
        pnlAccountManagementLayout.setHorizontalGroup(
            pnlAccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAccountInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAccountList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAccountManagementLayout.setVerticalGroup(
            pnlAccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccountManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAccountInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAccountList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbdpAdministratorManagement.addTab("Account Management", pnlAccountManagement);

        pnlAccountBankInfor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblBankID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBankID.setText("Bank ID:");

        txtfBankID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfBankIDjTextField1ActionPerformed(evt);
            }
        });

        lblBankPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBankPassword.setText("Password:");

        lblBankRole.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBankRole.setText("Role:");

        lblBankState.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBankState.setText("State:");

        txtfBankPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfBankPasswordjTextField1ActionPerformed(evt);
            }
        });

        cbbBankState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Disabled" }));

        lblBankUserID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBankUserID.setText("User ID:");

        txtfBankUserID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfBankUserIDjTextField1ActionPerformed(evt);
            }
        });

        cbbBankRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Manager", "User" }));
        cbbBankRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbBankRoleActionPerformed(evt);
            }
        });

        lblBankBalanced.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBankBalanced.setText("Balanced");

        txtfBankBalanced.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfBankBalancedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBankInforLayout = new javax.swing.GroupLayout(pnlBankInfor);
        pnlBankInfor.setLayout(pnlBankInforLayout);
        pnlBankInforLayout.setHorizontalGroup(
            pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankInforLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblBankPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBankID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(lblBankUserID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfBankUserID, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(txtfBankPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtfBankID, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBankInforLayout.createSequentialGroup()
                        .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblBankState, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(lblBankRole, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(lblBankBalanced, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbBankState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbBankRole, 0, 220, Short.MAX_VALUE)
                            .addComponent(txtfBankBalanced))))
                .addGap(18, 18, 18))
        );
        pnlBankInforLayout.setVerticalGroup(
            pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBankInforLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBankInforLayout.createSequentialGroup()
                        .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfBankID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBankID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBankInforLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblBankBalanced)
                                    .addComponent(txtfBankBalanced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(5, 5, 5)
                        .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbBankState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBankState)
                            .addComponent(txtfBankPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBankPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfBankUserID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblBankUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblBankRole))))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbBankRole, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlBankInforLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbBankRole, cbbBankState, lblBankBalanced, lblBankID, lblBankPassword, lblBankRole, lblBankState, lblBankUserID, txtfBankBalanced, txtfBankID, txtfBankPassword, txtfBankUserID});

        btnBankRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sub.png"))); // NOI18N
        btnBankRemove.setText("Remove");

        btnBankEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        btnBankEdit.setText("Edit");
        btnBankEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBankEditjButton2ActionPerformed(evt);
            }
        });

        btnBankAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnBankAdd.setText("Add");
        btnBankAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBankAddjButton3ActionPerformed(evt);
            }
        });

        btnBankRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnBankRefresh.setText("Refresh");
        btnBankRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBankRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBankAccountLayout = new javax.swing.GroupLayout(pnlBankAccount);
        pnlBankAccount.setLayout(pnlBankAccountLayout);
        pnlBankAccountLayout.setHorizontalGroup(
            pnlBankAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBankAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBankRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(btnBankAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBankAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBankEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBankRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlBankAccountLayout.setVerticalGroup(
            pnlBankAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBankRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnBankEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBankAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBankAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBankRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAccountBankInforLayout = new javax.swing.GroupLayout(pnlAccountBankInfor);
        pnlAccountBankInfor.setLayout(pnlAccountBankInforLayout);
        pnlAccountBankInforLayout.setHorizontalGroup(
            pnlAccountBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountBankInforLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBankInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBankAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlAccountBankInforLayout.setVerticalGroup(
            pnlAccountBankInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBankAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBankInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
        );

        pnlBankAccountList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblBankList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Bank ID", "Password", "Role", "Active", "Balance", "User ID", "Date Published"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBankList.setPreferredSize(new java.awt.Dimension(1019, 160));
        srlpBanklList.setViewportView(tblBankList);

        lblBankAttribute.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblBankAttribute.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBankAttribute.setText("Choose attribute:");

        btnBankFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnBankFind.setText("Find");

        cbbBankAttribute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bank ID", "Role", "Active", "Balance", "User ID", "Date Published" }));

        javax.swing.GroupLayout pnlBankAccountListAttributeLayout = new javax.swing.GroupLayout(pnlBankAccountListAttribute);
        pnlBankAccountListAttribute.setLayout(pnlBankAccountListAttributeLayout);
        pnlBankAccountListAttributeLayout.setHorizontalGroup(
            pnlBankAccountListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankAccountListAttributeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBankAttribute)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbBankAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfBankAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBankFind, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlBankAccountListAttributeLayout.setVerticalGroup(
            pnlBankAccountListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBankAccountListAttributeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankAccountListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbBankAttribute)
                    .addComponent(btnBankFind, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(lblBankAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtfBankAttribute))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlBankAccountListLayout = new javax.swing.GroupLayout(pnlBankAccountList);
        pnlBankAccountList.setLayout(pnlBankAccountListLayout);
        pnlBankAccountListLayout.setHorizontalGroup(
            pnlBankAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankAccountListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBankAccountListAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(srlpBanklList))
                .addContainerGap())
        );
        pnlBankAccountListLayout.setVerticalGroup(
            pnlBankAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBankAccountListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBankAccountListAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(srlpBanklList, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlBankAccountManagementLayout = new javax.swing.GroupLayout(pnlBankAccountManagement);
        pnlBankAccountManagement.setLayout(pnlBankAccountManagementLayout);
        pnlBankAccountManagementLayout.setHorizontalGroup(
            pnlBankAccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankAccountManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankAccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAccountBankInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBankAccountList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlBankAccountManagementLayout.setVerticalGroup(
            pnlBankAccountManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBankAccountManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAccountBankInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBankAccountList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbdpAdministratorManagement.addTab("Bank Account Management", pnlBankAccountManagement);

        pnlActivityUserDetail.setPreferredSize(new java.awt.Dimension(572, 124));

        lblActivityUsername.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblActivityUsername.setText("Username:");

        txtfActivityUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfActivityUsernameActionPerformed(evt);
            }
        });

        btnActivityFindbyUsername.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActivityFindbyUsername.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnActivityFindbyUsername.setText("Find by Username");

        btnActivityFindbyDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActivityFindbyDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnActivityFindbyDate.setText("Find by Date");

        lblActivityDateStart.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblActivityDateStart.setText("Date Start:");

        cbbActivityLogIn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Log In", "Log Out" }));
        cbbActivityLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbActivityLogInActionPerformed(evt);
            }
        });

        lblActivityDateEnd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblActivityDateEnd.setText("Date End");

        javax.swing.GroupLayout pnlActivityUserDetailLayout = new javax.swing.GroupLayout(pnlActivityUserDetail);
        pnlActivityUserDetail.setLayout(pnlActivityUserDetailLayout);
        pnlActivityUserDetailLayout.setHorizontalGroup(
            pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActivityUserDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblActivityDateStart, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(lblActivityUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblActivityDateEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlActivityUserDetailLayout.createSequentialGroup()
                        .addGroup(pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtfActivityDateStart, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(txtfActivityDateEnd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbActivityLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActivityFindbyDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlActivityUserDetailLayout.createSequentialGroup()
                        .addComponent(txtfActivityUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActivityFindbyUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlActivityUserDetailLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnActivityFindbyDate, btnActivityFindbyUsername});

        pnlActivityUserDetailLayout.setVerticalGroup(
            pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActivityUserDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblActivityUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfActivityUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActivityFindbyUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlActivityUserDetailLayout.createSequentialGroup()
                        .addGroup(pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblActivityDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfActivityDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlActivityUserDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblActivityDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfActivityDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnActivityFindbyDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbActivityLogIn))
                .addContainerGap())
        );

        pnlAccountHistory.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("ACCOUNTHISTORY")));

        tblAccountHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Login Record", "Logout Record"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        srlpAccountHistory.setViewportView(tblAccountHistory);

        javax.swing.GroupLayout pnlAccountHistoryLayout = new javax.swing.GroupLayout(pnlAccountHistory);
        pnlAccountHistory.setLayout(pnlAccountHistoryLayout);
        pnlAccountHistoryLayout.setHorizontalGroup(
            pnlAccountHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srlpAccountHistory)
        );
        pnlAccountHistoryLayout.setVerticalGroup(
            pnlAccountHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srlpAccountHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
        );

        pnlTransactionHistory.setBorder(javax.swing.BorderFactory.createTitledBorder("TRANSACTION HISTORY"));

        tblTransactionHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Bank ID", "Money", "Content", "Date"
            }
        ));
        srlTransactionHistory.setViewportView(tblTransactionHistory);
        if (tblTransactionHistory.getColumnModel().getColumnCount() > 0) {
            tblTransactionHistory.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblTransactionHistory.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblTransactionHistory.getColumnModel().getColumn(2).setPreferredWidth(140);
            tblTransactionHistory.getColumnModel().getColumn(3).setPreferredWidth(80);
        }

        javax.swing.GroupLayout pnlTransactionHistoryLayout = new javax.swing.GroupLayout(pnlTransactionHistory);
        pnlTransactionHistory.setLayout(pnlTransactionHistoryLayout);
        pnlTransactionHistoryLayout.setHorizontalGroup(
            pnlTransactionHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srlTransactionHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );
        pnlTransactionHistoryLayout.setVerticalGroup(
            pnlTransactionHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srlTransactionHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pnlAcitivyBankDetail.setPreferredSize(new java.awt.Dimension(572, 124));

        lblActivityBankID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblActivityBankID.setText("Bank ID:");

        txtfActivityBankID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfActivityBankIDActionPerformed(evt);
            }
        });

        btnAcitivyFindbyBankID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAcitivyFindbyBankID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnAcitivyFindbyBankID.setText("Find by BankID");

        btnActivityBankFindbyDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActivityBankFindbyDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnActivityBankFindbyDate.setText("Find by Date");

        lblActivityBankDateStart.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblActivityBankDateStart.setText("Date Start:");

        lblActivityBankDateEnd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblActivityBankDateEnd.setText("Date End:");

        javax.swing.GroupLayout pnlAcitivyBankDetailLayout = new javax.swing.GroupLayout(pnlAcitivyBankDetail);
        pnlAcitivyBankDetail.setLayout(pnlAcitivyBankDetailLayout);
        pnlAcitivyBankDetailLayout.setHorizontalGroup(
            pnlAcitivyBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcitivyBankDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAcitivyBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblActivityBankDateStart, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(lblActivityBankID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblActivityBankDateEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAcitivyBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtfActivityBankDateEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(txtfActivityBankID, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(txtfActivityBankDateStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAcitivyBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnActivityBankFindbyDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAcitivyFindbyBankID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlAcitivyBankDetailLayout.setVerticalGroup(
            pnlAcitivyBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcitivyBankDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAcitivyBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAcitivyFindbyBankID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtfActivityBankID)
                    .addComponent(lblActivityBankID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAcitivyBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnActivityBankFindbyDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAcitivyBankDetailLayout.createSequentialGroup()
                        .addGroup(pnlAcitivyBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblActivityBankDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfActivityBankDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAcitivyBankDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblActivityBankDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfActivityBankDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlActivityandTransactionLayout = new javax.swing.GroupLayout(pnlActivityandTransaction);
        pnlActivityandTransaction.setLayout(pnlActivityandTransactionLayout);
        pnlActivityandTransactionLayout.setHorizontalGroup(
            pnlActivityandTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActivityandTransactionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlActivityandTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlActivityUserDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addComponent(pnlAccountHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlActivityandTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTransactionHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAcitivyBankDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)))
        );
        pnlActivityandTransactionLayout.setVerticalGroup(
            pnlActivityandTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActivityandTransactionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlActivityandTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAcitivyBankDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlActivityUserDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlActivityandTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAccountHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTransactionHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tbdpAdministratorManagement.addTab("Activity and Transaction History", pnlActivityandTransaction);

        pnlTreatment.setPreferredSize(new java.awt.Dimension(1100, 403));

        pnlTreamentDetail.setPreferredSize(new java.awt.Dimension(725, 346));

        tblTreatment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Treatment Name", "Capacity", "Quantity"
            }
        ));
        tblTreatment.setPreferredSize(new java.awt.Dimension(705, 64));
        srlpTreatment.setViewportView(tblTreatment);

        javax.swing.GroupLayout pnlTreamentDetailLayout = new javax.swing.GroupLayout(pnlTreamentDetail);
        pnlTreamentDetail.setLayout(pnlTreamentDetailLayout);
        pnlTreamentDetailLayout.setHorizontalGroup(
            pnlTreamentDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTreamentDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(srlpTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTreamentDetailLayout.setVerticalGroup(
            pnlTreamentDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTreamentDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(srlpTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlTreatmentInput.setPreferredSize(new java.awt.Dimension(490, 120));

        lblTreatmentID.setText("ID:");

        lblTreatmentName.setText("Treatment Name:");

        lblTreatmentCapacity.setText("Capacity");

        lblTreatmentQuantity.setText("Quantity");

        javax.swing.GroupLayout pnlTreatmentInputLayout = new javax.swing.GroupLayout(pnlTreatmentInput);
        pnlTreatmentInput.setLayout(pnlTreatmentInputLayout);
        pnlTreatmentInputLayout.setHorizontalGroup(
            pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTreatmentInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTreatmentQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTreatmentCapacity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTreatmentID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTreatmentName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfTreatmentName)
                    .addComponent(txtfTreatmentID)
                    .addComponent(txtfTreatmentCapacity)
                    .addComponent(txtfTreatmentQuantity))
                .addContainerGap())
        );
        pnlTreatmentInputLayout.setVerticalGroup(
            pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTreatmentInputLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfTreatmentID)
                    .addComponent(lblTreatmentID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfTreatmentName)
                    .addComponent(lblTreatmentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfTreatmentCapacity)
                    .addComponent(lblTreatmentCapacity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfTreatmentQuantity)
                    .addComponent(lblTreatmentQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTreatmentRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        btnTreatmentRefresh.setText("Refresh");

        btnTreatmentRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sub.png"))); // NOI18N
        btnTreatmentRemove.setText("Remove");

        btnTreatmentAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnTreatmentAdd.setText("Add");
        btnTreatmentAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTreatmentAddActionPerformed(evt);
            }
        });

        btnTreatmentEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        btnTreatmentEdit.setText("Edit");

        cbbTreatment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Capacity", "Quantity" }));

        btnTreatmentFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnTreatmentFind.setText("Find");

        javax.swing.GroupLayout pnlTreatmentButtonLayout = new javax.swing.GroupLayout(pnlTreatmentButton);
        pnlTreatmentButton.setLayout(pnlTreatmentButtonLayout);
        pnlTreatmentButtonLayout.setHorizontalGroup(
            pnlTreatmentButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTreatmentButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTreatmentButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTreatmentEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTreatmentAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbTreatment, 0, 229, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(pnlTreatmentButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTreatmentRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTreatmentRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(btnTreatmentFind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTreatmentButtonLayout.setVerticalGroup(
            pnlTreatmentButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTreatmentButtonLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(pnlTreatmentButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTreatmentFind, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTreatmentButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTreatmentAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTreatmentRemove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(pnlTreatmentButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTreatmentEdit)
                    .addComponent(btnTreatmentRefresh))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnlTreatmentButtonLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnTreatmentAdd, btnTreatmentEdit, btnTreatmentFind, btnTreatmentRefresh, btnTreatmentRemove, cbbTreatment});

        javax.swing.GroupLayout pnlTreatmentLayout = new javax.swing.GroupLayout(pnlTreatment);
        pnlTreatment.setLayout(pnlTreatmentLayout);
        pnlTreatmentLayout.setHorizontalGroup(
            pnlTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTreatmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTreatmentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTreatmentInput, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTreamentDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTreatmentLayout.setVerticalGroup(
            pnlTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTreatmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTreamentDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addGroup(pnlTreatmentLayout.createSequentialGroup()
                        .addComponent(pnlTreatmentInput, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTreatmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlTreatmentManagementLayout = new javax.swing.GroupLayout(pnlTreatmentManagement);
        pnlTreatmentManagement.setLayout(pnlTreatmentManagementLayout);
        pnlTreatmentManagementLayout.setHorizontalGroup(
            pnlTreatmentManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTreatmentManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTreatmentManagementLayout.setVerticalGroup(
            pnlTreatmentManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTreatmentManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdpAdministratorManagement.addTab("Treatment Management", pnlTreatmentManagement);

        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout.png"))); // NOI18N
        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/admin-48.png"))); // NOI18N
        lblTitle.setText("Administrator Management");
        lblTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbdpAdministratorManagement))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbdpAdministratorManagement)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        this.setVisible(false);
        new SignInFrame().setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnBankRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBankRefreshActionPerformed
        // TODO add your handling code here:
        txtfBankID.setText("");
        txtfBankPassword.setText("");
        cbbBankRole.setSelectedIndex(0);
        cbbBankState.setSelectedIndex(0);
        txtfBankBalanced.setText("");
        txtfBankUserID.setText("");
        cbbBankAttribute.setSelectedIndex(0);
        txtfBankAttribute.setText("");
        listaccbank = Admin.getAccountsBank("");
        showTableAccountBank();
    }//GEN-LAST:event_btnBankRefreshActionPerformed

    private void btnBankAddjButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBankAddjButton3ActionPerformed
        // TODO add your handling code here:
        tempaccbank = getInputAccountBankData("Add");
        if (tempaccbank != null){
            int Activate = 1 - cbbBankState.getSelectedIndex();
            LocalDateTime DatePublished = LocalDateTime.now();
            tempaccbank.setAccountBank(tempaccbank.getBankid(), tempaccbank.getPassword(), 
                            tempaccbank.getRole(), Activate, tempaccbank.getBalance(), 
                            tempaccbank.getUserid(), DatePublished);
            boolean addresult = Admin.addAccountBank(tempaccbank);
            if (addresult == true){
                listaccbank.add(tempaccbank);
                showTableAccountBank();
            }
        }
        tempaccbank = null;
    }//GEN-LAST:event_btnBankAddjButton3ActionPerformed

    private void btnBankEditjButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBankEditjButton2ActionPerformed
        // TODO add your handling code here:
        tempaccbank = getInputAccountBankData("Edit");
        if (tempaccbank != null){
            String ID = tempaccbank.getBankid();
            int Activate = 1 - cbbBankState.getSelectedIndex();
            if (Activate == 0 && ID.equalsIgnoreCase("admin")){
                JOptionPane.showMessageDialog(null, 
                        "Account bank 'admin' must always activated", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                ArrayList<AccountBank> listtemp = Admin.getAccountsBank("where ID='" 
                                                                + ID + "'");
                for (AccountBank accbank : listtemp){
                    if (accbank.getBankid().equalsIgnoreCase(ID)){
                        tempaccbank.setAccountBank(tempaccbank.getBankid(), 
                                            tempaccbank.getPassword(), 
                                            tempaccbank.getRole(), Activate, 
                                            tempaccbank.getBalance(), tempaccbank.getUserid(), 
                                            accbank.getDatePublished());
                    }
                }
                boolean editresult = Admin.editAccountBank(tempaccbank);
                if (editresult == true){
                    for (AccountBank accbank : listaccbank){
                        if (accbank.getBankid().equalsIgnoreCase(ID)){ 
                            listaccbank.set(listaccbank.indexOf(accbank), tempaccbank);
                            showTableAccountBank();
                            break;
                        }
                    }
                }
                else System.out.println("1");
            }
        }
        tempaccbank = null;
    }//GEN-LAST:event_btnBankEditjButton2ActionPerformed

    private void cbbBankRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbBankRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbBankRoleActionPerformed

    private void txtfBankUserIDjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfBankUserIDjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfBankUserIDjTextField1ActionPerformed

    private void txtfBankPasswordjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfBankPasswordjTextField1ActionPerformed
        // TODO add your handling code here:
        String text = txtfBankPassword.getText();
        if (text.length() > 0){
            try {
                txtfBankPassword.setText(SignInFrame.toHexString
                                  (SignInFrame.getSHA(text)));
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }//GEN-LAST:event_txtfBankPasswordjTextField1ActionPerformed

    private void txtfBankIDjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfBankIDjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfBankIDjTextField1ActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        txtfUsername.setText("");
        txtfPassword.setText("");
        cbbRole.setSelectedIndex(0);
        txtfUserID.setText("");
        cbbState.setSelectedIndex(0);
        cbbAttribute.setSelectedIndex(0);
        txtfAttribute.setText("");
        listacc = Admin.getAccounts("");
        showTableAccount();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        tempacc = getInputAccountData("Add");
        if (tempacc != null){
            int Activate = 1 - cbbState.getSelectedIndex();
            LocalDateTime DatePublished = LocalDateTime.now();
            tempacc.setAccount(tempacc.getUsername(), tempacc.getPassword(), 
                            tempacc.getRole(), tempacc.getUserid(), 
                            Activate, DatePublished);
            boolean addresult = Admin.addAccount(tempacc);
            if (addresult == true){
                listacc.add(tempacc);
                showTableAccount();
            }
        }
        tempacc = null;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tempacc = getInputAccountData("Edit");
        if (tempacc != null){
            String Username = tempacc.getUsername();
            int Activate = 1 - cbbState.getSelectedIndex();
            if (Activate == 0 && Username.equalsIgnoreCase("admin")){
                JOptionPane.showMessageDialog(null, 
                        "Account 'admin' must always activated", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                ArrayList<Account> listtemp = Admin.getAccounts("where USERNAME='" 
                                                                + Username + "'");
                for (Account acc : listtemp){
                    if (acc.getUsername().equalsIgnoreCase(Username)){
                        tempacc.setAccount(tempacc.getUsername(), 
                                            tempacc.getPassword(), 
                                            tempacc.getRole(), tempacc.getUserid(), 
                                            Activate, acc.getDatePublished());
                    }
                }
                boolean editresult = Admin.editAccount(tempacc);
                if (editresult == true){
                    for (Account acc : listacc){
                        if (acc.getUsername().equalsIgnoreCase(Username)){ 
                            listacc.set(listacc.indexOf(acc), tempacc);
                            showTableAccount();
                            break;
                        }
                    }
                }
            }
        }
        tempacc = null;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbRoleActionPerformed

    private void txtfUserIDjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfUserIDjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfUserIDjTextField1ActionPerformed

    private void txtfPasswordjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfPasswordjTextField1ActionPerformed
        // TODO add your handling code here:
        String text = txtfPassword.getText();
        if (text.length() > 0){
            try {
                txtfPassword.setText(SignInFrame.toHexString
                                  (SignInFrame.getSHA(text)));
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }//GEN-LAST:event_txtfPasswordjTextField1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnTreatmentAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTreatmentAddActionPerformed
        // TODO add your handling code here:
        temptmp = getInputTreamentPlaceData("Add");
        if (temptmp != null){
            boolean addresult = Admin.addTreatmentPlace(temptmp);
            if (addresult == true){
                listTMP.add(temptmp);
                showTableTreamentPlace();
            }
        }
        temptmp = null;
    }//GEN-LAST:event_btnTreatmentAddActionPerformed

    private void txtfActivityBankIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfActivityBankIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfActivityBankIDActionPerformed

    private void cbbActivityLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbActivityLogInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbActivityLogInActionPerformed

    private void txtfActivityUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfActivityUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfActivityUsernameActionPerformed

    private void txtfBankBalancedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfBankBalancedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfBankBalancedActionPerformed

    private void cbbAttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbAttributeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbAttributeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminManagementPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminManagementPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminManagementPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminManagementPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminManagementPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcitivyFindbyBankID;
    private javax.swing.JButton btnActivityBankFindbyDate;
    private javax.swing.JButton btnActivityFindbyDate;
    private javax.swing.JButton btnActivityFindbyUsername;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBankAdd;
    private javax.swing.JButton btnBankEdit;
    private javax.swing.JButton btnBankFind;
    private javax.swing.JButton btnBankRefresh;
    private javax.swing.JButton btnBankRemove;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnTreatmentAdd;
    private javax.swing.JButton btnTreatmentEdit;
    private javax.swing.JButton btnTreatmentFind;
    private javax.swing.JButton btnTreatmentRefresh;
    private javax.swing.JButton btnTreatmentRemove;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbActivityLogIn;
    private javax.swing.JComboBox<String> cbbAttribute;
    private javax.swing.JComboBox<String> cbbBankAttribute;
    private javax.swing.JComboBox<String> cbbBankRole;
    private javax.swing.JComboBox<String> cbbBankState;
    private javax.swing.JComboBox<String> cbbRole;
    private javax.swing.JComboBox<String> cbbState;
    private javax.swing.JComboBox<String> cbbTreatment;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblActivityBankDateEnd;
    private javax.swing.JLabel lblActivityBankDateStart;
    private javax.swing.JLabel lblActivityBankID;
    private javax.swing.JLabel lblActivityDateEnd;
    private javax.swing.JLabel lblActivityDateStart;
    private javax.swing.JLabel lblActivityUsername;
    private javax.swing.JLabel lblAttribute;
    private javax.swing.JLabel lblBankAttribute;
    private javax.swing.JLabel lblBankBalanced;
    private javax.swing.JLabel lblBankID;
    private javax.swing.JLabel lblBankPassword;
    private javax.swing.JLabel lblBankRole;
    private javax.swing.JLabel lblBankState;
    private javax.swing.JLabel lblBankUserID;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblState;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTreatmentCapacity;
    private javax.swing.JLabel lblTreatmentID;
    private javax.swing.JLabel lblTreatmentName;
    private javax.swing.JLabel lblTreatmentQuantity;
    private javax.swing.JLabel lblUserID;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlAccountBankInfor;
    private javax.swing.JPanel pnlAccountHistory;
    private javax.swing.JPanel pnlAccountInfor;
    private javax.swing.JPanel pnlAccountList;
    private javax.swing.JPanel pnlAccountManagement;
    private javax.swing.JPanel pnlAcitivyBankDetail;
    private javax.swing.JPanel pnlActivityUserDetail;
    private javax.swing.JPanel pnlActivityandTransaction;
    private javax.swing.JPanel pnlAttribute;
    private javax.swing.JPanel pnlBankAccount;
    private javax.swing.JPanel pnlBankAccountList;
    private javax.swing.JPanel pnlBankAccountListAttribute;
    private javax.swing.JPanel pnlBankAccountManagement;
    private javax.swing.JPanel pnlBankInfor;
    private javax.swing.JPanel pnlButtonInfor;
    private javax.swing.JPanel pnlInfor;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTransactionHistory;
    private javax.swing.JPanel pnlTreamentDetail;
    private javax.swing.JPanel pnlTreatment;
    private javax.swing.JPanel pnlTreatmentButton;
    private javax.swing.JPanel pnlTreatmentInput;
    private javax.swing.JPanel pnlTreatmentManagement;
    private javax.swing.JScrollPane srlTransactionHistory;
    private javax.swing.JScrollPane srlpAccountHistory;
    private javax.swing.JScrollPane srlpBanklList;
    private javax.swing.JScrollPane srlpList;
    private javax.swing.JScrollPane srlpTreatment;
    private javax.swing.JTabbedPane tbdpAdministratorManagement;
    private javax.swing.JTable tblAccountHistory;
    private javax.swing.JTable tblBankList;
    private javax.swing.JTable tblList;
    private javax.swing.JTable tblTransactionHistory;
    private javax.swing.JTable tblTreatment;
    private javax.swing.JTextField txtfActivityBankDateEnd;
    private javax.swing.JTextField txtfActivityBankDateStart;
    private javax.swing.JTextField txtfActivityBankID;
    private javax.swing.JTextField txtfActivityDateEnd;
    private javax.swing.JTextField txtfActivityDateStart;
    private javax.swing.JTextField txtfActivityUsername;
    private javax.swing.JTextField txtfAttribute;
    private javax.swing.JTextField txtfBankAttribute;
    private javax.swing.JTextField txtfBankBalanced;
    private javax.swing.JTextField txtfBankID;
    private javax.swing.JTextField txtfBankPassword;
    private javax.swing.JTextField txtfBankUserID;
    private javax.swing.JTextField txtfPassword;
    private javax.swing.JTextField txtfTreatmentCapacity;
    private javax.swing.JTextField txtfTreatmentID;
    private javax.swing.JTextField txtfTreatmentName;
    private javax.swing.JTextField txtfTreatmentQuantity;
    private javax.swing.JTextField txtfUserID;
    private javax.swing.JTextField txtfUsername;
    // End of variables declaration//GEN-END:variables
}
