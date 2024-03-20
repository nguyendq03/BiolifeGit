/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class OrderProduct {

    private int id;
    private int orderID;
    private int prodcutID;
    private int quantity;
    private double price;

    public OrderProduct() {
    }

    public OrderProduct(int id, int orderID, int prodcutID, int quantity, double price) {
        this.id = id;
        this.orderID = orderID;
        this.prodcutID = prodcutID;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProdcutID() {
        return prodcutID;
    }

    public void setProdcutID(int prodcutID) {
        this.prodcutID = prodcutID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderProduct{" + "id=" + id + ", orderID=" + orderID + ", prodcutID=" + prodcutID + ", quantity=" + quantity + ", price=" + price + '}';
    }

   

}
