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
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import model.AccountDAO;

/**
 *
 * @author ADMIN
 */
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = Date.valueOf(LocalDate.now());
        String companyName = req.getParameter("txtCompanyName");
        String contactname = req.getParameter("txtContactName");
        String ContactTitle = req.getParameter("txtContactTitle");
        String address = req.getParameter("txtAddress");
        String Email = req.getParameter("txtEmail");
        String pass = req.getParameter("txtPass");
        String cusID = new AccountDAO().RandomStr(5);
        Account acc = new Account(0, Email, pass, cusID, 0, 2);
        Customer cust = new Customer(cusID, companyName, contactname, ContactTitle, address,date);
 

        AccountDAO a = new AccountDAO();
       int result = a.createAccount(cust, acc);
        if(result > 0){
            resp.sendRedirect("../signin.jsp");
        }else{
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        }
        
    }

}
