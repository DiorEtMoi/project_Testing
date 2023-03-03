/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Customer;
import dal.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class AllOrderProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("AccSession") == null) {
            resp.sendRedirect("../signin.jsp");
        } else {
            Customer c = (Customer) session.getAttribute("customer");
            ArrayList<Order> listorderNot = new OrderDAO().getOrderNotCompleted(c);
            ArrayList<Order> listorder = new OrderDAO().getOrderCompleted(c);
            req.setAttribute("listN", listorderNot);
            req.setAttribute("listC", listorder);
            req.getRequestDispatcher("../profile_order.jsp").forward(req, resp);
        }
    }

}
