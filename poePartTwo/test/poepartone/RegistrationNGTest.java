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

    // ============ USERNAME TESTS ============
    
    @Test
    public void testCheckUserName_Valid() {
        System.out.println("testCheckUserName_Valid");
        assertEquals(reg.checkUserName("kyl_1"), true);
    }
    
    @Test
    public void testCheckUserName_Invalid_NoUnderscore() {
        System.out.println("testCheckUserName_Invalid_NoUnderscore");
        assertEquals(reg.checkUserName("kyle!!!!!!"), false);
    }
    
    @Test
    public void testCheckUserName_Invalid_TooLong() {
        System.out.println("testCheckUserName_Invalid_TooLong");
        assertEquals(reg.checkUserName("kyle_smith"), false);
    }
    
    // ============ PASSWORD TESTS ============
    
    @Test
    public void testCheckPasswordComplexity_Valid() {
        System.out.println("testCheckPasswordComplexity_Valid");
        assertEquals(reg.checkPasswordComplexity("Ch&sec@ke99!"), true);
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid_TooShort() {
        System.out.println("testCheckPasswordComplexity_Invalid_TooShort");
        assertEquals(reg.checkPasswordComplexity("Pass1!"), false);
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid_NoUppercase() {
        System.out.println("testCheckPasswordComplexity_Invalid_NoUppercase");
        assertEquals(reg.checkPasswordComplexity("password1!"), false);
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid_NoDigit() {
        System.out.println("testCheckPasswordComplexity_Invalid_NoDigit");
        assertEquals(reg.checkPasswordComplexity("Password!"), false);
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid_NoSpecial() {
        System.out.println("testCheckPasswordComplexity_Invalid_NoSpecial");
        assertEquals(reg.checkPasswordComplexity("Password1"), false);
    }
    
    // ============ CELLPHONE TESTS ============
    
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        System.out.println("testCheckCellPhoneNumber_Valid");
        assertEquals(reg.checkCellPhoneNumber("+27838968976"), true);
    }
    
    @Test
    public void testCheckCellPhoneNumber_Invalid_NoCountryCode() {
        System.out.println("testCheckCellPhoneNumber_Invalid_NoCountryCode");
        assertEquals(reg.checkCellPhoneNumber("0838968976"), false);
    }
    
    @Test
    public void testCheckCellPhoneNumber_Invalid_TooShort() {
        System.out.println("testCheckCellPhoneNumber_Invalid_TooShort");
        assertEquals(reg.checkCellPhoneNumber("+27838"), false);
    }
    
    // ============ REGISTER USER TESTS ============
    // NOTE: registerUser returns ALL messages for each field (success or failure)
    
    @Test
    public void testRegisterUser_Success() {
        System.out.println("testRegisterUser_Success");
        String username = "kyl_1";
        String password = "Ch&sec@ke99!";
        String cellphone = "+27838968976";
        String expResult = "Username successfully captured.\n"
                         + "Password successfully captured.\n"
                         + "Cell phone number successfully added.";
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
                + " username contains an underscore and is no more than five characters in length.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.";
        String result = reg.registerUser(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testRegisterUser_InvalidPassword() {
        System.out.println("testRegisterUser_InvalidPassword");
        String username = "kyl_1";
        String password = "password";
        String cellphone = "+27838968976";
        String expResult = "Username successfully captured.\n"
                + "Password is not correctly formatted; please ensure that"
                + " the password contains at least eight characters, a capital letter,"
                + " a number, and a special character.\n"
                + "Cell phone number successfully added.";
        String result = reg.registerUser(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testRegisterUser_InvalidCellphone() {
        System.out.println("testRegisterUser_InvalidCellphone");
        String username = "kyl_1";
        String password = "Ch&sec@ke99!";
        String cellphone = "0838968976";
        String expResult = "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number incorrectly formatted or does not contain international code.";
        String result = reg.registerUser(username, password, cellphone);
        assertEquals(result, expResult);
    }
    
    // ============ REGISTER STATUS TESTS ============
    
    @Test
    public void testRegisterStatus_AllValid() {
        System.out.println("testRegisterStatus_AllValid");
        assertEquals(reg.registerStatus("kyl_1", "Ch&sec@ke99!", "+27838968976"), true);
    }
    
    @Test
    public void testRegisterStatus_InvalidUsername() {
        System.out.println("testRegisterStatus_InvalidUsername");
        assertEquals(reg.registerStatus("kyle!!!!!!", "Ch&sec@ke99!", "+27838968976"), false);
    }
    
    @Test
    public void testRegisterStatus_InvalidPassword() {
        System.out.println("testRegisterStatus_InvalidPassword");
        assertEquals(reg.registerStatus("kyl_1", "password", "+27838968976"), false);
    }
    
    @Test
    public void testRegisterStatus_InvalidCellphone() {
        System.out.println("testRegisterStatus_InvalidCellphone");
        assertEquals(reg.registerStatus("kyl_1", "Ch&sec@ke99!", "0838968976"), false);
    }
    
    // ============ LOGIN TESTS ============
    
    @Test
    public void testLoginUser_Success() {
        System.out.println("testLoginUser_Success");
        reg.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        reg.setUserDetails("Kyle", "Smith");
        Login login = new Login(reg);
        assertEquals(login.loginUser("kyl_1", "Ch&sec@ke99!"), true);
    }
    
    @Test
    public void testLoginUser_Failed_WrongPassword() {
        System.out.println("testLoginUser_Failed_WrongPassword");
        reg.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        Login login = new Login(reg);
        assertEquals(login.loginUser("kyl_1", "wrongpassword"), false);
    }
    
    @Test
    public void testLoginUser_Failed_WrongUsername() {
        System.out.println("testLoginUser_Failed_WrongUsername");
        reg.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        Login login = new Login(reg);
        assertEquals(login.loginUser("wronguser", "Ch&sec@ke99!"), false);
    }
    
    // ============ LOGIN STATUS TESTS ============
    
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