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
public class TransactionHistory {
    private String bankid;
    private float money;
    private String content;
    private LocalDateTime date;

    public TransactionHistory() {
        bankid = "";
        money = 0;
        content = "";
        date = null;
    }
    
    public void setActivityHis(String BankID, float Money, String Content, LocalDateTime Date){
        bankid = BankID;
        money = Money;
        content = Content;
        date = Date;
    }
    
    public String getBankid(){
        return bankid;
    }
    
    public float getMoney(){
        return money;
    }
    
    public String getContent(){
        return content;
    }
    
    public LocalDateTime getDate(){
        return date;
    }
}
