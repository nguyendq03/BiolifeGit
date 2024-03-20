/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Suppliers;
import entity.User;
import java.sql.Connection;
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
public class UserDAO extends DBContext {

    

    public User findByUserAndPassword(String username, String password) {
        try {
            String sql = "select * from Users \n"
                    + "where UserName = ? and Password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean isUserExist(String username) {
        try {
            String sql = "select * from Users \n"
                    + "where UserName = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void signUp(String username, String password) {
        String sql = "INSERT INTO Users (UserName, Password)\n"
                + "VALUES (?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //Create
    public Integer insertUsersByPrepared(User pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Users]]\n"
                + "           ([FullName]\n"
                + "           ,[UserName]\n"
                + "           ,[Password]\n"
                + "           ,[Email])\n"
                + "     VALUES(?,?,?,?)";
        //number of ? is number of fields
        //??index of ? start with 1
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            pre.setInt(1, pro.getId());
            pre.setString(2, pro.getFullName());
            pre.setString(3, pro.getPassword());
            pre.setString(4, pro.getEmail());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Integer updateUsers(User pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [FullName] = ?\n"
                + "      ,[UserName] = ?\n"
                + "      ,[Password] = ?\n"
                + "      ,[Email] = ?\n"
                + " WHERE ID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            pre.setInt(1, pro.getId());
            pre.setString(2, pro.getFullName());
            pre.setString(3, pro.getUsername());
            pre.setString(4, pro.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteUsers(int id) {
        int n = 0;
        String sql = "DELETE from Users WHERE ID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<User> getUsers(String sql) {
        Vector<User> vector = new Vector<User>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt("ID"); // or id = rs.getInt(1) since the index of id is 1 in ResultSet
                String fullname = rs.getString(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);

                User pro = new User(id, fullname, username, password, email);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
//        UserDAO dao = new UserDAO();
//        Vector<User> vector = dao.getUsers("select * from Users");
//        for (User pro : vector) {
//            System.out.println(pro);
//        }
//        dao.deleteProduct(85);

//        for (String string : dao.searchProduct("Chai")) {
//            System.out.println(string);
//        }
    }
}
