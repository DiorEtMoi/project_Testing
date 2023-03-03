/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Email;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.AccountDAO;

/**
 *
 * @author ADMIN
 */
public class forgotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/forgot.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        String newPass = new AccountDAO().RandomStr(6);
        String subject = "Your Password has been reset";
        String content = "Hi this is new Password, Please remember password dont lose it again :" + newPass;
        AccountDAO acc = new AccountDAO();
        acc.resetPass(email, newPass);
        String mess = "";

        try {
            Email.sendEmail("smtp.gmail.com", "587", "dinhhoan0019c@gmail.com", "sendMail", "sbbfiegozzibcnmo", email, subject, content);
            mess = "Your password has been reset. Please check your e-mail.";
        } catch (Exception e) {
            e.printStackTrace();
            mess = "There were an error: " + e.getMessage();
        } finally {
            req.setAttribute("mess", mess);
            req.getRequestDispatcher("forgot.jsp").forward(req, resp);
        }
    }

}
