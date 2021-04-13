/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDao;
import dao.AccountDetailDao;
import entity.Account;
import entity.AccountDetail;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.GenerateRandomString;
import util.SendEmail;

/**
 *
 * @author TRANTATDAT
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            boolean gender = request.getParameter("gender").equals("true");
            String address = request.getParameter("address");

            Account checkDupEmail = new AccountDao().findByEmail(email);

            if (checkDupEmail == null) {
                AccountDetail accountDetail = AccountDetail.builder()
                        .name(name)
                        .phone(phone)
                        .gender(gender)
                        .address(address)
                        .build();
                boolean addDetails = new AccountDetailDao().add(accountDetail);

                int accoundDetailId = new AccountDetailDao().getNewestAccountDetailsId();

                String activeCode = GenerateRandomString.generateString(10);

                Account account = Account.builder()
                        .email(email)
                        .password(password)
                        .accountDetailId(accoundDetailId)
                        .roleId(2)
                        .activeCode(activeCode)
                        .status(3)
                        .build();

                boolean addAccount = new AccountDao().add(account);

                if (addDetails && addAccount) {
                    String subject = "Active code for account at Golden Book Shop";
                    String message = "Your active code at Golden Book Shop is: " + activeCode;

                    new SendEmail(email, subject, message);
                    
                    request.setAttribute("message", "Register successfully. Please login and enter active code");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }

            if (checkDupEmail != null) {
                request.setAttribute("message", "This email has been used. Please use a different email!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

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
