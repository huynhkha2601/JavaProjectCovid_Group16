/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;

/**
 *
 * @author Thong
 */
public class AccountBank {
    String id,password,role;
    float balance;
    public AccountBank(){
        id ="";
        password="";
        role="";
        balance = 0;
    }
    public void setAccountBank(String ID, String Password, String Role, float Balance){
        id=ID;
        password=Password;
        role=Role;
        balance=Balance;
    }
}
