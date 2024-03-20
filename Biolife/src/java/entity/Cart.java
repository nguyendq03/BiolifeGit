/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Cart {

    private int id;
    private int userID;
    private int productID;
    private int quantity;
    private double totalPrice;
    private int items;
    private String name;
    private double price;
    private String image;

    public Cart() {
    }

    public Cart(int id, int userID, int productID, int quantity, double totalPrice, int items) {
        this.id = id;
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", userID=" + userID + ", productID=" + productID + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", items=" + items + ", name=" + name + ", price=" + price + '}';
    }

}
