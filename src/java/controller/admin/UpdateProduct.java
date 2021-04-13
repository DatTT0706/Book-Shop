/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.GenerateRandomString;

/**
 *
 * @author TRANTATDAT
 */
@WebServlet(name = "UpdateProduct", urlPatterns = {"/admin/update-product"})
public class UpdateProduct extends HttpServlet {

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
        String sProductId = request.getParameter("id");
        if (sProductId != null) {
            int productId = Integer.parseInt(sProductId);
            Product oldProduct = new ProductDao().getOne(productId);
            List<Type> lsType = new TypeDao().getAll();
            List<FR_category> lsFrCat = new FR_categoryDao().getAll();
            List<VN_category> lsVnCat = new VN_categoryDao().getAll();
            List<Author> lsAuthor = new AuthorDao().getAll();
            List<Publisher> lsPublishers = new PublisherDao().getAll();

            request.setAttribute("author", lsAuthor);
            request.setAttribute("publisher", lsPublishers);
            request.setAttribute("type", lsType);
            request.setAttribute("fr_categories", lsFrCat);
            request.setAttribute("vn_categories", lsVnCat);
            request.setAttribute("product", oldProduct);
            request.getRequestDispatcher("update-product.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {
                System.out.println("Error");
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                System.out.println("CHECK");
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    System.err.println(e.getMessage());
                }
                Iterator iter = items.iterator();
                HashMap params = new HashMap();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(new String(item.getFieldName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                                new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            System.out.println("Part: " + itemName);

                            String readPart = getServletContext().getRealPath("/");
                            String partUpload = readPart.replace("build\\", "") + "assets\\images\\products\\" + fileName;
                            //System.out.println("Real part: " + partUpload);

                            File saveFile = new File(partUpload);
                            item.write(saveFile);
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
                String name = (String) params.get("name");

                int oldId = Integer.parseInt((String) params.get("oldId"));
                int authorId = Integer.valueOf((String) params.get("author"));
                int publisherId = Integer.valueOf((String) params.get("publisher"));
                int typeId = Integer.valueOf((String) params.get("type"));
                int frCategoryId = Integer.valueOf((String) params.get("fr_category"));
                int vnCategoryId = Integer.valueOf((String) params.get("vn_category"));
                double price = Double.valueOf((String) params.get("price"));
                int quantity = Integer.valueOf((String) params.get("quantity"));
                int status = Integer.valueOf((String) params.get("status"));
                String description = (String) params.get("description");

                Image a = new ImageDao().findByName(fileName);
                if (a != null) {
                    new ImageDao().remove(a.getId());
                }

                Product updatedProduct = Product.builder()
                        .typeId(typeId)
                        .name(name)
                        .authorId(authorId)
                        .publisherId(publisherId)
                        .frCategoryId(frCategoryId)
                        .vnCategoryId(vnCategoryId)
                        .quantity(quantity)
                        .price(price)
                        .status(status)
                        .description(description)
                        .imgName(fileName)
                        .build();

                boolean isCheck = new ProductDao().update(oldId, updatedProduct);
                System.out.println(isCheck);

                Image newImage = Image.builder()
                        .name(fileName)
                        .productId(oldId)
                        .build();
                new ImageDao().add(newImage);

                if (isCheck) {
                    
                    request.setAttribute("message", "Save product successfully");
                    request.getRequestDispatcher("redirectpage").forward(request, response);
                }
            }

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
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
