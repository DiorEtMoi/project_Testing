/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.Customer;
import dal.DBContext;
import dal.Items;
import dal.Note;
import dal.Order;
import dal.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class OrderDAO extends DBContext {

    public int getRandom(int min, int max) {
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return random_int;
    }

    public void newOrder(Order o) {
        try {
            String sql = "insert into Orders(CustomerID,EmployeeID,OrderDate,RequiredDate,Freight) values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, o.getCustomerID());
            ps.setInt(2, o.getEmployeeID());
            ps.setDate(3, o.getOrderDate());
            ps.setDate(4, o.getRequiredDate());
            ps.setDouble(5, o.getFreight());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Order getTop1Order(String cus) {
        try {
            String sql = "select * from Orders\n"
                    + "where CustomerID = ?\n"
                    + "order by OrderID desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cus);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt(1);
                Order o = new Order();
                o.setOrderID(orderID);
                return o;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void insertOrderDetails(int orderID, Items item) {
        try {
            String sql = "insert into [Order Details] (OrderID,ProductID,UnitPrice,Quantity,Discount) values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderID);
            ps.setInt(2, item.getProduct().getProductID());
            ps.setDouble(3, item.getProduct().getUnitPrice());
            ps.setInt(4, item.getQuantity());
            ps.setDouble(5, 0);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public ArrayList<Order> getOrderNotCompleted(Customer c) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String sql = "select * from Orders \n"
                    + "where CustomerID = ? and ShippedDate is null and RequiredDate is not null";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCustomerID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt(1);
                Date date = rs.getDate(4);
                Order o = new Order();
                o.setOrderID(orderID);
                o.setOrderDate(date);
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Items> getAllOrderD(int id) {
        ArrayList<Items> list = new ArrayList<>();
        try {
            String sql = "select o.*,p.ProductName,p.productID from [Order Details] as o\n"
                    + "join Products as p\n"
                    + "on p.ProductID = o.ProductID\n"
                    + "where OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String productName = rs.getString(6);
                int pid = rs.getInt(7);
                Double price = rs.getDouble(3);
                int quantity = rs.getInt(4);
                Product p = new Product();
                p.setProductName(productName);
                p.setProductID(pid);
                p.setUnitPrice(price);
                Items item = new Items(p, quantity);
                list.add(item);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Order> getOrderCompleted(Customer c) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String sql = "select * from Orders \n"
                    + "where CustomerID = ? and ShippedDate is not null";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCustomerID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt(1);
                Date date = rs.getDate(4);
                Order o = new Order();
                o.setOrderID(orderID);
                o.setOrderDate(date);
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Note> getAllTotalInMonths() {
        ArrayList<Note> list = new ArrayList<>();
        String sql = "select SUM(Freight)  as total,MONTH(OrderDate) from Orders\n"
                + "where YEAR(OrderDate) = YEAR(GETDATE())\n"
                + "group by MONTH(OrderDate)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double total = rs.getDouble(1);
                int index = rs.getInt(2);
                list.add(new Note(index, total));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public double getTotalWeek() {
        String sql = "select SUM(Freight) from Orders\n"
                + "where year(GETDATE()) = YEAR(OrderDate) and datepart(ww, getdate()) = datepart(ww, OrderDate)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double total = rs.getDouble(1);
                return total;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double getTotalOrder() {
        String sql = "select SUM(Freight) from Orders";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double total = rs.getDouble(1);
                return total;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getCountAllOrder(int size) {
        try {
            String sql = "select count(*) from Orders as o\n"
                    + "join Employees as e\n"
                    + "on e.EmployeeID = o.EmployeeID\n"
                    + "join Customers as c\n"
                    + "on c.CustomerID = o.CustomerID";
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

    public ArrayList<Order> getAllOrder(int size, int index) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select o.OrderID,o.OrderDate,o.RequiredDate,o.ShippedDate,o.Freight, e.EmployeeID, c.CustomerID from Orders as o\n"
                + "join Employees as e\n"
                + "on e.EmployeeID = o.EmployeeID\n"
                + "join Customers as c\n"
                + "on c.CustomerID = o.CustomerID\n"
                + "order by o.OrderID\n"
                + "offset ? row\n"
                + "fetch first ? rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * size);
            ps.setInt(2, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt(1);
                Date orderDate = rs.getDate(2);
                Date reDate = rs.getDate(3);
                Date shipDate = rs.getDate(4);
                Double total = rs.getDouble(5);
                int eID = rs.getInt(6);
                String cid = rs.getString(7);
                Order o = new Order();
                o.setOrderID(oid);
                o.setCustomerID(cid);
                o.setEmployeeID(eID);
                o.setFreight(total);
                o.setOrderDate(orderDate);
                o.setRequiredDate(reDate);
                o.setShipdate(shipDate);
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public String getCustomerName(String cid) {
        String sql = "select * from Customers\n"
                + "where CustomerID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cname = rs.getString(3);
                return cname;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public String getFullName(int eid) {
        String sql = "select LastName + ' ' + FirstName from Employees\n"
                + "where EmployeeID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, eid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String full = rs.getString(1);
                return full;
            }

        } catch (Exception e) {
        }
        return null;
    }

    public void deleteOrder(int oid) {
        try {
            String sql = "update Orders set RequiredDate = null where OrderID = ?";
            PreparedStatement ps1 = connection.prepareStatement(sql);
            ps1.setInt(1, oid);
            ps1.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getAllOrderFilter(Date from, Date to, int size) {
        String sql = "select count(*) from Orders\n"
                + "where OrderDate between ? and ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, from);
            ps.setDate(2, to);
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

    public ArrayList<Order> getAllOrderFilterByDate(int index, int size) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "select o.OrderID,o.OrderDate,o.RequiredDate,o.ShippedDate,o.Freight, e.EmployeeID, c.CustomerID from Orders as o\n"
                + "join Employees as e\n"
                + "on e.EmployeeID = o.EmployeeID\n"
                + "join Customers as c\n"
                + "on c.CustomerID = o.CustomerID\n"
                + "where OrderDate between '2022-10-20' and '2022-11-01'\n"
                + "order by OrderID\n"
                + "offset ? row\n"
                + "fetch first ? rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * size);
            ps.setInt(2, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt(1);
                Date orderDate = rs.getDate(2);
                Date reDate = rs.getDate(3);
                Date shipDate = rs.getDate(4);
                Double total = rs.getDouble(5);
                int eID = rs.getInt(6);
                String cid = rs.getString(7);
                Order o = new Order();
                o.setOrderID(oid);
                o.setCustomerID(cid);
                o.setEmployeeID(eID);
                o.setFreight(total);
                o.setOrderDate(orderDate);
                o.setRequiredDate(reDate);
                o.setShipdate(shipDate);
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new OrderDAO().getOrderByID(11079));
    }

    public Order getOrderByID(int id) {
        try {
            String sql = "select OrderID,OrderDate,ShippedDate from Orders\n"
                    + "where OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt(1);
                Date date = rs.getDate(2);
                Date ship = rs.getDate(3);
                Order o = new Order();
                o.setOrderID(oid);
                o.setShipdate(ship);
                o.setOrderDate(date);
                return o;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Order> getAllOrderCancel(String cusid) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String sql = "select * from Orders \n"
                    + "where CustomerID = ? and RequiredDate is null";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cusid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt(1);
                Date date = rs.getDate(4);
                Order o = new Order();
                o.setOrderID(orderID);
                o.setOrderDate(date);
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;
    }

}
