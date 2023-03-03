/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class ProductController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pid = req.getParameter("pid");
        int id = 0;
        if(pid == null){
            pid = "1";
        }
        try {
            id = Integer.parseInt(pid);
            if(id < 0){
                id = 1;
            }
        } catch (Exception e) {
        }
        Product p = new ProductDAO().getProductByID(id);
        req.setAttribute("p", p);
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }
    
}
