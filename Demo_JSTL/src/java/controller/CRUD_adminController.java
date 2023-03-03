/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Category;
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
public class CRUD_adminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getContextPath();
        String action = req.getServletPath();
        switch (action) {
            case "/admin/product/create":
                showForm(req, resp);
                break;
            case "/admin/product/edit":
                edit_product(req, resp);
                break;
            case "/admin/product/delete":
                doget_delete(req, resp);
                break;
            case "/admin/product/searchcate":
                resp.sendRedirect(req.getContextPath() + "/admin/product");
                break;
            case "/admin/product/searchtxt":
                resp.sendRedirect(req.getContextPath() + "/admin/product");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/product/create":
                create_newProduct(req, resp);
                break;
            case "/admin/product/edit":
                doPost_edit(req, resp);
                break;
            case "/admin/product/searchcate":
                doPost_searchCate(req, resp);
                break;
            case "/admin/product/searchtxt":
                doPost_searchBytxt(req, resp);
                break;
        }
    }

    protected void doget_delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        int pid = 0;
        pid = Integer.parseInt(id);
        ProductDAO dao = new ProductDAO();
        dao.deleteByID(pid);
        resp.sendRedirect(req.getContextPath() + "/admin/product");
    }

    protected void edit_product(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        int pid = 0;
        pid = Integer.parseInt(id);
        Product p = new ProductDAO().getProductByID(pid);
        ArrayList<Category> list = new ProductDAO().getAllCate();
        req.setAttribute("listCate", list);
        req.setAttribute("p", p);
        req.getRequestDispatcher("/admin_edit.jsp").forward(req, resp);
    }

    protected void doPost_searchCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cateId = Integer.parseInt(req.getParameter("cid"));
        int index = 0;
        if (req.getParameter("index") == null) {
            index = 1;
            int size = 5;
            int endpage = new ProductDAO().getCount(cateId, size);
            ArrayList<Product> listP = new ProductDAO().getProductPage(cateId, index, size);
            ArrayList<Category> listc = new ProductDAO().getAllCate();
            req.setAttribute("listCate", listc);
            req.setAttribute("cid", cateId);
            req.setAttribute("endpage", endpage);
            req.setAttribute("listProduct", listP);
            req.setAttribute("index", index);
            req.getRequestDispatcher("/admin_search.jsp").forward(req, resp);
        } else {
            index = Integer.parseInt(req.getParameter("index"));
            int size = 5;
            int endpage = new ProductDAO().getCount(cateId, size);
            ArrayList<Product> listP = new ProductDAO().getProductPage(cateId, index, size);
            ArrayList<Category> listc = new ProductDAO().getAllCate();
            req.setAttribute("listCate", listc);
            req.setAttribute("cid", cateId);
            req.setAttribute("endpage", endpage);
            req.setAttribute("listProduct", listP);
            req.setAttribute("index", index);
            req.getRequestDispatcher("/admin_search.jsp").forward(req, resp);
        }

    }

    protected void doPost_edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("pid"));

        String productName = req.getParameter("txtProductName");
        double unitPrice = Double.parseDouble(req.getParameter("txtUnitPrice"));
        String quantity = req.getParameter("txtQuantityPerUnit");
        int inStocks = Integer.parseInt(req.getParameter("txtUnitsInStock"));
        int cid = Integer.parseInt(req.getParameter("ddlCategory"));
        String dis = req.getParameter("chkDiscontinued");
        boolean discontinue = true;
        if (dis != null) {
            discontinue = false;
        }
        Category c = new Category();
        c.setCategoryID(cid);
        Product p = new Product();
        p.setProductID(id);
        p.setProductName(productName);
        p.setUnitPrice(unitPrice);
        p.setQuantityPerUnit(quantity);
        p.setDiscontinued(discontinue);
        p.setUnitsInStock(inStocks);
        p.setCategory(c);
        ProductDAO dao = new ProductDAO();
        dao.UpdateProduct(p);
        resp.sendRedirect(req.getContextPath() + "/admin/product");
    }

    protected void create_newProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productName = req.getParameter("txtProductName");
        double unitPrice = Double.parseDouble(req.getParameter("txtUnitPrice"));
        String quantity = req.getParameter("txtQuantityPerUnit");
        int inStocks = Integer.parseInt(req.getParameter("txtUnitsInStock"));
        int cid = Integer.parseInt(req.getParameter("ddlCategory"));
        String dis = req.getParameter("chkDiscontinued");
        boolean discontinue = true;
        if (dis != null) {
            discontinue = false;
        }
        Category c = new Category();
        c.setCategoryID(cid);
        Product p = new Product();
        p.setProductName(productName);
        p.setUnitPrice(unitPrice);
        p.setQuantityPerUnit(quantity);
        p.setDiscontinued(discontinue);
        p.setUnitPrice(unitPrice);
        p.setUnitsInStock(inStocks);
        p.setCategory(c);
        ProductDAO dao = new ProductDAO();
        dao.createPro(p);
        resp.sendRedirect(req.getContextPath() + "/admin/product");
    }

    protected void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Category> listCate = new ProductDAO().getAllCate();
        req.setAttribute("listCate", listCate);
        req.getRequestDispatcher("/create_product.jsp").forward(req, resp);
    }

    //Search Product by txt
    protected void doPost_searchBytxt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txt = req.getParameter("txt");
        ArrayList<Category> listcate = new ProductDAO().getAllCate();
        req.setAttribute("listCate", listcate);
        req.setAttribute("txt", txt);
        int index = 0;
        if (req.getParameter("index") == null) {
            index = 1;
            int size = 5;
            int endpage = new ProductDAO().getCountProductTxt(txt, size);
            ArrayList<Product> listP = new ProductDAO().getProductBytxt(txt, index, size);
            req.setAttribute("endpage", endpage);
            req.setAttribute("listProduct", listP);
            req.setAttribute("index", index);
            req.getRequestDispatcher("/admin_search.jsp").forward(req, resp);
        }else{
            index = Integer.parseInt(req.getParameter("index"));
             int size = 5;
            int endpage = new ProductDAO().getCountProductTxt(txt, size);
            ArrayList<Product> listP = new ProductDAO().getProductBytxt(txt, index, size);
            req.setAttribute("endpage", endpage);
            req.setAttribute("listProduct", listP);
            req.setAttribute("index", index);
            req.getRequestDispatcher("/admin_search.jsp").forward(req, resp);
        }
    }
}
