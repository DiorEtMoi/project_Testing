/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.AccountDAO;

/**
 *
 * @author ADMIN
 */
public class changeProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("../profile.jsp");
    }
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer)session.getAttribute("customer");
        String companyName = req.getParameter("companyName");
        String contactName = req.getParameter("contactName");
        String contactTitle = req.getParameter("contactTitle");
        String address = req.getParameter("address");
        Customer c = new Customer(customer.getCustomerID(), companyName, contactName, contactTitle, address);
        AccountDAO a = new AccountDAO();
        a.updateCustomer(c);
        session.setAttribute("customer", c);
        resp.sendRedirect("../profile.jsp");
        
    }

}
