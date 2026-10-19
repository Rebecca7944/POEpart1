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


public class MessageNGTest {

    Message msg;

    public MessageNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        msg = new Message();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        msg = null;
    }

   

    @Test
    public void testCheckMessageLength_Success() {
        System.out.println("testCheckMessageLength_Success");
        String message = "Hi Mike, can you join us for dinner tonight?";
        String expResult = "Message ready to send.";
        String result = msg.checkMessageLength(message);
        assertEquals(result, expResult);
    }

    @Test
    public void testCheckMessageLength_Failure() {
        System.out.println("testCheckMessageLength_Failure");
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMsg.append("a");
        }
        String message = longMsg.toString();
        String expResult = "Message exceeds 250 characters by 10; please reduce the size.";
        String result = msg.checkMessageLength(message);
        assertEquals(result, expResult);
    }

   

    @Test
    public void testCheckRecipientCell_Valid_Plus27() {
        System.out.println("testCheckRecipientCell_Valid_Plus27");
        String expResult = "Cell phone number successfully captured.";
        String result = msg.checkRecipientCell("+27718693002");
        assertEquals(result, expResult);
    }

    @Test
    public void testCheckRecipientCell_Valid_Zero() {
        System.out.println("testCheckRecipientCell_Valid_Zero");
        String expResult = "Cell phone number successfully captured.";
        String result = msg.checkRecipientCell("08575975889");
        assertEquals(result, expResult);
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        System.out.println("testCheckRecipientCell_Invalid");
        String expResult = "Cell phone number is incorrectly formatted or does not contain "
                         + "an international code. Please correct the number and try again.";
        String result = msg.checkRecipientCell("08966553");
        assertEquals(result, expResult);
    }

    

    @Test
    public void testCreateMessageHash_TestCase1() {
        System.out.println("testCreateMessageHash_TestCase1");
        String result = msg.createMessageHash("0012345678", 0,
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals(result, "00:0:HITONIGHT");
    }

    @Test
    public void testCreateMessageHash_Loop() {
        System.out.println("testCreateMessageHash_Loop");

        String[] ids = {"0012345678", "0098765432", "0045678912"};
        int[] numbers = {1, 2, 3};
        String[] texts = {
            "Hi Mike, can you join us for dinner tonight?",
            "Hi Keegan, did you receive the payment?",
            "Ok, I am leaving without you."
        };
        String[] expected = {
            "00:1:HITONIGHT",
            "00:2:HIPAYMENT",
            "00:3:OKYOU"
        };

        for (int i = 0; i < ids.length; i++) {
            String result = msg.createMessageHash(ids[i], numbers[i], texts[i]);
            assertEquals(result, expected[i], "Hash mismatch for message " + i);
        }
    }

    

    @Test
    public void testCheckMessageID_Valid() {
        System.out.println("testCheckMessageID_Valid");
        assertEquals(msg.checkMessageID("1234567890"), true);
    }

    @Test
    public void testCheckMessageID_Invalid() {
        System.out.println("testCheckMessageID_Invalid");
        assertEquals(msg.checkMessageID("12345678901"), false);
    }

    @Test
    public void testGenerateMessageID_Length() {
        System.out.println("testGenerateMessageID_Length");
        String id = msg.generateMessageID();
        assertEquals(id.length(), 10);
    }

    @Test
    public void testGenerateMessageID_Unique() {
        System.out.println("testGenerateMessageID_Unique");
        String id1 = msg.generateMessageID();
        String id2 = msg.generateMessageID();
        assertNotEquals(id1, id2);
    }
}