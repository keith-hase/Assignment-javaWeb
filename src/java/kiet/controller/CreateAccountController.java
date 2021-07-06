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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kiet.account.AccountDAO;
import kiet.account.CreateAccountError;

/**
 *
 * @author keith
 */
@WebServlet(name = "CreateAccountController", urlPatterns = {"/CreateAccountController"})
public class CreateAccountController extends HttpServlet {

    private static final String SUCCESS = "login.html";
    private static final String FAIlED = "insert.jsp";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String lastname = request.getParameter("txtLastname");
        String url = FAIlED;
        CreateAccountError errors = new CreateAccountError();
        boolean foundError = false;
        try {
            //1. Bắt lỗi trước khi insert
            if (username.trim().length() < 3 || username.trim().length() > 20) {
                foundError = true;
                errors.setUsernameLengthErr("Username is required from 3 to 20 character");
            }
            if (password.trim().length() < 3 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthErr("Password is required from 3 to 30 character");
            }
            if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatch("Confirm password must match password!!");
            }
            if (lastname.trim().length() < 3 || lastname.trim().length() > 30) {
                foundError = true;
                errors.setLastnameLengthErr("lastname is required from 3 to 30 character");
            }
            //2. Nếu bắt xong lỗi ta sẽ bắt đầu kiểm tra 
            //Đúng -> thực hiện process
            //Sai -> thực hiện đưa lỗi ra cho người dùng sửa nếu là lỗi của người dùng
            
            if (foundError) {
                request.setAttribute("ERRORDETAIL", errors);
                url = FAIlED;
            }else{
                AccountDAO dao = new AccountDAO();
                boolean result = dao.insertAccount(username, password, lastname, true);
                if (result) {
                    url = SUCCESS;
                }
            }

        } catch (NamingException e) {
            log("InsertController NamingEx : " + e.getMessage());
        }catch (SQLException e) {
            String sqlException = e.getMessage();
            if (sqlException.trim().contains("duplicate")) {
                foundError = true;
                errors.setUsernameIsExisted("This username is already existed !!");
                request.setAttribute("ERRORDETAIL", errors);
            }
            log("InsertController SQLEx : " + sqlException);
        }
        finally{
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
