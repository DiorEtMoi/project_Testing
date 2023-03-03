/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Account;
import dal.Customer;
import dal.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.AccountDAO;
import model.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/account/profile/ordercancel":
                doGet_OrderCancel(req, resp);
                break;
            case "/account/profile/order_cancel":
                doget_cancel(req, resp);
                break;
            case "/account/profile":
                doGet_profile(req, resp);
                break;
            
        }
    }
    
    protected void doGet_OrderCancel(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        Customer c = (Customer)req.getSession().getAttribute("customer");
        ArrayList<Order> list = new OrderDAO().getAllOrderCancel(c.getCustomerID());
        req.setAttribute("list", list);
        req.getRequestDispatcher("/Order_cancel.jsp").forward(req, resp);
        
        
    }

    protected void doGet_profile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("AccSession");
        Customer c = new AccountDAO().getCustomer(acc.getCustomerID());
        req.setAttribute("c", c);
        req.getRequestDispatcher("../profile.jsp").forward(req, resp);
    }
    
    
    protected void doget_cancel(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        int id = Integer.parseInt(req.getParameter("oid"));
        OrderDAO dao = new OrderDAO();
        dao.deleteOrder(id);
        resp.sendRedirect(req.getContextPath() + "/account/profile/ordercancel");
    }

}
