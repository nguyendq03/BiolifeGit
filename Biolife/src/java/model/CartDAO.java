/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Cart;
import entity.Product;
import entity.Suppliers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CartDAO extends DBContext {

    public void insertCart(int uid, int pid) {
        String sql = "INSERT INTO [dbo].[carts]\n"
                + "           ([userID]\n"
                + "           ,[productID]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, pid);
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Cart isCartExist(Integer uid, Integer pid) {
        try {
            String sql = "select * from carts\n"
                    + "where userID = ? and productID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart(rs.getInt("id"),
                        rs.getInt("userID"),
                        rs.getInt("productID"),
                        rs.getInt("quantity"),
                        rs.getDouble("totalPrice"),
                        rs.getInt("items"));
                return cart;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Integer updatetoCarts(Cart pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Carts]\n"
                + "SET [Quantity] = ?\n"
                + "WHERE ID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            pre.setInt(1, pro.getQuantity());
            pre.setInt(2, pro.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void updateQuantityById(Integer id, Integer quantity) {
        String sql = "UPDATE [dbo].[carts]\n"
                + "   SET \n"
                + "      [quantity] = ?\n"
                + "      \n"
                + " WHERE ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public List<Cart> findAllCart() {
        try {
            String sql = "select c.id, p.[name] as productName,p.price ,c.quantity,c.totalPrice,p.image \n"
                    + "                    from carts c\n"
                    + "                    inner join products p on c.productID = p.ID";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Cart> carts = new ArrayList<>();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setName(rs.getString("productName"));
                cart.setImage(rs.getString("image"));
                cart.setPrice(rs.getDouble("price"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setTotalPrice(rs.getDouble("totalPrice"));
                carts.add(cart);
            }
            return carts;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteCartById(Integer id) {
        String sql = "DELETE FROM [dbo].[carts]\n"
                + "      WHERE ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    //Create
//    public int insertCartsByPrepared(Cart pro) {
//        int n = 0;
//        String sql = "INSERT INTO [dbo].[Carts]\n"
//                + "           ([UserID]\n"
//                + "           ,[ProductID]\n"
//                + "           ,[Quantity]\n"
//                + "           ,[TotalPrice]\n"
//                + "           ,[Items])\n"
//                + "     VALUES(?,?,?,?,?)";
//        //number of ? is number of fields
//        //??index of ? start with 1
//        try {
//            PreparedStatement pre = conn.prepareStatement(sql);
//            //pre.setDataType(indexOf?, para);
//            pre.setInt(1, pro.getId());
//            pre.setInt(2, pro.getUserID());
//            pre.setInt(3, pro.getProductID());
//            pre.setInt(4, pro.getQuantity());
//            pre.setDouble(5, pro.getTotalPrice());
////            pre.setInt(6, pro.getItems());
//
//            n = pre.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return n;
//    }
//    public int updateCarts(Cart pro) {
//        int n = 0;
//        String sql = "UPDATE [dbo].[Carts]\n"
//                + "   SET [Quantity] = ?\n"
//                + "      ,[TotalPrice] = ?\n"
//                + "      ,[Items] = ?\n"
//                + " WHERE ID=?";
//        try {
//            PreparedStatement pre = conn.prepareStatement(sql);
//            //pre.setDataType(indexOf?, para);
//            pre.setInt(1, pro.getId());
//            pre.setInt(2, pro.getUserID());
//            pre.setInt(3, pro.getProductID());
//            pre.setInt(4, pro.getQuantity());
//            pre.setDouble(5, pro.getTotalPrice());
////            pre.setInt(6, pro.getItems());
//            n = pre.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return n;
//    }
    public int deleteCarts(int id) {
        int n = 0;
        String sql = "DELETE from Carts WHERE ID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Cart> getCarts(String sql) {
        Vector<Cart> vector = new Vector<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("ID"); // or id = rs.getInt(1) since the index of id is 1 in ResultSet
                int userID;
                userID = rs.getInt(2);
                int ProductID = rs.getInt(3);
                int quantity = rs.getInt(4);
                double totalPrice = rs.getDouble(5);
                int items = rs.getInt(6);
//                Cart pro = new Cart(id, userID, ProductID, quantity, totalPrice, items);
//                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
//    /**
//     * searchProduct function will search following with input of user
//     * @param name
//     * @return name
//     */
//    public ArrayList<String> searchProduct(String name) {
//        ArrayList<String> list = new ArrayList<>();
//        String sql = "select p.ProductName, c.CategoryName, s.CompanyName\n"
//                + "from Suppliers p join Categories c on p.CategoryID = c.CategoryID\n"
//                + "	join Suppliers s on p.SupplierID = s.SupplierID\n"
//                + "where p.ProductName = '" + name +"'";
//        ResultSet rs = getData(sql);
//        try {
//            if (rs.next()) {
//                String pro = rs.getString("ProductName");
//                String sup = rs.getString("CategoryName");
//                String cate = rs.getString("CompanyName");
//                list.add(pro);
//                list.add(cate);
//                list.add(sup);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }

    public static void main(String[] args) {
        CartDAO dao = new CartDAO();
        Vector<Cart> vector = dao.getCarts("select * from Carts");
        for (Cart pro : vector) {
            System.out.println(pro);
        }
//        dao.deleteProduct(85);

//        for (String string : dao.searchProduct("Chai")) {
//            System.out.println(string);
//        }
    }

}
