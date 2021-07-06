/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kiet.Item.ItemDAO;
import kiet.Item.ItemDTO;
import kiet.cart.CartObject;

/**
 *
 * @author keith
 */
@WebServlet(name = "ViewCartController", urlPatterns = {"/ViewCartController"})
public class ViewCartController extends HttpServlet {

    private static final String VIEW_CART_PAGE = "viewCart.jsp";

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
        String url = VIEW_CART_PAGE;
        try {

            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CART");

            if (cart != null) {

                Map<String, Integer> item = cart.getItem();
                List<ItemDTO> list = new ArrayList<>();
                ItemDTO dto;
                ItemDAO dao = new ItemDAO();
                if (item != null) {
                    for (String key : item.keySet()) {
                        int keyInteger = Integer.parseInt(key);
                        dto = dao.getProductById(keyInteger);

                        list.add(dto);
                    }

                    session.setAttribute("DETAILITEM", list);
                }
            }
            else{
                session.invalidate();
            }

        } catch (NamingException e) {
            log("ViewCartController NamingEx : " + e.getMessage());
        } catch (SQLException e) {
            log("ViewCartController SQLEx : " + e.getMessage());
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
