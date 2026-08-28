/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;

/**
 *
 * @author Student
 */
public class Login extends Registration {
    
    Registration registration;
    
    public Login(Registration registration) {
        this.registration = registration;
    }
    
    public boolean loginUser(String username, String password) {
        if(username.equals(registration.userName) && password.equals(registration.passWord)) {
            return true;
        } else {
            return false;
        }
    }
    
    public String returnLoginStatus(String username, String password) {
        if(loginUser(username, password)) {
            return "Welcome " + registration.getFirstName() + ", " + registration.getLastName() +
                    " it is great to see you again.";
        } else {
            return "Username or password incorrect . .The login has failed, please try again.";
        }
    }
} // end of class
    
