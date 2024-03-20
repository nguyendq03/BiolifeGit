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
import java.util.List;
import model.CartDAO;

/**
 *
 * @author Admin
 */
public class AddToCartController extends BaseAuthenticationController {

    protected void processGet() throws ServletException, IOException {
        processGet(null, null);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer uid = ((User) request.getSession().getAttribute("user")).getId();
        Integer pid = Integer.parseInt(request.getParameter("pid"));

        CartDAO cartDAO = new CartDAO();
        Cart cart = cartDAO.isCartExist(uid, pid);
        if (cart == null) {
            // insert cart
            cartDAO.insertCart(uid, pid);
        } else {
            //if cart is exist will add to quantity
            cart.setQuantity(cart.getQuantity() + 1);
            cartDAO.updateQuantityById(cart.getId(), cart.getQuantity() + 1);
            // update cart to database
            cartDAO.updatetoCarts(cart);
        }
        response.sendRedirect("viewcart");
    }
}
