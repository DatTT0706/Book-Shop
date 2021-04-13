/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDao;
import entity.Cart;
import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRANTATDAT
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int productId = Integer.parseInt(request.getParameter("id"));

            Product product = new ProductDao().getOne(productId);

            HttpSession session = request.getSession();
            //create a list cart and save to session
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            //create a list product at the time of add to cart to check quantity of product in cart
            List<Product> lsProduct = new ProductDao().getAll();

            //update the number of product in the database
            product.setQuantity(product.getQuantity() - 1);
            new ProductDao().update(productId, product);
            if (listCart == null) {
                //create new list cart and add product
                listCart = new ArrayList<>();  //[]
                listCart.add(Cart.builder()
                        .id(product.getId())
                        .imgName(product.getImgName())
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(1)
                        .build()
                );

            } else {
                //list cart existed
                boolean check = true;
                for (Cart cart : listCart) {
                    //product already in cart
                    if (cart.getId() == productId) {
                        cart.setQuantity(cart.getQuantity() + 1);
                        check = false;
                        break;
                    }
                }

                //add new product to cart
                if (check) {
                    listCart.add(Cart.builder()
                            .id(product.getId())
                            .imgName(product.getImgName())
                            .name(product.getName())
                            .price(product.getPrice())
                            .quantity(1)
                            .build()
                    );
                }

            }
            //tong so luong sp trong gio hang tong tien
            int number = 0;
            double totalMoney = 0;
            for (Cart cart : listCart) {
                number += cart.getQuantity();
                totalMoney += cart.getQuantity() * cart.getPrice();
            }
            //send all attribute
            session.setAttribute("lsProductInStore", lsProduct);
            session.setAttribute("totalProduct", number);
            session.setAttribute("totalMoney", totalMoney);
            session.setAttribute("listCart", listCart);
            response.sendRedirect("Products");
        } catch (Exception ex) {
            response.sendRedirect("error.jsp");
        }
    }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
