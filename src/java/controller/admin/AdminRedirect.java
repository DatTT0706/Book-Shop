/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dao.AccountDao;
import dao.AuthorDao;
import dao.CouponDao;
import dao.FR_categoryDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.PublisherDao;
import dao.TypeDao;
import dao.VN_categoryDao;
import entity.Account;
import entity.Author;
import entity.Coupon;
import entity.FR_category;
import entity.Order;
import entity.Product;
import entity.Publisher;
import entity.Type;
import entity.VN_category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shado
 */
@WebServlet(name = "RedirectPage", urlPatterns = {"/admin/redirectpage"})
public class AdminRedirect extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.valueOf(request.getParameter("page"));
            }

            switch (page) {
                case 1:
                    //dashboard
                    request.setAttribute("page", 1);
                    request.setAttribute("productQuantity", new ProductDao().countTotalProduct());
                    request.setAttribute("orderQuantity", new OrderDao().countTotalOrder());
                    request.setAttribute("accountQuantity", new AccountDao().countTotalAccount());
                    request.setAttribute("message", request.getAttribute("message"));
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                    break;
                case 2:
                    //manage account
                    List<Account> listAccount = new AccountDao().getAll();
                    request.setAttribute("page", 2);
                    request.setAttribute("accounts", listAccount);
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                    break;
                case 3:
                    //manage product and add product
                    List<Product> lsProduct = new ProductDao().getAll();
                    List<Type> lsType = new TypeDao().getAll();
                    List<FR_category> lsFrCat = new FR_categoryDao().getAll();
                    List<VN_category> lsVnCat = new VN_categoryDao().getAll();
                    List<Author> lsAuthor = new AuthorDao().getAll();
                    List<Publisher> lsPublishers = new PublisherDao().getAll();
                    request.setAttribute("page", 3);
                    request.setAttribute("products", lsProduct);
                    request.setAttribute("author", lsAuthor);
                    request.setAttribute("publisher", lsPublishers);
                    request.setAttribute("type", lsType);
                    request.setAttribute("fr_categories", lsFrCat);
                    request.setAttribute("vn_categories", lsVnCat);
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                    break;
                case 4:
                    //manage orders
                    request.setAttribute("page", 4);
                    List<Order> lsOrder = new OrderDao().getAll();
                    request.setAttribute("lsOrder", lsOrder);
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                    break;
                case 5:
                    request.setAttribute("page", 5);
                    List<Coupon> lsCoupon = new CouponDao().getAll();
                    request.setAttribute("lsCoupon", lsCoupon);
                    request.getRequestDispatcher("admin-index.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
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
