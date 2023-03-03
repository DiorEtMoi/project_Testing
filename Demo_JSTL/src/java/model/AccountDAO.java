/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.Account;
import dal.DBContext;
import dal.Account;
import dal.Customer;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class AccountDAO extends DBContext {

    public Account getAccount(String email, String pass) {
        Account a = null;
        try {
            String sql = "select * from Accounts\n"
                    + "where Email = ? and Password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");
                a = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
            }
        } catch (Exception e) {
        }
        return a;
    }

    public int createAccount(Customer cust, Account acc) {
        int result1 = 0;
        int result2 = 0;
        try {
            String sql1 = "insert into Customers(CustomerID, CompanyName,ContactName,ContactTitle,Address,Date) values(?,?,?,?,?,?)";
            String sql2 = "insert into Accounts(Email,Password,CustomerID,Role) values(?,?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps1.setString(1, cust.getCustomerID());
            ps1.setString(2, cust.getCompanyName());
            ps1.setString(3, cust.getContactName());
            ps1.setString(4, cust.getContactTitle());
            ps1.setString(5, cust.getAddress());
            ps2.setString(1, acc.getEmail());
            ps2.setString(2, acc.getPassword());
            ps2.setString(3, acc.getCustomerID());
            ps2.setInt(4, 2);
            ps1.setDate(6, cust.getDate());
            result1 = ps1.executeUpdate();
            result2 = ps2.executeUpdate();

        } catch (Exception e) {
        }
        if (result1 > 0 && result2 > 0) {
            return 1;
        } else {
            return 0;
        }

    }

    // function to generate a random string of length n
    public String RandomStr(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public Customer getCustomer(String cusID) {
        try {
            String sql = "select c.* from Accounts as a\n"
                    + "join Customers as c\n"
                    + "on c.CustomerID = a.CustomerID\n"
                    + "where a.CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cusID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String customerId = rs.getString(1);
                String companyName = rs.getString(2);
                String contactName = rs.getString(3);
                String contactTitle = rs.getString(4);
                String address = rs.getString(5);
                Customer c = new Customer(customerId, companyName, contactName, contactTitle, address);
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> list = new ArrayList<>();
        try {
            String sql = "select * from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cusid = rs.getString(1);
                Customer c = new Customer();
                c.setCustomerID(cusid);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void createCustomer(Customer c) {
        try {
            String sql = "insert into Customers(CustomerID, CompanyName,ContactName,ContactTitle,Address,Date) values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCustomerID());
            ps.setString(2, c.getCompanyName());
            ps.setString(3, c.getContactName());
            ps.setString(4, c.getContactTitle());
            ps.setString(5, c.getAddress());
            ps.setDate(6, c.getDate());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateCustomer(Customer c) {
        try {
            String sql = "update Customers \n"
                    + "set CompanyName = ?,\n"
                    + "	ContactName = ?,\n"
                    + "	ContactTitle = ?,\n"
                    + "	[Address] = ?\n"
                    + "where CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCompanyName());
            ps.setString(2, c.getContactName());
            ps.setString(3, c.getContactTitle());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getCustomerID());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getAllCustomerInMonths() {
        String sql = "select count(*) from Customers \n"
                + "where MONTH(Date) = MONTH(GETDATE())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getTotalCustomer() {
        String sql = "select count(*) from Customers \n";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getCountGuest() {
        String sql = "select count(*) from Customers as c\n"
                + "left join Accounts as a\n"
                + "on c.CustomerID = a.CustomerID\n"
                + "where a.AccountID is null";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                return total;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new AccountDAO().getCustomer("ERNSH"));
    }

    public void resetPass(String email, String pass) {
        String sql = "update Accounts set [Password] = ?\n"
                + "where Email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
