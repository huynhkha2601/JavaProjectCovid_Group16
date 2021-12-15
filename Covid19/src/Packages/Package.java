/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Packages;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Package {
    private String pID,pName;
    private int limitNum,quantity;
    private float price;
    private Date limitTime;

    // get Constructor
    public Package() {
    }

    public Package(String pID, String pName, int limitNum, Date limitTime, float price, int quantity) {
        this.pID = pID;
        this.pName = pName;
        this.limitNum = limitNum;
        this.quantity = quantity;
        this.price = price;
        this.limitTime = limitTime;
    }

    // getter and setter for each attribute
    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
    }

    
    // function.
    @Override
    public String toString() {
        return this.pID + " " + this.pName + " " + limitNum + " " + limitTime + " " + price + " " + quantity;
    }
    
}
