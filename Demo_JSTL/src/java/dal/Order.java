/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.util.ArrayList;
import model.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class Order {
    private int orderID;
    private String customerID;
    private int employeeID;
    private Date orderDate;
    private Date requiredDate;
    private Date shipdate;
    private ArrayList<Items> items;
    private double freight;

    public Order() {
    }

    public Order(int orderID, String customerID, int employeeID, Date orderDate, Date requiredDate, ArrayList<Items> items, double freight) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.items = items;
        this.freight = freight;
    }

    public Date getShipdate() {
        return shipdate;
    }

    public void setShipdate(Date shipdate) {
        this.shipdate = shipdate;
    }
    
    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

   
   
    

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public ArrayList<Items> getAllOrderDetails(){
        OrderDAO o = new OrderDAO();
        ArrayList<Items> list = o.getAllOrderD(orderID);
        return list;
    }
    public String getCustomerName(){
        OrderDAO o = new OrderDAO();
        return o.getCustomerName(customerID);
    }
      public String getFullEName(){
        OrderDAO o = new OrderDAO();
        return o.getFullName(employeeID);
    }
}
