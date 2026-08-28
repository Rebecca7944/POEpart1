/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;

/**
 *
 * @author Student
 */

public class Registration { // start of class
    
    String userName;
    String passWord;
    String cellPhone;
    String firstName;
    String lastName;
    
    public boolean checkUserName(String UserName){// start of user name method
        if(UserName != null && UserName.contains("_") && UserName.length() <= 5){
            return true;
        } else {
            return false;
        }
    } //end of user name method
    
    public boolean checkPasswordComplexity(String password) { // Start of password method
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        String specialChars = "!@#$%^&*()_+-=[]{}|;:',.<>?/~`";
        
        for(int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if(Character.isUpperCase(c)) {
                hasUpper = true;
            } else if(Character.isDigit(c)) {
                hasDigit = true;
            } else if(specialChars.contains(String.valueOf(c))) {
                hasSpecial = true;
            }
        }
        
        return hasUpper && hasDigit && hasSpecial;
    } // end of the password method  
 
    public boolean checkCellPhoneNumber(String cellphone) { // start of cellphone method
        if (cellphone == null) {
            return false;
        }
       
        return cellphone.matches("^\\+27[0-9]{9}$");
    }// end of cellphone method      
    
    public String registerUser(String username, String password, String cellphone){
        // Checks all validations
        boolean isUsernameValid = checkUserName(username);
        boolean isPasswordValid = checkPasswordComplexity(password);
        boolean isCellphoneValid = checkCellPhoneNumber(cellphone);
        
       
        StringBuilder resultMessage = new StringBuilder();
        
        // Check username
        if(isUsernameValid) {
            resultMessage.append("Username successfully captured.\n");
        } else {
            resultMessage.append("Username is not correctly formatted; please "
                    + "ensure that your username contains an underscore and is"
                    + " no more than five characters in length.\n");
        }
        
        // Check password
        if(isPasswordValid) {
            resultMessage.append("Password successfully captured.\n");
        } else {
            resultMessage.append("Password is not correctly formatted; "
                    + "please ensure that the password contains at least eight"
                    + " characters, a capital letter, a number, and a special character.\n");
        }
        
        // Check cellphone
        if(isCellphoneValid) {
            resultMessage.append("Cell phone number successfully added.");
        } else {
            resultMessage.append("Cell phone number incorrectly formatted "
                    + "or does not contain international code.");
        }
        
        // If all are valid then it can processed to register the user
        if(isUsernameValid && isPasswordValid && isCellphoneValid) {
            this.userName = username;
            this.passWord = password;
            this.cellPhone = cellphone;
            return resultMessage.toString().trim();
        }
        
        return resultMessage.toString().trim();
    }
    
    public boolean registerStatus(String username, String password, String cellphone){
        return checkUserName(username) && checkPasswordComplexity(password) && checkCellPhoneNumber(cellphone);
    }
    
    //  This method to store first and last name
    public void setUserDetails(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
} // end of class


