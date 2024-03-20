/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBContext {

    public int insertOrders(Orders ord) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([CreatedDate]\n"
                + "           ,[UserID]\n"
                + "           ,[Status])\n"
                + "     VALUES(?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDate(1, ord.getCreatedDate());
            pre.setInt(2, ord.getUserID());
            pre.setString(3, ord.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateOrders(Orders ord) {
        int n = 0;
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [CreatedDate] = ?\n"
                + "      ,[TotalPrice] = ?\n"
                + "      ,[UserID] = ?\n"
                + "      ,[Status] = ?\n"
                + " WHERE ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDate(1, ord.getCreatedDate());
            pre.setInt(2, ord.getUserID());
            pre.setString(3, ord.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteOrder(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Orders]\n"
                + "      WHERE ID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Orders> getOrders(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        ResultSet rs = getData(sql);
        try {
//            private int identity;
//    private Date CreatedDate;
//    private double TotalPrice;
//    private int UserID;
//    private String Status;
            while (rs.next()) {
                int identity = rs.getInt(1);
                Date createdDate = rs.getDate(2);
                double totalPrice = rs.getDouble(3);
                int userID = rs.getInt(4);
                String status = rs.getString(5);
                Orders order = new Orders(identity, createdDate, totalPrice, userID, status);
                vector.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
//    public Vector<ArrayList<String>> viewOrderOfCustomerById(String id) {
//        Vector<ArrayList<String>> vector = new Vector();
//        String sql = "select o.OrderID, o.OrderDate as Date, c.ContactName as CustomerName, \n"
//                + "	e.FirstName + ' ' + e.LastName as EmployeeName, p.ProductName, od.Quantity,\n"
//                + "	od.UnitPrice, od.Discount, (od.Quantity*od.UnitPrice)*(1-od.Discount) as SubTotal\n"
//                + " from Orders o join [Order Details] od on o.OrderID = od.OrderID\n"
//                + "	join Products p on od.ProductID = p.ProductID\n"
//                + "	join Customers c on o.CustomerID = c.CustomerID\n"
//                + "	join Employees e on e.EmployeeID = o.EmployeeID\n"
//                + "where c.CustomerID = '" + id + "'";
//        ResultSet rs = getData(sql);
//        try {
//            while (rs.next()) {
//                String orderID = Integer.toString(rs.getInt("OrderID"));
//                String date = (String) rs.getString("Date");
//                String customerName = rs.getString("CustomerName");
//                String employeeName = rs.getString("employeeName");
//                String productName = rs.getString("ProductName");
//                String quantity = Integer.toString(rs.getInt("Quantity"));
//                String unitPrice = Double.toString(rs.getDouble("UnitPrice"));
//                String discount = Double.toString(rs.getDouble("Discount"));
//                String subTotal = String.format("%,.1f", rs.getDouble("SubTotal"));
//                ArrayList<String> list = new ArrayList<>();
//                list.add(orderID);
//                list.add(date);
//                list.add(customerName);
//                list.add(employeeName);
//                list.add(productName);
//                list.add(quantity);
//                list.add(unitPrice);
//                list.add(discount);
//                list.add(subTotal);
//                vector.add(list);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return vector;
//    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        Vector<Orders> vector = dao.getOrders("select * from Orders");
        for (Orders pro : vector) {
            System.out.println(pro);
        }
    }
}
