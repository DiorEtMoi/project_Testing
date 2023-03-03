/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.Customer;
import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CustomerDAO extends DBContext {

    public int getCountCustomer(int size) {
        try {
            String sql = "select count(*) from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                int endpage = count / size;
                if (count % size != 0) {
                    endpage++;
                }
                return endpage;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public ArrayList<Customer> getAllCount(int size, int index) {
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "select * from Customers \n"
                + "order by CustomerID\n"
                + "offset ? row\n"
                + "fetch first ? rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1)*size);
            ps.setInt(2, size);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String cid = rs.getString(1);
                String comName = rs.getString(2);
                String contactName = rs.getString(3);
                String contactTitle = rs.getString(4);
                String address = rs.getString(5);
                Date date = rs.getDate(6);
                Customer c = new Customer(cid, comName, contactName, contactTitle, address, date);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
