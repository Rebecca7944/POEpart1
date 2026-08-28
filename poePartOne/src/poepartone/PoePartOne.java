/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepartone;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class PoePartOne { // start of class

    public static void main(String[] args) { // start of main
        
        Scanner input = new Scanner(System.in);
        Registration reg = new Registration();
        
        System.out.println("========================================");
        System.out.println("Welcome to QuickChat Registration System");
        System.out.println("========================================\n");
        
        //  Registration
        System.out.println("--- REGISTRATION ---");
        
        System.out.print("Enter username: ");
        String username = input.nextLine();
        
        System.out.print("Enter password: ");
        String password = input.nextLine();
        
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        
        System.out.print("Enter cellphone number (+27XXXXXXXXX): ");
        String cellphone = input.nextLine();
        
        // The user whos gonna register ,get full result message
        String result = reg.registerUser(username, password, cellphone);
        System.out.println("\n" + result);
        
        // It checks if all fields were valid
        boolean isUsernameValid = reg.checkUserName(username);
        boolean isPasswordValid = reg.checkPasswordComplexity(password);
        boolean isCellphoneValid = reg.checkCellPhoneNumber(cellphone);
        
        // If all valid then proceed to login
        if(isUsernameValid && isPasswordValid && isCellphoneValid) {
            reg.setUserDetails(firstName, lastName);
            
            //  Login
            System.out.println("\n--- LOGIN ---");
            
            Login login = new Login(reg);
            int attempts = 0;
            boolean loggedIn = false;
            
            while(attempts < 3 && !loggedIn) {
                System.out.print("Enter username: ");
                String loginUsername = input.nextLine();
                
                System.out.print("Enter password: ");
                String loginPassword = input.nextLine();
                
                String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
                System.out.println(loginStatus);
                
                if(login.loginUser(loginUsername, loginPassword)) {
                    loggedIn = true;
                    System.out.println("\n========================================");
                    System.out.println("Login successful! Welcome to QuickChat!");
                    System.out.println("========================================");
                } else {
                    attempts++;
                    if(attempts < 3) {
                        System.out.println("Attempts remaining: " + (3 - attempts) + "\n");
                    }
                }
            }
            
            if(!loggedIn) {
                System.out.println("\nMaximum login attempts exceeded.");
            }
        } else {
            System.out.println("\nRegistration failed. Please correct the errors and try again.");
        }
        
        input.close();
        System.out.println("\nThank you for using QuickChat.");
    } // end of main
} // end of class
