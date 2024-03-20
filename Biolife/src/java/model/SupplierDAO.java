/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import entity.Suppliers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SupplierDAO extends DBContext {

    //Create
    public int insertSuppliersByPrepared(Suppliers pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Suppliers]\n"
                + "           ([Name]\n"
                + "           ,[Contact])\n"
                + "     VALUES(?,?)";
        //number of ? is number of fields
        //??index of ? start with 1
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            pre.setInt(1, pro.getId());
            pre.setString(2, pro.getName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateSuppliers(Suppliers pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Suppliers]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Description] = ?\n"
                + " WHERE ID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            pre.setInt(1, pro.getId());
            pre.setString(2, pro.getName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteSuppliers(int id) {
        int n = 0;
        String sql = "DELETE from Suppliers WHERE ID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Suppliers> getSuppliers(String sql) {
        Vector<Suppliers> vector = new Vector<Suppliers>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("ID"); // or id = rs.getInt(1) since the index of id is 1 in ResultSet
                String supname = rs.getString(2);
                String contact = rs.getString(3);
                Suppliers pro = new Suppliers(id, supname, contact);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
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
//
   public static void main(String[] args) {
        SupplierDAO dao = new SupplierDAO();
        Vector<Suppliers> vector = dao.getSuppliers("select * from Suppliers");
       for (Suppliers pro : vector) {
           System.out.println(pro);
      }
//       dao.deleteSuppliers(85);
//
//       for (String string : dao.searchProduct("Chai")) {
//           System.out.println(string);
       }
   

}
