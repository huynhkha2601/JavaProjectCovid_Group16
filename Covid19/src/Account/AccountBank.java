/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;

import java.util.Date;

/**
 *
 * @author Thong
 */
public class AccountBank {
    String id,password,role;
    float balance;
    Date datePublished;
    public AccountBank(){
        id ="";
        password="";
        role="";
        balance = 0;
        datePublished=null;
    }
    public void setAccountBank(String ID, String Password, String Role, float Balance,Date DatePublished){
        id=ID;
        password=Password;
        role=Role;
        balance=Balance;
        datePublished = DatePublished;
    }
}
