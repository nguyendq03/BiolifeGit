/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Roles;
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
public class RoleDAO extends DBContext {

    public int insertRolesByPrepared(Roles pro) {
        int n = 0;
        String sql = "INSERT INTO [Roles]\n"
                + "           (Name)\n"
                + "     VALUES(?)";
        //number of ? is number of fields
        //??index of ? start with 1
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            pre.setString(1, pro.getName());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateRoles(Roles pro) {
        int n = 0;
        String sql = "UPDATE [Roles]\n"
                + "   SET [Name] = ?\n"
                + " WHERE ID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            pre.setString(1, pro.getName());
            
            pre.setInt(2, pro.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteRoles(int id) {
        int n = 0;
        String sql = "DELETE from Roles WHERE ID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Roles> getRoles(String sql) {
        Vector<Roles> vector = new Vector<Roles>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("ID"); // or id = rs.getInt(1) since the index of id is 1 in ResultSet
                String name = rs.getString(2);
                
                Roles rol = new Roles(id, name);
                vector.add(rol);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

//    /**
//     * searchProduct functin will search following with input of user
//     *
//     * @param name
//     * @return name
//     */
//    public ArrayList<String> searchProduct(String name) {
//        ArrayList<String> list = new ArrayList<>();
//        String sql = "select p.ProductName, c.CategoryName, s.CompanyName\n"
//                + "from Products p join Categories c on p.CategoryID = c.CategoryID\n"
//                + "	join Suppliers s on p.SupplierID = s.SupplierID\n"
//                + "where p.ProductName = '" + name + "'";
//        ResultSet rs = getData(sql);
//        try {
//            if (rs.next()) {
//                String rol = rs.getString("ProductName");
//                String sup = rs.getString("CategoryName");
//                String cate = rs.getString("CompanyName");
//                list.add(rol);
//                list.add(cate);
//                list.add(sup);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }

    public static void main(String[] args) {
        RoleDAO dao = new RoleDAO();
        Vector<Roles> vector = dao.getRoles("select * from Roles");
        for(Roles rol : vector) {
            System.out.println(rol);
        }
//        dao.deleteProduct(85);

//        for (String string : dao.searchProduct("Chai")) {
//            System.out.println(string);
//        }
    }
}
