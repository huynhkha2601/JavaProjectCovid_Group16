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
public class Account {
    String username,password,role,userid;
    int activated;
    Date datePublished;
    public Account(){
        username="";
        password="";
        role="";
        userid="";
        activated = 0;
        datePublished = null;
    }
    public void show()
    {
        System.out.println(username);
        System.out.println(password);
        System.out.println(role);
        System.out.println(userid);
        System.out.println(activated);
    }
    public void setAccount(String Username, String Password, String Role, String Userid, int Activated,Date DatePublished){
        username=Username;
        password =Password;
        role = Role;
        userid = Userid;
        activated = Activated;
        datePublished = DatePublished;
    }
}
