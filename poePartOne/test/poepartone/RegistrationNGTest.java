/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poepartone;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Student
 */
public class RegistrationNGTest {


    Registration reg;
    
    public RegistrationNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        reg = new Registration();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        reg = null;
    }

    // USERNAME TEST
    
    @Test
    public void testCheckUserName_Valid() {
        System.out.println("testCheckUserName_Valid");
        String UserName = "kyl_1";
        boolean expResult = true;
        boolean result = reg.checkUserName(UserName);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testCheckUserName_Invalid_NoUnderscore() {
        System.out.println("testCheckUserName_Invalid_NoUnderscore");
        String UserName = "kyle!!!!!!";
        boolean expResult = false;
        boolean result = reg.checkUserName(UserName);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testCheckUserName_Invalid_TooLong() {
        System.out.println("testCheckUserName_Invalid_TooLong");
        String UserName = "kyle_smith";
        boolean expResult = false;
        boolean result = reg.checkUserName(UserName);
        assertEquals(result, expResult);
    }
    
    // PASSWORD TEST
    
    @Test
    public void testCheckPasswordComplexity_Valid() {
        System.out.println("testCheckPasswordComplexity_Valid");
        String password = "Ch&sec@ke99!";
        boolean expResult = true;
        boolean result = reg.checkPasswordComplexity(password);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid_TooShort() {
        System.out.println("testCheckPasswordComplexity_Invalid_TooShort");
        String password = "Pass1!";
        boolean expResult = false;
        boolean result = reg.checkPasswordComplexity(password);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid_NoUppercase() {
        System.out.println("testCheckPasswordComplexity_Invalid_NoUppercase");
        String password = "password1!";
        boolean expResult = false;
        boolean result = reg.checkPasswordComplexity(password);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid_NoDigit() {
        System.out.println("testCheckPasswordComplexity_Invalid_NoDigit");
        String password = "Password!";
        boolean expResult = false;
        boolean result = reg.checkPasswordComplexity(password);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid_NoSpecial() {
        System.out.println("testCheckPasswordComplexity_Invalid_NoSpecial");
        String password = "Password1";
        boolean expResult = false;
        boolean result = reg.checkPasswordComplexity(password);
        assertEquals(result, expResult);
    }
    
    // CELLPHONE TEST
    
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        System.out.println("testCheckCellPhoneNumber_Valid");
        String cellphone = "+27838968976";
        boolean expResult = true;
        boolean result = reg.checkCellPhoneNumber(cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testCheckCellPhoneNumber_Invalid_NoCountryCode() {
        System.out.println("testCheckCellPhoneNumber_Invalid_NoCountryCode");
        String cellphone = "0838968976";
        boolean expResult = false;
        boolean result = reg.checkCellPhoneNumber(cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testCheckCellPhoneNumber_Invalid_TooShort() {
        System.out.println("testCheckCellPhoneNumber_Invalid_TooShort");
        String cellphone = "+27838";
        boolean expResult = false;
        boolean result = reg.checkCellPhoneNumber(cellphone);
        assertEquals(result, expResult);
    }
    
    //  REGISTER USER TEST
    
    @Test
    public void testRegisterUser_Success() {
        System.out.println("testRegisterUser_Success");
        String username = "kyl_1";
        String password = "Ch&sec@ke99!";
        String cellphone = "+27838968976";
        String expResult = "Registration was successful";
        String result = reg.registerUser(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testRegisterUser_InvalidUsername() {
        System.out.println("testRegisterUser_InvalidUsername");
        String username = "kyle!!!!!!";
        String password = "Ch&sec@ke99!";
        String cellphone = "+27838968976";
        String expResult = "Username is not correctly formatted; please ensure that your"
                + " username contains an underscore and is no more than five characters in length.";
        String result = reg.registerUser(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testRegisterUser_InvalidPassword() {
        System.out.println("testRegisterUser_InvalidPassword");
        String username = "kyl_1";
        String password = "password";
        String cellphone = "+27838968976";
        String expResult = "Password is not correctly formatted; please ensure that"
                + " the password contains at least eight characters, a capital letter,"
                + " a number, and a special character.";
        String result = reg.registerUser(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testRegisterUser_InvalidCellphone() {
        System.out.println("testRegisterUser_InvalidCellphone");
        String username = "kyl_1";
        String password = "Ch&sec@ke99!";
        String cellphone = "0838968976";
        String expResult = "Cell phone number incorrectly formatted "
                + "or does not contain international code.";
        String result = reg.registerUser(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    // REGISTER STATUS TEST
    
    @Test
    public void testRegisterStatus_AllValid() {
        System.out.println("testRegisterStatus_AllValid");
        String username = "kyl_1";
        String password = "Ch&sec@ke99!";
        String cellphone = "+27838968976";
        boolean expResult = true;
        boolean result = reg.registerStatus(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testRegisterStatus_InvalidUsername() {
        System.out.println("testRegisterStatus_InvalidUsername");
        String username = "kyle!!!!!!";
        String password = "Ch&sec@ke99!";
        String cellphone = "+27838968976";
        boolean expResult = false;
        boolean result = reg.registerStatus(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testRegisterStatus_InvalidPassword() {
        System.out.println("testRegisterStatus_InvalidPassword");
        String username = "kyl_1";
        String password = "password";
        String cellphone = "+27838968976";
        boolean expResult = false;
        boolean result = reg.registerStatus(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testRegisterStatus_InvalidCellphone() {
        System.out.println("testRegisterStatus_InvalidCellphone");
        String username = "kyl_1";
        String password = "Ch&sec@ke99!";
        String cellphone = "0838968976";
        boolean expResult = false;
        boolean result = reg.registerStatus(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    //LOGIN TEST
    
    @Test
    public void testLoginUser_Success() {
        System.out.println("testLoginUser_Success");
        reg.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        reg.setUserDetails("Kyle", "Smith");
        Login login = new Login(reg);
        boolean expResult = true;
        boolean result = login.loginUser("kyl_1", "Ch&sec@ke99!");
        assertEquals(result, expResult);
    }
    
    @Test
    public void testLoginUser_Failed_WrongPassword() {
        System.out.println("testLoginUser_Failed_WrongPassword");
        reg.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        Login login = new Login(reg);
        boolean expResult = false;
        boolean result = login.loginUser("kyl_1", "wrongpassword");
        assertEquals(result, expResult);
    }
    
    @Test
    public void testLoginUser_Failed_WrongUsername() {
        System.out.println("testLoginUser_Failed_WrongUsername");
        reg.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        Login login = new Login(reg);
        boolean expResult = false;
        boolean result = login.loginUser("wronguser", "Ch&sec@ke99!");
        assertEquals(result, expResult);
    }
    
    //LOGIN STATUS TEST
    
    @Test
    public void testReturnLoginStatus_Success() {
        System.out.println("testReturnLoginStatus_Success");
        reg.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        reg.setUserDetails("Kyle", "Smith");
        Login login = new Login(reg);
        String expResult = "Welcome Kyle, Smith it is great to see you again.";
        String result = login.returnLoginStatus("kyl_1", "Ch&sec@ke99!");
        assertEquals(result, expResult);
    }
    
    @Test
    public void testReturnLoginStatus_Failed() {
        System.out.println("testReturnLoginStatus_Failed");
        reg.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        Login login = new Login(reg);
        String expResult = "Username or password incorrect, please try again.";
        String result = login.returnLoginStatus("kyl_1", "wrongpassword");
        assertEquals(result, expResult);
    }
}
