/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Record;

import java.time.LocalDateTime;

/**
 *
 * @author ACER
 */
public class ActivityHistory {
    private String username;
    private LocalDateTime logindt;
    private LocalDateTime logoutdt;

    public ActivityHistory() {
        username = "";
        logindt = null;
        logoutdt = null;
    }
    
    public void setActivityHis(String Username, LocalDateTime LoginDT, LocalDateTime LogoutDT){
        username = Username;
        logindt = LoginDT;
        logoutdt = LogoutDT;
    }
    
    public String getUsername(){
        return username;
    }
    
    public LocalDateTime getLoginDT(){
        return logindt;
    }
    
    public LocalDateTime getLogoutDT(){
        return logoutdt;
    }
}
