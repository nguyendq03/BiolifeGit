/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    Connection conn = null;
    PreparedStatement ps;
    
    ResultSet rs;
    public DBContext(String url, String user, String pass) {
        try {
            //call Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connect
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("connected");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public ResultSet getData(String sql) {
        ResultSet rs;
        rs = null;
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public DBContext() {
        this("jdbc:sqlserver://localhost:1433;databaseName=Biolife;encrypt=true;trustServerCertificate=true;", "sa", "123456");
    }

    public static void main(String[] args) {
        new DBContext();
    }

  

    
}
