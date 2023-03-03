/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import dal.Items;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class addToCartAjax extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String pid = req.getParameter("pid");
        ArrayList<Items> list = new ArrayList<>();
        int id = 0;
        if (pid == null) {
            id = 1;
        } else {
            id = Integer.parseInt(pid);
        }
        if (session.getAttribute("cart") == null) {
            list.add(new Items(new ProductDAO().getProductByID(id), 1));
        } else {
            list = (ArrayList<Items>) session.getAttribute("cart");
            boolean check = false;
            for (Items item : list) {
                if (item.getProduct().getProductID() == id) {
                    item.setQuantity(item.getQuantity() + 1);
                    check = true;
                    break;
                }
            }
            if (check == false) {
                list.add(new Items(new ProductDAO().getProductByID(id), 1));
            }

        }
        session.setAttribute("cart", list);
        PrintWriter print = resp.getWriter();
        String contextPath = req.getContextPath();
        print.println("<a href=\"" + contextPath + "/shop_cart\">Cart: " + list.size() + "</a>");
    }

}
