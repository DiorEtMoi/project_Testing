/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Category;
import dal.Customer;
import dal.Note;
import dal.Order;
import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.AccountDAO;
import model.CustomerDAO;
import model.OrderDAO;
import model.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class adminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/admin/dashboard":
                dashboard(req, resp);
                break;
            case "/admin/product":
                adminProduct(req, resp);
                break;
            case "/admin/order":
                doGet_order(req, resp);
                break;
            case "/admin/customer":
                doGet_customer(req, resp);
                break;
            case "/admin/order/delete":
                doget_deleteOrder(req, resp);
                break;
            case "/admin/order/date":
                doPost_order(req, resp);
                break;
            case "/admin/order/detail":
                doGet_detailOrder(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/admin/order/date":
                doPost_order(req, resp);
                break;
        }
    }

    protected void dashboard(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int newCus = new AccountDAO().getAllCustomerInMonths();
        int totalCus = new AccountDAO().getTotalCustomer();
        double weektotal = new OrderDAO().getTotalWeek();
        double totalOrder = new OrderDAO().getTotalOrder();
        int countGuest = new AccountDAO().getCountGuest();
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(0.0);
        }
        for (Note note : new OrderDAO().getAllTotalInMonths()) {
            list.set(note.getMonth() - 1, note.getTotal());
        }
        req.setAttribute("allguest", countGuest);
        req.setAttribute("newCus", newCus);
        req.setAttribute("totalCus", totalCus);
        req.setAttribute("total", list);
        req.setAttribute("totalOrder", totalOrder);
        req.setAttribute("totalWeek", weektotal);
        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }

    protected void adminProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Category> listCate = new ProductDAO().getAllCate();
        String sIndex = req.getParameter("index");
        int id = 0;
        if (sIndex == null) {
            id = 1;
        } else {
            id = Integer.parseInt(sIndex);
        }
        int size = 5;
        int endpage = new ProductDAO().getCountAllProduct(size);
        ArrayList<Product> listP = new ProductDAO().getPage(id, size);
        req.setAttribute("listCate", listCate);
        req.setAttribute("listProduct", listP);
        req.setAttribute("endpage", endpage);
        req.getRequestDispatcher("../admin_product.jsp").forward(req, resp);
    }

    //admin order
    protected void doGet_order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String curent = req.getParameter("index");
        int index = 0;
        int size = 10;
        int endPage = new OrderDAO().getCountAllOrder(size);
        try {
            if (curent == null) {
                index = 1;
            } else {
                index = Integer.parseInt(curent);
                if (index < 0 || index > endPage) {
                    index = 1;
                }
            }
        } catch (Exception e) {
            index = 1;
        }
        int max = index + 4;
        while (max > endPage) {
            max--;
        }

        ArrayList<Order> listO = new OrderDAO().getAllOrder(size, index);
        req.setAttribute("max", max);
        req.setAttribute("listO", listO);
        req.setAttribute("endpage", endPage);
        req.setAttribute("index", index);
        req.getRequestDispatcher("/order_admin.jsp").forward(req, resp);
    }

    protected void doget_deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String oid = req.getParameter("oid");
        int id = 0;
        if (oid == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/order");
        } else {
            id = Integer.parseInt(oid);
            OrderDAO o = new OrderDAO();
            o.deleteOrder(id);
            resp.sendRedirect(req.getContextPath() + "/admin/order");
        }
    }
    // DOGET order details

    protected void doGet_detailOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("oid"));

        OrderDAO dao = new OrderDAO();
        Order o = dao.getOrderByID(id);
        req.setAttribute("o", o);
        req.getRequestDispatcher("/order_detail_admin.jsp").forward(req, resp);
    }

//    dopost Order filter date
    protected void doPost_order(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Date from = Date.valueOf(req.getParameter("from"));
        Date to = Date.valueOf(req.getParameter("to"));
        int size = 10;
        int endPage = new OrderDAO().getAllOrderFilter(from, to, size);
        String curent = req.getParameter("index");
        int index = 0;
        try {
            if (curent == null) {
                index = 1;
            } else {
                index = Integer.parseInt(curent);
                if (index < 0 || index > endPage) {
                    index = 1;
                }
            }
        } catch (Exception e) {
            index = 1;
        }
        int max = index + 4;
        while (max > endPage) {
            max--;
        }
        int check = 1;
        ArrayList<Order> listO = new OrderDAO().getAllOrderFilterByDate(index, size);
        req.setAttribute("listO", listO);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("check", check);
        req.setAttribute("index", index);
        req.setAttribute("max", max);
        req.setAttribute("endpage", endPage);
        req.getRequestDispatcher("/order_admin.jsp").forward(req, resp);

    }
    //doGet customer admin

    protected void doGet_customer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int size = 10;
        String curent = req.getParameter("index");
        int endPage = new CustomerDAO().getCountCustomer(size);
        int index = 0;
        try {
            if (curent == null) {
                index = 1;
            } else {
                index = Integer.parseInt(curent);
                if (index < 0 || index > endPage) {
                    index = 1;
                }
            }
        } catch (Exception e) {
            index = 1;
        }
        int max = index + 4;
        while (max > endPage) {
            max--;
        }
        ArrayList<Customer> list = new CustomerDAO().getAllCount(size,index);
        req.setAttribute("endpage", endPage);
        req.setAttribute("index", index);
        req.setAttribute("max", max);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/admin_customer.jsp").forward(req, resp);
    }

}
