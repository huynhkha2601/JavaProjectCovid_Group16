/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Account;


import java.time.LocalDateTime;


public class Account {
    String username, password, role, userid;
    int activated;
    LocalDateTime datepublished;

    public Account() {
        username = "";
        password = "";
        role = "";
        userid = "";
        activated = 0;
        datepublished = null;
    }

    public void show() {
        System.out.println(username);
        System.out.println(password);
        System.out.println(role);
        System.out.println(userid);
        System.out.println(activated);
    }

    public void setAccount(String Username, String Password, String Role,
            String Userid, int Activated, LocalDateTime DatePublished) {
        username = Username;
        password = Password;
        role = Role;
        userid = Userid;
        activated = Activated;
        datepublished = DatePublished;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUserid() {
        return userid;
    }

    public int getState() {
        return activated;
    }

    public LocalDateTime getDatePublished() {
        return datepublished;
    }
}
