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
public class pagingByAjax extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = Integer.parseInt(req.getParameter("index"));
        int size = 5;
        ArrayList<Product> listProduct = new ProductDAO().getPage(index, size);
         String contextPath = req.getContextPath();
          PrintWriter print = resp.getWriter();
          print.println("  <table id=\"orders\">\n" +
"                        <tr>\n" +
"                            <th>ProductID</th>\n" +
"                            <th>ProductName</th>\n" +
"                            <th>UnitPrice</th>\n" +
"                            <th>Unit</th>\n" +
"                            <th>UnitsInStock</th>\n" +
"                            <th>Category</th>\n" +
"                            <th>Discontinued</th>\n" +
"                            <th></th>\n" +
"                        </tr>");
        for (Product product : listProduct) {
            if(product.getQuantityPerUnit() == null){
                product.setQuantityPerUnit("");
            }
            print.println(" <tr>\n" +
"                                <td><a href=\"order-detail.html?id=5\">#"+product.getProductID()+"</a></td>\n" +
"                                <td>"+product.getProductName()+"</td>\n" +
"                                <td>"+product.getUnitPrice()+"</td>\n" +
"                                <td>"+product.getQuantityPerUnit()+"</td>\n" +
"                                <td>"+product.getUnitsInStock()+"</td>\n" +
"                                <td>"+product.getCategory().getCategoryName()+"</td>\n" +
"                                <td>"+product.isDiscontinued()+"</td>\n" +
"                                <td>\n" +
"                                    <a href=\""+contextPath+"/admin/product/edit?id="+product.getProductID()+"\">Edit</a> &nbsp; | &nbsp; \n" +
"                                    <a href=\""+contextPath+"/admin/product/delete?id="+product.getProductID()+"\">Delete</a>\n" +
"                                </td>\n" +
"                            </tr>");
        }
        print.println("</table>");
    }
    
}
