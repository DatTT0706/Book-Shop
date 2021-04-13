/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AuthorDao;
import dao.FR_categoryDao;
import dao.ImageDao;
import dao.ProductDao;
import dao.PublisherDao;
import dao.TypeDao;
import dao.VN_categoryDao;
import entity.Author;
import entity.FR_category;
import entity.Image;
import entity.Product;
import entity.Publisher;
import entity.Type;
import entity.VN_category;
import java.io.IOException;
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
@WebServlet(name = "ProductDetailsServlet", urlPatterns = {"/product-detail"})
public class ProductDetailsServlet extends HttpServlet {

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
        try {
            //get id of product, product's author and publisher
            int id = Integer.parseInt(request.getParameter("id"));
            int authorId = Integer.parseInt(request.getParameter("author_id"));
            int publisherId = Integer.parseInt(request.getParameter("publisher_id"));
            
            //get details of that product, its author and publisher
            Product prdDetails = new ProductDao().getOne(id);
            Type prdType = new TypeDao().getOne(prdDetails.getTypeId());
            Author prdAuthor = new AuthorDao().getOne(prdDetails.getAuthorId());
            Publisher prdPublisher = new PublisherDao().getOne(prdDetails.getPublisherId());
            //get category of product
            if (prdDetails.getTypeId() == 1) {
                VN_category prdCategory = new VN_categoryDao().getOne(prdDetails.getVnCategoryId());
                request.setAttribute("productCategory", prdCategory);
            } else {
                FR_category prdCategory = new FR_categoryDao().getOne(prdDetails.getFrCategoryId());
                request.setAttribute("productCategory", prdCategory);
            }
            //get image of product, all products with same author or same publisher
            List<Image> prdImg = new ImageDao().getAllByProductId(id);
            List<Product> prdSmAuthor = new ProductDao().getSameAuthor(authorId);
            List<Product> prdSmPublisher = new ProductDao().getSamePublisher(publisherId);
            //get all categories for navbar            
            List<FR_category> lsFrCat = new FR_categoryDao().getAll();
            List<VN_category> lsVnCat = new VN_categoryDao().getAll();

            //send all data to jsp to display
            request.setAttribute("product", prdDetails);
            request.setAttribute("productType", prdType);
            request.setAttribute("productAuthor", prdAuthor);
            request.setAttribute("productPublisher", prdPublisher);
            request.setAttribute("lsImage", prdImg);
            request.setAttribute("sameAuthor", prdSmAuthor);
            request.setAttribute("samePublisher", prdSmPublisher);
            request.setAttribute("lsFrCat", lsFrCat);
            request.setAttribute("lsVnCat", lsVnCat);
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
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
