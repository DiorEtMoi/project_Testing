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
import java.io.PrintWriter;
import java.util.ArrayList;
import model.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class SearchByAjax extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txtSearch = req.getParameter("txt");
        String cid = req.getParameter("cid");
        int id = 0;
        try {
            id = Integer.parseInt(cid);
        } catch (Exception e) {
        }
        ArrayList<Product> list = new ProductDAO().getSearchProduct(txtSearch, id);
        PrintWriter print = resp.getWriter();
        String contextPath = req.getContextPath();
        for (Product product : list) {
            print.println("<div class=\"product\" style=\"margin-bottom: 10px\">\n"
                    + "                 <div class=\"cart_product\">\n"
                    + "                    <div class=\"cart_img\">\n"
                    + "                       <a href=\""+contextPath+"/product?pid="+product.getProductID()+"\">\n"
                    + "                            <img src=\"./img/1.jpg\" alt=\"\">\n"
                    + "                        </a>"
                    + "                    </div>\n"
                    + "                    <div class=\"info_product\">\n"
                    + "                        <h1>" + product.getProductName() + "</h1>\n"
                    + "                        <p>" + product.getUnitPrice() + "</p>\n"
                    + "                        <button><a href=\"" + contextPath + "/shop_cart?pid=" + product.getProductID() + "&action=buy\">Buy now</a></button>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>");
        }

    }

}
