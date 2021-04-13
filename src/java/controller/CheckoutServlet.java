/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDetailDao;
import dao.CouponDao;
import dao.OrderDao;
import dao.OrdersDetailsDao;
import dao.OtherAddressDao;
import entity.Account;
import entity.AccountDetail;
import entity.Cart;
import entity.Coupon;
import entity.Order;
import entity.OtherAddress;
import java.io.IOException;
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
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("authSuccess");
        if (request.getParameter("coupon") != null) {
            
            double discountMoney = (double) session.getAttribute("totalMoney");
            if (request.getParameter("coupon-use").equals("true")) {
                String coupon = request.getParameter("coupon");
                Coupon couponFound = new CouponDao().findByCode(coupon);
                
                if (couponFound != null) {
                    discountMoney -= (discountMoney * 0.1);
                    session.setAttribute("totalMoney", discountMoney);
                    request.setAttribute("couponUsed", true);
                    request.setAttribute("message", "Coupon Used!");
                    couponFound.setUsed(true);
                    new CouponDao().updateStatus(couponFound.getId(), couponFound);
                }                
            }
        }
        AccountDetail accountDetail = new AccountDetailDao().getOne(a.getAccountDetailId());
        List<Coupon> lsCoupon = new CouponDao().getCouponOfAccount(a.getId());
        request.setAttribute("accountDetailInfo", accountDetail);
        request.setAttribute("lsCoupon", lsCoupon);
        request.setAttribute("message", request.getAttribute("message"));
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("authSuccess");
            double totalMoney = (double) session.getAttribute("totalMoney");
            boolean otherAddressConf = request.getParameter("btnOtherAddress").equals("true");
            
            String name = "";
            String mobile = "";
            String address = "";
            String note = "";
            
            if (otherAddressConf) {
                name = request.getParameter("name");
                mobile = request.getParameter("mobile");
                address = request.getParameter("address");
                note = request.getParameter("note");
            }

            //add order
            OrderDao od = new OrderDao();
            int orderId = od.addOrder(Order.builder()
                    .accountId(a.getId())
                    .totalMoney(totalMoney)
                    .note(note)
                    .status(1)
                    .build()
            );

            //add order details
            if (orderId > 0) {
                OrdersDetailsDao odd = new OrdersDetailsDao();
                boolean result = odd.add((List<Cart>) session.getAttribute("listCart"), orderId);
                //check other address
                if (otherAddressConf) {
                    //add other address for order
                    OtherAddress otherAddress = OtherAddress.builder()
                            .name(name)
                            .phoneNumber(mobile)
                            .address(address)
                            .orderId(orderId)
                            .build();
                    new OtherAddressDao().add(otherAddress);
                } else {
                    //add default address for order
                    AccountDetail accountDetail = new AccountDetailDao().getOne(a.getAccountDetailId());
                    OtherAddress otherAddress = OtherAddress.builder()
                            .name(accountDetail.getName())
                            .phoneNumber(accountDetail.getPhone())
                            .address(accountDetail.getAddress())
                            .orderId(orderId)
                            .build();
                    new OtherAddressDao().add(otherAddress);
                }
                
                if (result) {
                    //order sucessful, remove cart, totalmoney, totalProduct
                    session.removeAttribute("listCart");
                    session.removeAttribute("totalMoney");
                    session.removeAttribute("totalProduct");
                    session.removeAttribute("lsProductInStore");
                    
                    response.sendRedirect("thanks.jsp");
                } else {
                    //remove order
                    //write code here
                    od.removeOrder(orderId);
                    response.sendRedirect("checkout");
                }
            } else {
                response.sendRedirect("checkout");
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
