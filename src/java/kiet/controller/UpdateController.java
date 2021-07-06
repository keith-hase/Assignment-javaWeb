/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kiet.account.AccountDAO;

/**
 *
 * @author keith
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR_PAGE = "error.html";

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
        String id = request.getParameter("txtID");
        String keyword = request.getParameter("keyword");

        String lastName = request.getParameter("txtLastname");
        // 1. Nhận giá trị vào từ checkBox nếu null sẽ chuyển thành rỗng và ko null sẽ nhận giá trị từ param
        // 2. Kiểm tra is role boolean từ chuỗi checkbox nhận đc có hay rỗng thì trả về true hoặc false
        String checkBoxOn = (request.getParameter("cbRole") == null ? "" : request.getParameter("cbRole"));
        boolean isRole = (checkBoxOn.equals("ON") ? true : false);

        System.out.println("check box: " + checkBoxOn);

        String url = ERROR_PAGE;
        try {
            AccountDAO dao = new AccountDAO();
            boolean updateResult = dao.updateAccount(id, lastName, isRole);
            if (updateResult) {
                url = "MainController?btnAction=Search&txtSearch=" + keyword;
            }
        } catch (SQLException e) {
            log("UpdateController SQLEx " + e.getMessage());
        } catch (NamingException e) {
            log("UpdateController NamingEx " + e.getMessage());
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
