/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;

/**
 *
 * @author Student
 */
public class User {
 

 //User class to store user registration details
 // This class holds all user information for the registration system

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String cellphoneNumber;
    
    // Default constructor , this constructor has no parameters
    public User() {
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.cellphoneNumber = "";
    }
    
    // Parameterized constructor , the constructor take he parameter
    public User(String username, String password, String firstName, 
            String lastName, String cellphoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellphoneNumber = cellphoneNumber;
    }
    
    // Getters and Setters,they allow you to access and modify private variables in a class.
    public String getUsername() { 
        return username; 
    }
    
    public void setUsername(String username) { 
        this.username = username; 
    }
    
    public String getPassword() { 
        return password; 
    }
    
    public void setPassword(String password) { 
        this.password = password; 
    }
    
    public String getFirstName() { 
        return firstName; 
    }
    
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }
    
    public String getLastName() { 
        return lastName; 
    }
    
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }
    
    public String getCellphoneNumber() { 
        return cellphoneNumber; 
    }
    
    public void setCellphoneNumber(String cellphoneNumber) { 
        this.cellphoneNumber = cellphoneNumber; 
    }
    
    @Override
    public String toString() {
        return "User{" + "username=" + username + ", firstName=" + firstName + 
               ", lastName=" + lastName + ", cellphoneNumber=" + cellphoneNumber + '}';
    }
}

