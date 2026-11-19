/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poepartone;

public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellphone) {
        // Reference: GeeksforGeeks (2024) 'How to validate a phone number using regular expression'
        // This regex ensures the number starts with +27 and is followed by exactly 9 digits
        return cellphone != null && cellphone.matches("^\\+27[0-9]{9}$");
    }

    public String registerUser(String username, String password, String cellphone) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure"
                    + " that your username contains an underscore and"
                    + " is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at "
                    + "least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellphone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellphone;
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    public boolean loginUser(String username, String password) {
        return this.username != null && this.username.equals(username) 
            && this.password != null && this.password.equals(password);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public void setUserDetails(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}