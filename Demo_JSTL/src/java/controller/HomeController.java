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
import java.util.HashMap;
import model.ProductDAO;

/**
 *
 * @author ADMIN
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Product> listHot = new ProductDAO().getHotproduct();
        ArrayList<Product> listBest = new ProductDAO().getBestSale();
        String sIndex = req.getParameter("index");
        int size = 4;
        int endPage = new ProductDAO().getCountAllProduct(size);
        int index = 0;
        try {
            if(sIndex == null){
                index = 1;
            }else{
                index = Integer.parseInt(sIndex);
                if(index < 0 || index > endPage){
                    index = 1;
                }
            }
        } catch (Exception e) {
            index = 1;
        }
        int max = index+4;
        while(max > endPage){
            max--;
        }
        ArrayList<Product> list = new ProductDAO().getPage(index, size);
        ArrayList<Category> listcate = new ProductDAO().getAllCate();
        req.setAttribute("max", max);
        req.setAttribute("endpage", endPage);
        req.setAttribute("index", index);
        req.setAttribute("listnew", list);
        req.setAttribute("listCate", listcate);
        req.setAttribute("best", listBest);
        req.setAttribute("hot", listHot);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
