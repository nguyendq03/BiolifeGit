/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Orders {

    private int id;
    private Date createdDate;
    private double totalPrice;
    private int userID;
    private String status;

    public Orders() {
    }

    public Orders(int id, Date createdDate, double totalPrice, int userID, String status) {
        this.id = id;
        this.createdDate = createdDate;
        this.totalPrice = totalPrice;
        this.userID = userID;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" + "id=" + id + ", createdDate=" + createdDate + ", totalPrice=" + totalPrice + ", userID=" + userID + ", status=" + status + '}';
    }

    
}
