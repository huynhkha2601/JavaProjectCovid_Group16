/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PackageRegister;

import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class PackageRegister {
    
    private String ID, PackageID, PackageName;
    private LocalDateTime record;
    private float price, total;
    private int quantity;

    public PackageRegister() {
    }

    public PackageRegister(String ID, String PackageID, String PackageName, LocalDateTime record, float price, float total, int quantity) {
        this.ID = ID;
        this.PackageID = PackageID;
        this.PackageName = PackageName;
        this.record = record;
        this.price = price;
        this.total = total;
        this.quantity = quantity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPackageID() {
        return PackageID;
    }

    public void setPackageID(String PackageID) {
        this.PackageID = PackageID;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String PackageName) {
        this.PackageName = PackageName;
    }

    public LocalDateTime getRecord() {
        return record;
    }

    public void setRecord(LocalDateTime record) {
        this.record = record;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
