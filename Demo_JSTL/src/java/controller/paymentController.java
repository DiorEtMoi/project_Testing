/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.Account;
import dal.Customer;
import dal.Email;
import dal.Employee;
import dal.Items;
import dal.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.AccountDAO;
import model.Manager;
import model.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class paymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("cart.jsp");
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Email    

        String email = "";

        Date date = Date.valueOf(LocalDate.now());
        HttpSession session = req.getSession();
        Manager m = new Manager();
        AccountDAO a = new AccountDAO();
        Customer c = new Customer();
        OrderDAO order = new OrderDAO();
        ArrayList<Customer> listc = a.getAllCustomer();
        if (session.getAttribute("AccSession") == null) {
            String cusID = a.RandomStr(5);
            String companyName = req.getParameter("companyName");
            String contactName = req.getParameter("contactName");
            String contactTitle = req.getParameter("contactTitle");
            String address = req.getParameter("address");
            if (m.SearchCustByID(listc, cusID) == null) {
                c = new Customer(cusID, companyName, contactName, contactTitle, address, date);
                a.createCustomer(c);
            } else {
                resp.getWriter().print("CustomerID da ton tai");
            }

        } else {
            Account acc = (Account) session.getAttribute("AccSession");
            c = new AccountDAO().getCustomer(acc.getCustomerID());
            //Email
            email = acc.getEmail();
        }
        Date required = Date.valueOf(req.getParameter("txtDate"));
        Double total = Double.parseDouble(req.getParameter("txtTotal"));
        Employee e = new Employee(order.getRandom(1, 9));
        ArrayList<Items> list = (ArrayList<Items>) session.getAttribute("cart");
        Order o = new Order();
        o.setItems(list);
        o.setRequiredDate(required);
        o.setCustomerID(c.getCustomerID());
        o.setEmployeeID(e.getEmployeeID());
        o.setOrderDate(date);
        o.setFreight(total);
        order.newOrder(o);
        Order orderTOP1 = order.getTop1Order(o.getCustomerID());
        String content = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"utf-8\" />\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n"
                + "\n"
                + "    <title>A simple, clean, and responsive HTML invoice template</title>\n"
                + "\n"
                + "    <!-- Favicon -->\n"
                + "    <link rel=\"icon\" href=\"./images/favicon.png\" type=\"image/x-icon\" />\n"
                + "\n"
                + "    <!-- Invoice styling -->\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n"
                + "            text-align: center;\n"
                + "            color: #777;\n"
                + "        }\n"
                + "\n"
                + "        body h1 {\n"
                + "            font-weight: 300;\n"
                + "            margin-bottom: 0px;\n"
                + "            padding-bottom: 0px;\n"
                + "            color: #000;\n"
                + "        }\n"
                + "\n"
                + "        body h3 {\n"
                + "            font-weight: 300;\n"
                + "            margin-top: 10px;\n"
                + "            margin-bottom: 20px;\n"
                + "            font-style: italic;\n"
                + "            color: #555;\n"
                + "        }\n"
                + "\n"
                + "        body a {\n"
                + "            color: #06f;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box {\n"
                + "            max-width: 800px;\n"
                + "            margin: auto;\n"
                + "            padding: 30px;\n"
                + "            border: 1px solid #eee;\n"
                + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);\n"
                + "            font-size: 16px;\n"
                + "            line-height: 24px;\n"
                + "            font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n"
                + "            color: #555;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table {\n"
                + "            width: 100%;\n"
                + "            line-height: inherit;\n"
                + "            text-align: left;\n"
                + "            border-collapse: collapse;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table td {\n"
                + "            padding: 5px;\n"
                + "            vertical-align: top;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table tr td:nth-child(2) {\n"
                + "            text-align: right;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table tr.top table td {\n"
                + "            padding-bottom: 20px;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table tr.top table td.title {\n"
                + "            font-size: 45px;\n"
                + "            line-height: 45px;\n"
                + "            color: #333;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table tr.information table td {\n"
                + "            padding-bottom: 40px;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table tr.heading td {\n"
                + "            background: #eee;\n"
                + "            border-bottom: 1px solid #ddd;\n"
                + "            font-weight: bold;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table tr.details td {\n"
                + "            padding-bottom: 20px;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table tr.item td {\n"
                + "            border-bottom: 1px solid #eee;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table tr.item.last td {\n"
                + "            border-bottom: none;\n"
                + "        }\n"
                + "\n"
                + "        .invoice-box table tr.total td:nth-child(2) {\n"
                + "            border-top: 2px solid #eee;\n"
                + "            font-weight: bold;\n"
                + "        }\n"
                + "\n"
                + "        @media only screen and (max-width: 600px) {\n"
                + "            .invoice-box table tr.top table td {\n"
                + "                width: 100%;\n"
                + "                display: block;\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "\n"
                + "            .invoice-box table tr.information table td {\n"
                + "                width: 100%;\n"
                + "                display: block;\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "\n"
                + "\n"
                + "    <div class=\"invoice-box\">\n"
                + "        <table>\n"
                + "            <tr class=\"top\">\n"
                + "                <td colspan=\"2\">\n"
                + "                    <table>\n"
                + "                        <tr>\n"
                + "                            <td>\n"
                + "                                Order #: " + orderTOP1.getOrderID() + "<br />\n"
                + "                                Created: " + date + "<br />\n"
                + "                                Due: " + orderTOP1.getRequiredDate() + "\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "\n"
                + "            <tr class=\"heading\">\n"
                + "                <td>Item</td>\n"
                + "\n"
                + "                <td>Price</td>\n"
                + "            </tr>";
        for (Items items : list) {
            order.insertOrderDetails(orderTOP1.getOrderID(), items);
            content += "<tr class=\"item\">\n"
                    + "                <td>" + items.getProduct().getProductName() + "</td>\n"
                    + "\n"
                    + "                <td>" + items.getProduct().getUnitPrice() + "</td>\n"
                    + "            </tr>";
        }
        content += " <tr class=\"total\">\n"
                + "                <td></td>\n"
                + "\n"
                + "                <td>Total: $" + total + "</td>\n"
                + "            </tr>\n"
                + "        </table>\n"
                + "    </div>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        if (session.getAttribute("AccSession") != null) {
            try {
                Email.sendEmail("smtp.gmail.com", "587", "dinhhoan0019c@gmail.com", "sendMail", "sbbfiegozzibcnmo", email, "Your Order", content);

            } catch (UnsupportedEncodingException | MessagingException ex) {
                ex.printStackTrace();
            } 
        }
        session.removeAttribute("cart");
        resp.sendRedirect("home");
    }

}
