/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import entity.Account;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRANTATDAT
 */
@WebFilter(filterName = "AdminLoginFilter", urlPatterns = {"/admin/*"})
public class AdminLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("authSuccess");
        if (account != null) {
            if (account.getRoleId() == 1) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("../Products");
            }
        } else {
            res.sendRedirect("../login.jsp");
        }
    }

    @Override
    public void destroy() {
        
    }

}
