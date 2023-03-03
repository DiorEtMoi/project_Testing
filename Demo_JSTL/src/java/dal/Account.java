/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author ADMIN
 */
public class Account {
    private int AcountId;
    private String Email;
    private String Password;
    private String CustomerID;
    private int EmployeeID;
    private int Role;

    public Account() {
    }

    public Account(int AcountId, String Email, String Password, String CustomerID, int EmployeeID, int Role) {
        this.AcountId = AcountId;
        this.Email = Email;
        this.Password = Password;
        this.CustomerID = CustomerID;
        this.EmployeeID = EmployeeID;
        this.Role = Role;
    }

    public int getAcountId() {
        return AcountId;
    }

    public void setAcountId(int AcountId) {
        this.AcountId = AcountId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "Account{" + "AcountId=" + AcountId + ", Email=" + Email + ", Password=" + Password + ", CustomerID=" + CustomerID + ", EmployeeID=" + EmployeeID + ", Role=" + Role + '}';
    }
    
    
    
    
}
