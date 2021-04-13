/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FR_categoryDao;
import dao.ProductDao;
import dao.TypeDao;
import dao.VN_categoryDao;
import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRANTATDAT
 */
@WebServlet(name = "FilterServlet", urlPatterns = {"/filter"})
public class FilterServlet extends HttpServlet {

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
            int type = Integer.parseInt(request.getParameter("type"));
            int flagOrder = 0;
            if (request.getParameter("flag") != null) {
                flagOrder = Integer.parseInt(request.getParameter("flag"));
            }
            List<Product> lsProduct = new ArrayList<>();           
            int type_id = 0;
            String typeName = "";
            switch (type) {
                //VN books or FR books
                case 1: {
                    type_id = Integer.parseInt(request.getParameter("type_id"));
                    request.setAttribute("type_id", type_id);
                    typeName = new TypeDao().getOne(type_id).getName();
                    lsProduct = new ProductDao().getAllByType(type_id);
                    break;
                }
                //FR categories
                case 2: {
                    int category_id = Integer.parseInt(request.getParameter("cat_id"));
                    request.setAttribute("category_id", category_id);
                    typeName = new FR_categoryDao().getOne(category_id).getName();
                    lsProduct = new ProductDao().getAllByFrCategory(category_id);
                    break;
                }
                //VN categories
                case 3: {
                    int category_id = Integer.parseInt(request.getParameter("cat_id"));
                    request.setAttribute("category_id", category_id);
                    typeName = new VN_categoryDao().getOne(category_id).getName();
                    lsProduct = new ProductDao().getAllByVNCategory(category_id);
                    break;
                }
            }

            switch (flagOrder) {
                case 1: {
                    lsProduct.sort(Comparator.comparingDouble(Product::getPrice));
                    break;
                }
                case 2: {
                    lsProduct.sort(Comparator.comparingDouble(Product::getPrice).reversed());
                    break;
                }
            }

            //set attribute                     
            request.setAttribute("filter", type);
            request.setAttribute("typeName", typeName);
            request.setAttribute("lsProduct", lsProduct);
            request.setAttribute("flagOrder", flagOrder);           
            request.getRequestDispatcher("filter.jsp").forward(request, response);
        } catch (Exception e) {
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
