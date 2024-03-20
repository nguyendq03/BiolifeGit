/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
public class ProductDAO extends DBContext {

    public List<Product> findAll() {
        try {
            String sql = "select p.ID, p.name as productName,"
                    + " Description, Price,"
                    + " Image"
                    + " from products p";
                   
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("ID"));
                product.setName(rs.getString("productName"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getFloat("price"));
               
                product.setImage(rs.getString("Image"));
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Product getProductById(String id) {
        try {
            String sql = "select * from Products\n"
                    + "where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("Name"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getFloat("Price"));
                product.setImage(rs.getString("Image"));
                return product;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Product> searchByName(String search) {
        try {
            String sql = "select * from Products\n"
                    + "where [name] like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("Name"));
                product.setDescription(rs.getString("Description"));
                product.setPrice(rs.getFloat("Price"));
                product.setImage(rs.getString("Image"));
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Create
    public int insertProductByPrepared(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([Name]\n"
                + "           ,[Description]\n"
                + "           ,[Price]\n"
                + "           ,[StockQuantity]\n"
                + "           ,[SupplierID]\n"
                + "           ,[Image])\n"
                + "     VALUES(?,?,?,?,?,?)";
        //number of ? is number of fields
        //??index of ? start with 1
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            pre.setInt(1, pro.getId());
            pre.setString(2, pro.getName());
            pre.setString(3, pro.getDescription());
            pre.setDouble(4, pro.getPrice());
            pre.setInt(5, pro.getStockQuantity());
            pre.setInt(6, pro.getSupplierID());
            pre.setString(7, pro.getImage());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[StockQuantity] = ?\n"
                + "      ,[SupplierID] = ?\n"
                + "      ,[Image] = ?\n"
                + " WHERE ID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            pre.setInt(1, pro.getId());
            pre.setString(2, pro.getName());
            pre.setString(3, pro.getDescription());
            pre.setDouble(4, pro.getPrice());
            pre.setInt(5, pro.getStockQuantity());
            pre.setInt(6, pro.getSupplierID());
            pre.setString(7, pro.getImage());
            pre.setInt(8, pro.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteProduct(int id) {
        int n = 0;
        String sql = "DELETE from Products WHERE ID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Product> getProduct(String sql) {
        Vector<Product> vector = new Vector<Product>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("ID"); // or id = rs.getInt(1) since the index of id is 1 in ResultSet
                String name = rs.getString(2);
                String description = rs.getString(3);
                double price = rs.getDouble(4);
                int stockQuantity = rs.getInt(5);
                int supid = rs.getInt(6);
                String image = rs.getString(7);
                Product pro = new Product(id, name, description, price, stockQuantity, supid, image, name);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Vector<Product> vector = dao.getProduct("select * from Products");
        for (Product pro : vector) {
            System.out.println(pro);
        }
//        dao.deleteProduct(85);

//        for (String string : dao.searchProduct("Chai")) {
//            System.out.println(string);
//        }
    }

}
