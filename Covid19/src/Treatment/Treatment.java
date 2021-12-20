/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Treatment;

/**
 *
 * @author Ashbell
 */
public class Treatment {
    private String id, name;
    private int capacity, quantity;
    public Treatment() {
        this.id = null;
        this.name = null;
        this.capacity = 0;
        this.quantity = 0;
    }
    public Treatment(String id, String name, int capacity, int quantity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.quantity = quantity;
    }
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getQuantity() {
        return quantity;
    }
}
