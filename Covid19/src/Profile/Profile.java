/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Profile;

/**
 *
 * @author Ashbell
 */
public class Profile {
    private String id, fullName, address, status, treatment;
    private int YoB;
    private double debt;
    public Profile() {
        this.id = null;
        this.fullName = null;
        this.YoB = 0;
        this.address = null;
        this.status = null;
        this.treatment = null;
        this.debt = 0;
    }
    public Profile(String id, String fullName, int YoB, String address, String status, String treatment, double debt) {
        this.id = id;
        this.fullName = fullName;
        this.YoB = YoB;
        this.address = address;
        this.status = status;
        this.treatment = treatment;
        this.debt = debt;
    }
    public String getID() {
        return this.id;
    }
    public String getFullName() {
        return this.fullName;
    }
    public int getYoB() {
        return this.YoB;
    }
    public String getAddress() {
        return this.address;
    }
    public String getStatus() {
        return this.status;
    }
    public String getTreatment() {
        return this.treatment;
    }
    public double getDebt() {
        return this.debt;
    }
}
