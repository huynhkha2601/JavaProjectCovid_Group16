/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TreatmentPlace;

/**
 *
 * @author ACER
 */
public class TreatmentPlace {
    private String id;
    private String name;
    private int capacity;
    private int quantity;

    public TreatmentPlace() {
        id = "";
        name = "";
        capacity = 0;
        quantity = 0;
    }
    public void setTreatmentPlace(String ID, String Name, int Capacity, int Quantity){
        id = ID;
        name = Name;
        capacity = Capacity;
        quantity = Quantity;
    }
    public String getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getCapacity(){
        return capacity;
    }
    public int getQuantity(){
        return quantity;
    }
}
