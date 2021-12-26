/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;

import java.time.LocalDateTime;

/**
 *
 * @author Thong
 */
public class AccountBank {
    String id,password,role, userid;
    int activated;
    float balance;
    LocalDateTime datepublished;
    public AccountBank(){
        id ="";
        password="";
        role="";
        activated = 0;
        balance = 0;
        userid = "";
        datepublished = null;
    }
    public void setAccountBank(String ID, String Password, String Role, 
                               int Activated, float Balance, String Userid,
                               LocalDateTime DatePublished){
        id=ID;
        password=Password;
        role=Role;
        activated = Activated;
        balance=Balance;
        userid = Userid;
        datepublished = DatePublished;
    }
    
    public String getBankid(){
        return id;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }
    public int getState(){
        return activated;
    }
    public float getBalance(){
        return balance;
    }
    public String getUserid(){
        return userid;
    }
    public LocalDateTime getDatePublished(){
        return datepublished;
    }
}
