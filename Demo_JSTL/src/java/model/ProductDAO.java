/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.Category;
import dal.DBContext;
import dal.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public ArrayList<Product> getHotproduct() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select top 4 * from(select p.*,o.Discount from [Order Details] as o\n"
                    + "join Products as p \n"
                    + "on p.ProductID= o.ProductID\n"
                    + ") as x\n"
                    + "order by x.Discount desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String productName = rs.getString(2);
                double price = rs.getDouble(5);
                Product p = new Product();
                p.setProductID(id);
                p.setUnitPrice(price);
                p.setProductName(productName);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Product> getBestSale() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select top 4 * from(select count(OrderID) as[Bought], p.ProductID,p.ProductName,p.UnitPrice from [Order Details] as o\n"
                    + "join Products as p \n"
                    + "on p.ProductID = o.ProductID\n"
                    + "group by p.ProductID,p.ProductName,p.UnitPrice) as x\n"
                    + "order by x.Bought desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(2);
                String productName = rs.getString(3);
                double price = rs.getDouble(4);
                Product p = new Product();
                p.setProductID(id);
                p.setUnitPrice(price);
                p.setProductName(productName);
                list.add(p);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Products";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String productName = rs.getString(2);
                double price = rs.getDouble(5);
                Product p = new Product();
                p.setProductID(id);
                p.setUnitPrice(price);
                p.setProductName(productName);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Category> getAllCate() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            String sql = "select * from Categories";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String cateName = rs.getString(2);
                Category c = new Category(id, cateName);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getCount(int cateID, int size) {
        String sql = "select COUNT(*) from Products\n"
                + "where CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cateID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                int endPage = count / size;
                if (count % size != 0) {
                    endPage++;
                }
                return endPage;
            }

        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Product> getProductPage(int cateID, int index, int size) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select p.*,c.CategoryName from Products as p\n"
                    + "join Categories as c\n"
                    + "on c.CategoryID = p.CategoryID\n"
                    + "where c.CategoryID = ?\n"
                    + "order by ProductID\n"
                    + "offset ? rows\n"
                    + "fetch first ? row only";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cateID);
            ps.setInt(2, (index - 1) * size);
            ps.setInt(3, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt(1);
                String name = rs.getString(2);
                int cid = rs.getInt(3);
                String quan = rs.getString(4);
                double price = rs.getDouble(5);
                int unitOnStocks = rs.getInt(6);
                String cateName = rs.getString(10);
                Product p = new Product();
                Category c = new Category();
                c.setCategoryName(cateName);
                c.setCategoryID(cid);
                p.setProductID(pid);
                p.setCategory(c);
                p.setProductName(name);
                p.setQuantityPerUnit(quan);
                p.setUnitPrice(price);
                p.setUnitsInStock(unitOnStocks);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getCountAllProduct(int size) {
        try {
            String sql = "select count(*) from Products";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int page = total / size;
                if (total % size != 0) {
                    page++;
                }
                return page;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ArrayList<Product> getPage(int index, int size) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select p.*,c.CategoryName from Products as p\n"
                    + "join Categories as c\n"
                    + "on p.CategoryID = c.CategoryID\n"
                    + "order by p.ProductID\n"
                    + "offset ? rows\n"
                    + "fetch first ? row only";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * size);
            ps.setInt(2, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String productName = rs.getString(2);
                int cateID = rs.getInt(3);
                String quantity = rs.getString(4);
                double price = rs.getDouble(5);
                int unitOnStocks = rs.getInt(6);
                boolean isDis = rs.getBoolean(9);
                String cateName = rs.getString(10);
                Category c = new Category();
                c.setCategoryID(cateID);
                c.setCategoryName(cateName);
                Product p = new Product();
                p.setProductID(id);
                p.setQuantityPerUnit(quantity);
                p.setUnitsInStock(unitOnStocks);
                p.setDiscontinued(isDis);
                p.setUnitPrice(price);
                p.setProductName(productName);
                p.setCategory(c);
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Product> getSearchProduct(String txt, int id) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Products\n"
                    + "where ProductName like ? and CategoryID = ?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(2, id);
            ps.setString(1, "%" + txt + "%");
    
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt(1);
                String productName = rs.getString(2);
                double price = rs.getDouble(5);
                Product p = new Product();
                p.setProductID(pid);
                p.setUnitPrice(price);
                p.setProductName(productName);
                if(p.getProductName().contains(txt)){
                     list.add(p);
                }               
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductByID(int id) {
        String sql = "select p.*,c.CategoryName from Products as p\n"
                + "join Categories as c\n"
                + "on c.CategoryID = p.CategoryID\n"
                + "where p.ProductID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt(1);
                String name = rs.getString(2);
                int cid = rs.getInt(3);
                String quan = rs.getString(4);
                double price = rs.getDouble(5);
                int unitOnStocks = rs.getInt(6);
                boolean isDis = rs.getBoolean(9);
                String cateName = rs.getString(10);
                Product p = new Product();
                Category c = new Category();
                c.setCategoryName(cateName);
                c.setCategoryID(cid);
                p.setProductID(pid);
                p.setDiscontinued(isDis);
                p.setCategory(c);
                p.setProductName(name);
                p.setQuantityPerUnit(quan);
                p.setUnitPrice(price);
                p.setUnitsInStock(unitOnStocks);
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void createPro(Product p) {
        String sql = "insert into Products(ProductName,CategoryID,QuantityPerUnit,UnitPrice,UnitsInStock,Discontinued) values(\n"
                + "?,?,?,?,?,?\n"
                + ")";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategory().getCategoryID());
            ps.setString(3, p.getQuantityPerUnit());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getUnitsInStock());
            ps.setBoolean(6, p.isDiscontinued());
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void UpdateProduct(Product p) {
        try {
            String sql = "update Products  set \n"
                    + "[ProductName] = ?,\n"
                    + "CategoryID = ?,\n"
                    + "QuantityPerUnit = ?,\n"
                    + "UnitPrice = ?,\n"
                    + "UnitsInStock = ?,\n"
                    + "Discontinued = ?\n"
                    + "where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategory().getCategoryID());
            ps.setString(3, p.getQuantityPerUnit());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getUnitsOnOrder());
            ps.setBoolean(6, p.isDiscontinued());
            ps.setInt(7, p.getProductID());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteByID(int id) {
        try {
            String sql = "delete from Products \n"
                    + "where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public ArrayList<Product> getProductBytxt(String txt, int index, int size) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String sql = "select p.*,c.CategoryName from Products as p\n"
                    + "join Categories as c\n"
                    + "on c.CategoryID = p.CategoryID\n"
                    + "where p.ProductName like ?\n"
                    + "order by p.ProductID\n"
                    + "offset ? rows\n"
                    + "fetch first ? row only";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, (index - 1) * size);
            ps.setInt(3, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String productName = rs.getString(2);
                int cateID = rs.getInt(3);
                String quantity = rs.getString(4);
                double price = rs.getDouble(5);
                int unitOnStocks = rs.getInt(6);
                boolean isDis = rs.getBoolean(9);
                String cateName = rs.getString(10);
                Category c = new Category();
                c.setCategoryID(cateID);
                c.setCategoryName(cateName);
                Product p = new Product();
                p.setProductID(id);
                p.setQuantityPerUnit(quantity);
                p.setUnitsInStock(unitOnStocks);
                p.setDiscontinued(isDis);
                p.setUnitPrice(price);
                p.setProductName(productName);
                p.setCategory(c);
                list.add(p);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public int getCountProductTxt(String txt, int size) {
        String sql = "select count(*) from Products\n"
                + "where ProductName like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
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

}
