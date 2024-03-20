
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Cart;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.CartDAO;

/**
 *
 * @author Admin
 */
public class ViewCartController extends BaseAuthenticationController {
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        CartDAO cartDAO = new CartDAO();
        request.setAttribute("fullname", user.getFullName());
        List<Cart> carts = cartDAO.findAllCart();
        request.setAttribute("viewcart", carts);
        System.out.println(carts);

        int totalQuan = 0;
        for(Cart c : carts) {
            totalQuan += c.getQuantity();
        }
        request.setAttribute("totalQuan", totalQuan);
        request.getRequestDispatcher("JSP/shopping_cart.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
    
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
