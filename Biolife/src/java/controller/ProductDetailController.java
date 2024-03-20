/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import entity.Product;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartDAO;
import model.ProductDAO;

/**
 *
 * @author Admin
 */
public class ProductDetailController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user;
        user = (User) request.getSession().getAttribute("user");
        String id = request.getParameter("id");
        ProductDAO productDAO = new ProductDAO();
        Product productDetail = productDAO.getProductById(id);
//        request.setAttribute("fullname", user.getFullName());
        request.setAttribute("product", productDetail);
        request.getRequestDispatcher("/JSP/productDetail.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String quantity = request.getParameter("quantity");
        System.out.println(quantity);
        String productID = request.getParameter("productID");
        System.out.println(productID);
        request.getRequestDispatcher("JSP/shopping_cart.jsp");
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
