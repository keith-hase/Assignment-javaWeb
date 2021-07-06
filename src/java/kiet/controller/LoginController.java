/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kiet.account.AccountDAO;

/**
 *
 * @author keith
 */
public class LoginController extends HttpServlet {

    private static final String SUCCESS_LOGIN = "admin.jsp";
    private static final String FALSE_LOGIN = "error.jsp";

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
        PrintWriter out = response.getWriter();
        String url = FALSE_LOGIN;
         String result = null;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            // Call DAO method
            if (!"".equals(username.trim()) || !"".equals(password.trim())) {
                AccountDAO dao = new AccountDAO();
                 result = dao.checkLogin(username, password);
            }

            if (result != null) {
                url = SUCCESS_LOGIN;

                //Sử dụng cookies
//                Cookie cookie = new Cookie(username, request.getParameter("txtPassword"));
//                cookie.setMaxAge(60 * 4);
//                response.addCookie(cookie);
                // Sử dụng session 
                HttpSession session = request.getSession();
                session.setAttribute("LASTNAME", result);

            } else {
                url = FALSE_LOGIN;
            }
        } catch (SQLException e) {
            log("LoginController SQLEx " + e.getMessage());
        } catch (NamingException e) {
            log("LoginController NamingEx " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
