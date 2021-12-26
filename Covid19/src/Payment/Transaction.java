/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Payment;

import java.time.LocalDateTime;

/**
 *
 * @author Ashbell
 */
public class Transaction {
    private float money;
    private String content;
    private LocalDateTime record;
    public Transaction() {
        money = 0;
        content = "";
        record = null;
    }
    public Transaction(float money, String content, LocalDateTime record) {
        this.money = money;
        this.content = content;
        this.record = record;
    }
    public float getMoney() {
        return money;
    }
    public String getContent() {
        return content;
    }
    public LocalDateTime getRecord() {
        return record;
    }
}
