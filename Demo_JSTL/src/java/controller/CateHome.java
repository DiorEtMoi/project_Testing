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
import model.CategoryDAO;
import model.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class CateHome extends HttpServlet{
    private ProductDAO pro = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Category> allcate = new ProductDAO().getAllCate();
        String paramSize = req.getParameter("size");
        int size = 0;
        if(paramSize == null){
            paramSize = "12";
        }
        size = Integer.parseInt(paramSize);
        String sIndex = req.getParameter("index");
        String cid = req.getParameter("cid");
        if(sIndex == null){
            sIndex = "1";
        }
        int index = Integer.parseInt(sIndex);
        int id = 0;
        try {
            id = Integer.parseInt(cid);
            if(id <= 0 || id > allcate.size()){
                id = 1;
            }
        } catch (Exception e) {
            
        }
        int endpage = pro.getCount(id, size);
        ArrayList<Product> list = pro.getProductPage(id, index, size);
        req.setAttribute("cid", id);
        req.setAttribute("index", index);
        req.setAttribute("endpage", endpage);
        req.setAttribute("list", list);
        req.setAttribute("listcate", allcate);
        req.setAttribute("cid", id);
        req.getRequestDispatcher("category.jsp").forward(req, resp);
    }
    
    
}
