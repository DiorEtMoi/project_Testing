/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import dal.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends DBContext {

    public ArrayList<Product> getAllProductByCate(int id) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Products \n"
                    + "where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int pid = rs.getInt(1);
                String proName = rs.getString(2);
                double price = rs.getDouble(3);
                Product p = new Product();
                p.setProductID(pid);
                p.setProductName(proName);
                p.setUnitPrice(price);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
