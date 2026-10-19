/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepartone;

import java.util.Scanner;

public class PoePartTwo {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Registration reg = new Registration();

        System.out.println("========================================");
        System.out.println("Welcome to QuickChat Registration System");
        System.out.println("========================================\n");

        // ================= REGISTRATION =================
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

      
        boolean usernameOK = reg.checkUserName(username);
        boolean passwordOK = reg.checkPasswordComplexity(password);
        boolean cellphoneOK = reg.checkCellPhoneNumber(cellphone);

        System.out.println("\n===== FEEDBACK =====");

        if (usernameOK) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your "
                    + "username contains an underscore and is no more than five characters in length.");
        }

        if (passwordOK) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the "
                    + "password contains at least eight characters, a capital letter, a number, "
                    + "and a special character.");
        }

        if (cellphoneOK) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain "
                    + "international code.");
        }

   
        if (!usernameOK || !passwordOK || !cellphoneOK) {
            System.out.println("\nPlease re-enter the fields marked as incorrect:\n");
        }

        // Retry username if wrong
        while (!usernameOK) {
            System.out.print("Enter username: ");
            username = input.nextLine();
            usernameOK = reg.checkUserName(username);
            if (usernameOK) {
                System.out.println("Username successfully captured.");
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your "
                        + "username contains an underscore and is no more than five characters in length.");
            }
        }

        // Retry password if wrong
        while (!passwordOK) {
            System.out.print("Enter password: ");
            password = input.nextLine();
            passwordOK = reg.checkPasswordComplexity(password);
            if (passwordOK) {
                System.out.println("Password successfully captured.");
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the "
                        + "password contains at least eight characters, a capital letter, a number, "
                        + "and a special character.");
            }
        }

        // Retry cellphone if wrong
        while (!cellphoneOK) {
            System.out.print("Enter cellphone number (+27XXXXXXXXX): ");
            cellphone = input.nextLine();
            cellphoneOK = reg.checkCellPhoneNumber(cellphone);
            if (cellphoneOK) {
                System.out.println("Cell phone number successfully added.");
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain "
                        + "international code.");
            }
        }

       
        reg.registerUser(username, password, cellphone);
        reg.setUserDetails(firstName, lastName);

        System.out.println("\nAll details captured! Moving to login...");

      
        System.out.println("\n--- LOGIN ---");

        Login login = new Login(reg);
        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < 3 && !loggedIn) {
            System.out.print("Enter username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = input.nextLine();

            String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginStatus);

            if (login.loginUser(loginUsername, loginPassword)) {
                loggedIn = true;
                System.out.println("\n========================================");
                System.out.println("Welcome to QuickChat.");
                System.out.println("========================================");

                Message msg = new Message();
                int option = 0;

                do {
                    System.out.println("\n===== QuickChat Menu =====");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Choose an option: ");

                    option = input.nextInt();
                    input.nextLine();

                    switch (option) {
                        case 1 -> msg.sendingMessages();
                        case 2 -> System.out.println("Coming Soon.");
                        case 3 -> System.out.println("Goodbye!");
                        default -> System.out.println("Invalid option. Please choose 1, 2 or 3.");
                    }
                } while (option != 3);

            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("Attempts remaining: " + (3 - attempts) + "\n");
                }
            }
        }

        if (!loggedIn) {
            System.out.println("\nMaximum login attempts exceeded.");
        }

        input.close();
        System.out.println("\nThank you for using QuickChat.");
    }
}