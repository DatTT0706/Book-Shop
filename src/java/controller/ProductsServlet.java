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
import entity.FR_category;
import entity.Product;
import entity.Type;
import entity.VN_category;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
@WebServlet(name = "ProductsServlet", urlPatterns = {"/Products"})
public class ProductsServlet extends HttpServlet {

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
            //handle paging in index
            String pageStr = request.getParameter("page");
            //default page number
            int page = 1;

            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }

            ProductDao pd = new ProductDao();
            //number of product per page
            int numberProduct = 15;
            //get number of product in store
            int totalProduct = pd.countTotalProduct();
            //calculate total page number
            int totalPage = (totalProduct % numberProduct == 0) ? totalProduct / numberProduct : totalProduct / numberProduct + 1;

            //add number to list of page
            List<Integer> lsPage = new ArrayList<>();
            for (int i = 1; i <= totalPage; ++i) {
                lsPage.add(i);
            }

            //get sort flag
            int flagOrder = 0;
            if (request.getParameter("flag") != null) {
                flagOrder = Integer.parseInt(request.getParameter("flag"));
            }

            List<Product> lsProduct = new ArrayList<>();

            switch (flagOrder) {
                case 0: {
                    lsProduct = pd.getProductPerPage((page * numberProduct - numberProduct), numberProduct);
                    break;
                }
                case 1: {
                    lsProduct = pd.getProductPerPageIncreasingPrice((page * numberProduct - numberProduct), numberProduct);
                    lsProduct.sort(Comparator.comparingDouble(Product::getPrice));
                    break;
                }
                case 2: {
                    lsProduct = pd.getProductPerPageDecreasingPrice((page * numberProduct - numberProduct), numberProduct);
                    lsProduct.sort(Comparator.comparingDouble(Product::getPrice).reversed());
                    break;
                }
            }

            HttpSession session = request.getSession();

            //get type, category 
            List<Type> lsType = new TypeDao().getAll();
            List<FR_category> lsFrCat = new FR_categoryDao().getAll();
            List<VN_category> lsVnCat = new VN_categoryDao().getAll();

            //set type, category attribute
            session.setAttribute("lsType", lsType);
            session.setAttribute("lsFrCat", lsFrCat);
            session.setAttribute("lsVnCat", lsVnCat);

            //get 3 newest product
            List<Product> lsNewProduct = new ProductDao().getThreeNewProduct();

            //set attribute          
            //set paging variable
            request.setAttribute("page", page);
            request.setAttribute("lsPage", lsPage);
            request.setAttribute("lsProduct", lsProduct);
            //send sort flag
            request.setAttribute("flagOrder", flagOrder);
            request.setAttribute("lsNewProduct", lsNewProduct);
            //send data to jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
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
