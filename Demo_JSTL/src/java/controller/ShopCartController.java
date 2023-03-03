/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Items;
import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import model.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class ShopCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (action == null) {
            if (session.getAttribute("cart") == null) {
                resp.sendRedirect("home");
            } else {
                resp.sendRedirect("cart.jsp");
            }
        } else {

            if (action.equalsIgnoreCase("buy")) {
                doGet_Buy(req, resp);
            } else if (action.equalsIgnoreCase("remove")) {
                doGet_remove(req, resp);
            } else if (action.equalsIgnoreCase("desc")) {
                doGet_des(req, resp);
            } else {
                resp.sendRedirect("home");
            }
        }
    }

    protected void doGet_Buy(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int id = 0;
        if (req.getParameter("pid") != null) {
            id = Integer.parseInt(req.getParameter("pid"));
        }
        if (session.getAttribute("cart") == null) {
            ArrayList<Items> list = new ArrayList<>();
            Product p = new ProductDAO().getProductByID(id);
            list.add(new Items(p, 1));
            session.setAttribute("cart", list);
        } else {
            ArrayList<Items> list = (ArrayList<Items>) session.getAttribute("cart");
            boolean flag = false;
            for (Items items : list) {
                if (items.getProduct().getProductID() == id) {
                    items.setQuantity(items.getQuantity() + 1);
                    flag = true;
                }
            }
            if (flag == false) {
                Product p = new ProductDAO().getProductByID(id);
                list.add(new Items(p, 1));
            }
            session.setAttribute("cart", list);
        }
        resp.sendRedirect("cart.jsp");
    }

    protected void doGet_remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int id = 0;
        if (req.getParameter("pid") != null) {
            id = Integer.parseInt(req.getParameter("pid"));
        }
        ArrayList<Items> list = (ArrayList<Items>) session.getAttribute("cart");
        for (Items items : list) {
            if (items.getProduct().getProductID() == id) {
                list.remove(items);
                break;
            }
        }
        session.setAttribute("cart", list);
        if (list.size() == 0) {
            resp.sendRedirect("home");
        } else {
            resp.sendRedirect("cart.jsp");
        }

    }

    protected void doGet_des(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int id = 0;
        if (req.getParameter("pid") != null) {
            id = Integer.parseInt(req.getParameter("pid"));
        }
        ArrayList<Items> list = (ArrayList<Items>) session.getAttribute("cart");
        for (Items item : list) {
            if (item.getProduct().getProductID() == id) {
                if (item.getQuantity() == 1) {
                    list.remove(item);
                    break;
                } else {
                    item.setQuantity(item.getQuantity() - 1);
                }
            }
        }
        session.setAttribute("cart", list);
        if (list.size() == 0) {
            resp.sendRedirect("home");
        } else {
            resp.sendRedirect("cart.jsp");
        }

    }

}
