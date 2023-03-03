/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.Customer;
import java.sql.Array;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Manager {
    public Customer SearchCustByID(ArrayList<Customer> list,String id){
        for (Customer customer : list) {
            if(customer.getCustomerID().equalsIgnoreCase(id)){
                return customer;
            }
        }
        return null;
    }
}
