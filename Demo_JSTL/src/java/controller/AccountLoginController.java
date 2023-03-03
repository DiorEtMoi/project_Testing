/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Account;
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
public class AccountLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         HttpSession session = req.getSession();
        if (req.getSession().getAttribute("AccSession") != null) {
            req.getSession().removeAttribute("AccSession");
            req.getSession().removeAttribute("customer");
            
        }
        req.getRequestDispatcher("../signin.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("msg") != null) {
            session.removeAttribute("msg");
        }
        String email = req.getParameter("txtEmail");
        String pass = req.getParameter("txtPass");
        AccountDAO a = new AccountDAO();
        Account acc = a.getAccount(email, pass);

        if (acc != null) {
            req.getSession().setAttribute("AccSession", acc);
            if (acc.getRole() == 2) {
                Customer c = a.getCustomer(acc.getCustomerID());
                req.getSession().setAttribute("customer", c);
            }

        }
    }

}
