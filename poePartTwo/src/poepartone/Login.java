/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;

import java.util.Scanner;

public class Login extends Registration {
    
    Registration registration;
    
    public Login(Registration registration) {
        this.registration = registration;
    }
    
    //Verifies login details match the registered user.
     
    public boolean loginUser(String username, String password) {
        if(registration.userName != null && username.equals(registration.userName) 
                && registration.passWord != null && password.equals(registration.passWord)) {
            return true;
        } else {
            return false;
        }
    }
    
    
     // Returns login status message 
    public String returnLoginStatus(String username, String password) {
        if(loginUser(username, password)) {
            return "Welcome " + registration.getFirstName() + ", " 
                    + registration.getLastName() + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
} // end of class