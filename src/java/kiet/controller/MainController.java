/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author keith
 */
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.html";
    private static final String LOGINACCOUNT = "LoginController";
    private static final String SEARCHCONTROLLER = "SearchController";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String STARTUP_SERVLET = "StartupServlet";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String ADDTOCART_CONTROLLER = "AddtocartController";
    private static final String REMOVEFROMCART_CONTROLLER = "RemovetocartController";
    private static final String VIEW_CART_PAGE = "ViewCartController";
    private static final String VIEW_SHOP = "ViewShopController";
    private static final String ADD_PRODUCT = "AddProductController";
    private static final String SIGN_UP = "CreateAccountController";
    private static final String CHECK_OUT = "CheckoutController";
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
        String url = LOGIN_PAGE;
        String action = request.getParameter("btnAction");
        try {

            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("Login")) {
                    url = LOGINACCOUNT;
                } else if (action.equals("Search")) {
                    url = SEARCHCONTROLLER;
                } else if (action.equals("Delete")) {
                    url = DELETE_CONTROLLER;
                } else if (action.equals("Update")) {
                    url = UPDATE_CONTROLLER;
                } else if (action.equals("Logout")) {
                    url = LOGOUT_CONTROLLER;
                } else if (action.equals("Add to cart")) {
                    url = ADDTOCART_CONTROLLER;
                } else if (action.equals("View your cart")) {
                    url = VIEW_CART_PAGE;
                } else if (action.equals("Remove selected items")) {
                    url = REMOVEFROMCART_CONTROLLER;
                } else if (action.equals("View shop")) {
                    url = VIEW_SHOP;
                } else if (action.equals("Add Item")) {
                    url = ADD_PRODUCT;
                } else if (action.equals("Create new account")) {
                    url = SIGN_UP;
                }else if (action.equals("Checkout Order")) {
                    url = CHECK_OUT;
                }
                
                else{
                    log("Action unknow");
                }
            }
        } catch (Exception e) {
            log("Error at MainController" + e.getMessage());
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
