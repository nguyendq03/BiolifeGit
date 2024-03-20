/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class UserDAOIT {

    public UserDAOIT() {
    }

    private Connection conn;

    @Before
    public void setUp() {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=Biolife;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "123456";

        DBContext dbContext = new DBContext(jdbcUrl, username, password);
        conn = dbContext.conn;

        assertNotNull("Connection should not be null", conn);

    }

    /**
     * Test of findByUserAndPassword method, of class UserDAO.
     */
    @Test
    public void testFindByUserAndPassword() {
        UserDAO userDAO = new UserDAO();
        // Dữ liệu thực tế để test
        String existingUsername = "john_doe";
        String existingPassword = "password123";

        // Kỳ vọng kết quả
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setFullName("John Doe");
        expectedUser.setEmail("john.doe@gmail.com");
        expectedUser.setUsername(existingUsername);
        expectedUser.setPassword(existingPassword);

        // Thực hiện tìm kiếm
        User actualUser = userDAO.findByUserAndPassword(existingUsername, existingPassword);

        // So sánh kết quả thực tế và kỳ vọng
        assertNotNull("User should exist", actualUser);
        assertEquals("User ID should match", expectedUser.getId(), actualUser.getId());
        assertEquals("Full name should match", expectedUser.getFullName(), actualUser.getFullName());
        assertEquals("Email should match", expectedUser.getEmail(), actualUser.getEmail());
        assertEquals("Username should match", expectedUser.getUsername(), actualUser.getUsername());
        assertEquals("Password should match", expectedUser.getPassword(), actualUser.getPassword());
    }

    /**
     * Test of isUserExist method, of class UserDAO.
     */
    @Test
    public void testIsUserExist() {
//        UserDAO userDAO = new UserDAO();
//        // Dữ liệu thực tế để test
//        String existingUsername = "john_doe";
//        String nonExistingUsername = "nguyenddd";
//        
//        // Thực hiện kiểm tra
//        boolean existingUserExist = userDAO.isUserExist(existingUsername);
//        boolean nonExistingUserExist = userDAO.isUserExist(nonExistingUsername);
//        
//        // Kỳ vọng kết quả
//        assertTrue("Existing user", existingUserExist);
//        assertFalse("Non-existing user", nonExistingUserExist);
    }

    /**
     * Test of signUp method, of class UserDAO.
     */
    @Test
    public void testSignUp() {
//        UserDAO userDAO = new UserDAO();
//        // Dữ liệu thực tế để test
//        String existingUsername = "john_doe";
//        String nonExistingUsername = "nguyen123";
//        String password = "password";
//
//        // Thực hiện kiểm tra
//        userDAO.signUp(existingUsername, password);
//        boolean existingUserSignedUp = userDAO.isUserExist(existingUsername);
//
//        userDAO.signUp(nonExistingUsername, password);
//        boolean nonExistingUserSignedUp = userDAO.isUserExist(nonExistingUsername);
//
//        // Kỳ vọng kết quả
//        assertFalse("Existing user should not sign up again", existingUserSignedUp);
//        assertTrue("Non-existing user should sign up successfully", nonExistingUserSignedUp);

    }

    /**
     * Test of main method, of class UserDAO.
     */
    @Test
    public void testMain() {

    }

}
