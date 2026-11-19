/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;

/**
 *
 * @author Student
 */


import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginNGTest {
    
    Login login;

    @BeforeMethod
    public void setUp() {
        login = new Login();
    }

    @Test
    public void testCheckUserName_Success() {
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    @Test
    public void testCheckUserName_Failure() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_Success() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_Failure() {
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_Success() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_Failure() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    
    @Test
    public void testRegisterUser_Success() {
        String expected = "Username successfully captured.\n"
                        + "Password successfully captured.\n"
                        + "Cell phone number successfully added.";
        String actual = login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertEquals(actual, expected);
    }
    
    @Test
    public void testLoginUser_Success() {
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        login.setUserDetails("Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
    }
    
    @Test
    public void testLoginUser_Failure() {
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }
    
    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976");
        login.setUserDetails("Kyle", "Smith");
        String expected = "Welcome Kyle, Smith it is great to see you again.";
        assertEquals(login.returnLoginStatus(true), expected);
    }
}