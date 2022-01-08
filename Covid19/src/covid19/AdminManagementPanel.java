/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package covid19;

import covid19.AccountFrame.SignInFrame;
import Account.*;
import Admin.Admin;
import Helper.*;
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
    Comparators com = new Comparators();
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
        listacc.sort(com.accCom);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "Username", "Password", "Role", "UserID", "Activated", 
            "Date Pulished"
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
        listaccbank.sort(com.accbCom);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "BankID", "Password", "Role", "Activated", "Balance", "UserID", 
            "Date Pulished"
        });
        for (AccountBank accbnk : listaccbank){
            model.addRow(new Object[]{
                accbnk.getBankid(), accbnk.getPassword(), accbnk.getRole(), 
                accbnk.getState(), String.format("%.3f",accbnk.getBalance()), accbnk.getUserid(), 
                DateFormatter.parse(accbnk.getDatePublished())
            });
        }
        tblBankList.setModel(model);
    }
    
    public void showTableActivityHistory(){
        listah.sort(com.ahCom);
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
        listth.sort(com.thCom);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "BankID", "Money", "Content", "Date"
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
        listTMP.sort(com.tpCom);
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
    public Account getInputAccountData(){
        String Username = txtfUsername.getText().trim();
        String Password = new String(txtfPassword.getPassword());
        String Role = cbbRole.getSelectedItem().toString();
        String UserID = txtfUserID.getText().trim();
        if (UserID.length() == 0) UserID = null;
        int Activate = 1 - cbbState.getSelectedIndex();
        LocalDateTime DatePublished = LocalDateTime.now();
        Account acc = new Account();
        acc.setAccount(Username, Password, Role, UserID, Activate, DatePublished);
        return acc;
    }
    
    /*Get and check if input is valid for Account Bank*/
    public AccountBank getInputAccountBankData(){
        String BankID = txtfBankID.getText().trim();
        String Password = new String(txtfBankPassword.getPassword());
        String Role = cbbBankRole.getSelectedItem().toString();
        String UserID = txtfBankUserID.getText().trim();
        if (UserID.length() == 0) UserID = null;
        int Activate = 1 - cbbBankState.getSelectedIndex();
        float Balance = 0;
        try{
            Balance = Float.parseFloat(txtfBankBalanced.getText().trim());
        } catch (NumberFormatException ex){
            Balance = 0;
        }
        LocalDateTime DatePublished = LocalDateTime.now();
        AccountBank accbnk = new AccountBank();
        accbnk.setAccountBank(BankID, Password, Role, Activate, Balance, UserID, DatePublished);
        return accbnk;
    }
    
    /*Get and check if input is valid for Treatment Place*/
    public TreatmentPlace getInputTreamentPlaceData(){
        String ID = txtfTreatmentID.getText().trim();
        String Name = txtfTreatmentName.getText().trim();
        int Capacity;
        try{
            Capacity = Integer.parseInt(txtfTreatmentCapacity.getText().trim());
            if (Capacity < 0) Capacity = 0;
        } catch (NumberFormatException ex){
            Capacity = 0;
        }
        int Quantity;
        try{
            Quantity = Integer.parseInt(txtfTreatmentQuantity.getText().trim());
            if (Quantity < 0) Quantity = 0;
        } catch (NumberFormatException ex){
            Quantity = 0;
        }
        TreatmentPlace tmp = new TreatmentPlace();
        tmp.setTreatmentPlace(ID, Name, Capacity, Quantity);
        return tmp;
    }
    
    /*Check password before encrypt*/
    boolean CheckPass(String pass){
        boolean validPass = true;
        if (pass.length() < 6){
            validPass = false;
        }
        else{
            int Upper = 0; int Lower = 0; int Digit = 0;
            int length = pass.length();
            for (int i = 0; i < length; i ++){
                if (pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z'){
                    Upper += 1;
                }
                else if (pass.charAt(i) >= 'a' && pass.charAt(i) <= 'z'){
                    Lower += 1;
                }
                else if (pass.charAt(i) >= '0' && pass.charAt(i) <= '9'){
                    Digit += 1;
                }
            }
            if (Upper == 0 || Lower == 0 || Digit == 0){
                    validPass = false;
            }
        }
        return validPass;
    }
    
    
    /*Add needed action performed for buttons*/
    void AddActionPerformed(){
        /*For account tab*/
        txtfPassword.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfPasswordActionPerformed(evt);
            }
        });
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
        txtfBankPassword.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfBankPasswordActionPerformed(evt);
            }
        });
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
    
    
    public void txtfPasswordActionPerformed(java.awt.event.ActionEvent evt){
        String text = new String(txtfPassword.getPassword());
        if (text.length() > 0){
            boolean validPass = CheckPass(text);
            if (validPass == true){
                try {
                    txtfPassword.setText(SignInFrame.toHexString
                                      (SignInFrame.getSHA(text)));
                } catch (NoSuchAlgorithmException ex) {
                    System.out.println("Error: " + ex);
                }
            }
            else{
                MessageDialog.showErrorDialog(this, "Password must have at least 6 characters\n"
                                            + "and must have at least 1 uppercase, 1 lowercase and 1 digit", 
                                            "Invalid password.");
            }
        }
    }
    
    public void btnRemoveActionPerformed(java.awt.event.ActionEvent evt){
        tempacc = getInputAccountData();
        String mess = Admin.removeAccount(tempacc);
        if (mess == null){
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
        else{
            MessageDialog.showErrorDialog(this, mess, "Error while removing account.");
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
        String findwith = cbbAttribute.getSelectedItem().toString() + ":" 
                        + txtfAttribute.getText().trim();
        String condition = Admin.make_condition(findwith);
        String[] temp = condition.split(":");
        if (temp[0].equalsIgnoreCase("Error")){
            MessageDialog.showErrorDialog(this, condition, "Invalid input found.");
        }
        else{
            listacc = Admin.getAccounts(condition);
            showTableAccount();
        }
    }
    
    
    public void txtfBankPasswordActionPerformed(java.awt.event.ActionEvent evt){
        String text = new String(txtfBankPassword.getPassword());
        if (text.length() > 0){
            boolean validPass = CheckPass(text);
            if (validPass == true){
                try {
                    txtfPassword.setText(SignInFrame.toHexString
                                      (SignInFrame.getSHA(text)));
                } catch (NoSuchAlgorithmException ex) {
                    System.out.println("Error: " + ex);
                }
            }
            else{
                MessageDialog.showErrorDialog(this, "Password must have at least 6 characters\n"
                                            + "and must have at least 1 uppercase, 1 lowercase and 1 digit", 
                                            "Invalid password.");
            }
        }
    }
    
    public void btnBankRemoveActionPerformed(java.awt.event.ActionEvent evt){
        tempaccbank = getInputAccountBankData();
        String mess = Admin.removeAccountBank(tempaccbank);
        if (mess == null){
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
        else{
            MessageDialog.showErrorDialog(this, mess, "Error while removing account bank.");
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
        String findwith = cbbBankAttribute.getSelectedItem().toString() + ":" 
                        + txtfBankAttribute.getText().trim();
        String condition = Admin.make_condition(findwith);
        String[] temp = condition.split(":");
        if (temp[0].equalsIgnoreCase("Error")){
            MessageDialog.showErrorDialog(this, condition, "Invalid input found.");
        }
        else{
            listaccbank = Admin.getAccountsBank(condition);
            showTableAccountBank();
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
                validDS = Admin.checkDateTime(dateStart);
                if (validDS == -1){
                    MessageDialog.showErrorDialog(this, "Date Start " + mess, "Invalid input found");
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
                    dateStart = Admin.toSQLDateTime(dateStart).replace(' ', 'T');
                    dateStart = LocalDateTime.parse(dateStart).toString().replace('T', ' ');
                    dateStart = "'" + dateStart + "'";
                }
            }
            if (dateEnd.length() == 0) dateEnd = null;
            else{
                dateEnd = dateEnd.split("\\.")[0];
                validDE = Admin.checkDateTime(dateEnd);
                if (validDE == -1){
                    MessageDialog.showErrorDialog(this, "Date End " + mess, "Invalid input found");
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
                    dateEnd = Admin.toSQLDateTime(dateEnd).replace(' ', 'T');
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
                validDS = Admin.checkDateTime(dateStart);
                if (validDS == -1){
                    MessageDialog.showErrorDialog(this, "Date Start " + mess, "Invalid input found");
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
                    dateStart = Admin.toSQLDateTime(dateStart).replace(' ', 'T');
                    System.out.println(dateStart);
                    dateStart = LocalDateTime.parse(dateStart).toString().replace('T', ' ');
                    dateStart = "'" + dateStart + "'";
                }
            }
            if (dateEnd.length() == 0) dateEnd = null;
            else{
                dateEnd = dateEnd.split("\\.")[0];
                validDE = Admin.checkDateTime(dateEnd);
                if (validDE == -1){
                    MessageDialog.showErrorDialog(this, "Date End " + mess, "Invalid input found");
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
                    dateEnd = Admin.toSQLDateTime(dateEnd).replace(' ', 'T');
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
            condition = condition + "Name like N'%" + input + "%'";
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
            MessageDialog.showErrorDialog(this, mess, "Invalid input found.");
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
        temptmp = getInputTreamentPlaceData();
        String mess = Admin.editTreatmentPlace(temptmp);
        if (mess == null){
            for (TreatmentPlace tmp : listTMP){
                if (tmp.getID().equalsIgnoreCase(temptmp.getID())){ 
                    listTMP.set(listTMP.indexOf(tmp), temptmp);
                    showTableTreamentPlace();
                    break;
                }
            }
        }
        else{
            MessageDialog.showErrorDialog(this, mess, "Error while editing treament place.");
        }
        temptmp = null;
    }
    
    public void btnTreatmentRemoveActionPerformed(java.awt.event.ActionEvent evt){
        temptmp = getInputTreamentPlaceData();
        String mess = Admin.removeTreatmentPlace(temptmp);
        if (mess == null){
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
        else{
            MessageDialog.showErrorDialog(this, mess, "Error while removing treatment place.");
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
        cbbState = new javax.swing.JComboBox<>();
        lblUserID = new javax.swing.JLabel();
        txtfUserID = new javax.swing.JTextField();
        cbbRole = new javax.swing.JComboBox<>();
        txtfPassword = new javax.swing.JPasswordField();
        pnlButtonInfor = new javax.swing.JPanel();
        btnRemove = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        pnlAccountList = new javax.swing.JPanel();
        pnlAttribute = new javax.swing.JPanel();
        lblAttribute = new javax.swing.JLabel();
        txtfAttribute = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        cbbAttribute = new javax.swing.JComboBox<>();
        scrlpList = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        pnlBankAccountManagement = new javax.swing.JPanel();
        pnlAccountBankInfor = new javax.swing.JPanel();
        pnlBankInfor = new javax.swing.JPanel();
        lblBankID = new javax.swing.JLabel();
        txtfBankID = new javax.swing.JTextField();
        lblBankPassword = new javax.swing.JLabel();
        lblBankRole = new javax.swing.JLabel();
        lblBankState = new javax.swing.JLabel();
        cbbBankState = new javax.swing.JComboBox<>();
        lblBankUserID = new javax.swing.JLabel();
        txtfBankUserID = new javax.swing.JTextField();
        cbbBankRole = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        lblBankBalanced = new javax.swing.JLabel();
        txtfBankBalanced = new javax.swing.JTextField();
        txtfBankPassword = new javax.swing.JPasswordField();
        pnlBankAccount = new javax.swing.JPanel();
        btnBankRemove = new javax.swing.JButton();
        btnBankEdit = new javax.swing.JButton();
        btnBankAdd = new javax.swing.JButton();
        btnBankRefresh = new javax.swing.JButton();
        pnlBankAccountList = new javax.swing.JPanel();
        pnlBankAccountListAttribute = new javax.swing.JPanel();
        lblBankAttribute = new javax.swing.JLabel();
        txtfBankAttribute = new javax.swing.JTextField();
        btnBankFind = new javax.swing.JButton();
        cbbBankAttribute = new javax.swing.JComboBox<>();
        scrlpBankList = new javax.swing.JScrollPane();
        tblBankList = new javax.swing.JTable();
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
        scrlpAccountHistory = new javax.swing.JScrollPane();
        tblAccountHistory = new javax.swing.JTable();
        pnlTransactionHistory = new javax.swing.JPanel();
        scrlpTransactionHistory = new javax.swing.JScrollPane();
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
        scrlpTreatment = new javax.swing.JScrollPane();
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
                    .addComponent(txtfUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtfPassword))
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
                    .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlInforLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbRole, cbbState, lblPassword, lblRole, lblState, lblUserID, txtfUsername});

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

        lblAttribute.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAttribute.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAttribute.setText("Choose attribute:");

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnFind.setText("Find");

        cbbAttribute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Username", "Password", "Role", "Active", "UserID", "Date Published" }));

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

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Password", "Role", "User ID", "Activated", "Date Published"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlpList.setViewportView(tblList);

        javax.swing.GroupLayout pnlAccountListLayout = new javax.swing.GroupLayout(pnlAccountList);
        pnlAccountList.setLayout(pnlAccountListLayout);
        pnlAccountListLayout.setHorizontalGroup(
            pnlAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrlpList))
                .addContainerGap())
        );
        pnlAccountListLayout.setVerticalGroup(
            pnlAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccountListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlpList, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
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
                    .addComponent(txtfBankID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtfBankPassword))
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
                            .addComponent(lblBankPassword)
                            .addComponent(txtfBankPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        pnlBankInforLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbBankRole, cbbBankState, lblBankBalanced, lblBankID, lblBankPassword, lblBankRole, lblBankState, lblBankUserID, txtfBankBalanced, txtfBankID, txtfBankUserID});

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

        lblBankAttribute.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblBankAttribute.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBankAttribute.setText("Choose attribute:");

        btnBankFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnBankFind.setText("Find");

        cbbBankAttribute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bank ID", "Password", "Role", "Active", "Balance" }));

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

        tblBankList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BankID", "Password", "Role", "Activated", "Balance", "UserID", "Date Published"
            }
        ));
        scrlpBankList.setViewportView(tblBankList);

        javax.swing.GroupLayout pnlBankAccountListLayout = new javax.swing.GroupLayout(pnlBankAccountList);
        pnlBankAccountList.setLayout(pnlBankAccountListLayout);
        pnlBankAccountListLayout.setHorizontalGroup(
            pnlBankAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBankAccountListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBankAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBankAccountListAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrlpBankList))
                .addContainerGap())
        );
        pnlBankAccountListLayout.setVerticalGroup(
            pnlBankAccountListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBankAccountListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBankAccountListAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlpBankList, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
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

            },
            new String [] {
                "Username", "Login Record", "Logout Record"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlpAccountHistory.setViewportView(tblAccountHistory);

        javax.swing.GroupLayout pnlAccountHistoryLayout = new javax.swing.GroupLayout(pnlAccountHistory);
        pnlAccountHistory.setLayout(pnlAccountHistoryLayout);
        pnlAccountHistoryLayout.setHorizontalGroup(
            pnlAccountHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlpAccountHistory)
        );
        pnlAccountHistoryLayout.setVerticalGroup(
            pnlAccountHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlpAccountHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
        );

        pnlTransactionHistory.setBorder(javax.swing.BorderFactory.createTitledBorder("TRANSACTION HISTORY"));

        tblTransactionHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BankID", "Money", "Content", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlpTransactionHistory.setViewportView(tblTransactionHistory);

        javax.swing.GroupLayout pnlTransactionHistoryLayout = new javax.swing.GroupLayout(pnlTransactionHistory);
        pnlTransactionHistory.setLayout(pnlTransactionHistoryLayout);
        pnlTransactionHistoryLayout.setHorizontalGroup(
            pnlTransactionHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlpTransactionHistory)
        );
        pnlTransactionHistoryLayout.setVerticalGroup(
            pnlTransactionHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlpTransactionHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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

            },
            new String [] {
                "ID", "Treatment Name", "Capacity", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlpTreatment.setViewportView(tblTreatment);

        javax.swing.GroupLayout pnlTreamentDetailLayout = new javax.swing.GroupLayout(pnlTreamentDetail);
        pnlTreamentDetail.setLayout(pnlTreamentDetailLayout);
        pnlTreamentDetailLayout.setHorizontalGroup(
            pnlTreamentDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlpTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
        );
        pnlTreamentDetailLayout.setVerticalGroup(
            pnlTreamentDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlpTreatment)
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
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfTreatmentID, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lblTreatmentID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfTreatmentName, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lblTreatmentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfTreatmentCapacity, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lblTreatmentCapacity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(pnlTreatmentInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfTreatmentQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lblTreatmentQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
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
        tempaccbank = getInputAccountBankData();
        String mess = Admin.addAccountBank(tempaccbank);
        if (mess == null){
            listaccbank.add(tempaccbank);
             showTableAccountBank();
        }
        else{
            MessageDialog.showErrorDialog(this, mess, "Eror while adding account bank.");
        }
        tempaccbank = null;
    }//GEN-LAST:event_btnBankAddjButton3ActionPerformed

    private void btnBankEditjButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBankEditjButton2ActionPerformed
        // TODO add your handling code here:
        tempaccbank = getInputAccountBankData();
        String mess = Admin.editAccountBank(tempaccbank);
        if (mess == null){
            String ID = tempaccbank.getBankid();
            for (AccountBank accbank : listaccbank){
                if (accbank.getBankid().equalsIgnoreCase(ID)){ 
                    listaccbank.set(listaccbank.indexOf(accbank), tempaccbank);
                    showTableAccountBank();
                    break;
                }
            }
        }
        else{
            MessageDialog.showErrorDialog(this, mess, "Error while editing account bank.");
        }
        tempaccbank = null;
    }//GEN-LAST:event_btnBankEditjButton2ActionPerformed

    private void cbbBankRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbBankRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbBankRoleActionPerformed

    private void txtfBankUserIDjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfBankUserIDjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfBankUserIDjTextField1ActionPerformed

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
        tempacc = getInputAccountData();
        String mess = Admin.addAccount(tempacc);
        if (mess == null){
            listacc.add(tempacc);
            showTableAccount();
        }
        else{
            MessageDialog.showErrorDialog(this, mess, "Error while adding account.");
        }
        tempacc = null;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tempacc = getInputAccountData();
        String mess = Admin.editAccount(tempacc);
        if (mess == null){
            String Username = tempacc.getUsername();
            for (Account acc : listacc){
                if (acc.getUsername().equalsIgnoreCase(Username)){ 
                    listacc.set(listacc.indexOf(acc), tempacc);
                    showTableAccount();
                    break;
                }
            }
        }
        else{
            MessageDialog.showErrorDialog(this, mess, "Error while editing account.");
        }
        tempacc = null;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbRoleActionPerformed

    private void txtfUserIDjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfUserIDjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfUserIDjTextField1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnTreatmentAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTreatmentAddActionPerformed
        // TODO add your handling code here:
        temptmp = getInputTreamentPlaceData();
        String mess = Admin.addTreatmentPlace(temptmp);
        if (mess == null){
            listTMP.add(temptmp);
            showTableTreamentPlace();
        }
        else{
            MessageDialog.showErrorDialog(this, mess, "Error while adding treatment place.");
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

    private void txtfAttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfAttributeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfAttributeActionPerformed

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
    private javax.swing.JScrollPane scrlpAccountHistory;
    private javax.swing.JScrollPane scrlpBankList;
    private javax.swing.JScrollPane scrlpList;
    private javax.swing.JScrollPane scrlpTransactionHistory;
    private javax.swing.JScrollPane scrlpTreatment;
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
    private javax.swing.JPasswordField txtfBankPassword;
    private javax.swing.JTextField txtfBankUserID;
    private javax.swing.JPasswordField txtfPassword;
    private javax.swing.JTextField txtfTreatmentCapacity;
    private javax.swing.JTextField txtfTreatmentID;
    private javax.swing.JTextField txtfTreatmentName;
    private javax.swing.JTextField txtfTreatmentQuantity;
    private javax.swing.JTextField txtfUserID;
    private javax.swing.JTextField txtfUsername;
    // End of variables declaration//GEN-END:variables
}