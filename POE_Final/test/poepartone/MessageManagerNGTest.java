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

public class MessageManagerNGTest {
    
    MessageManager mm;

    @BeforeMethod
    public void setUp() {
        mm = new MessageManager();
        
        // Populating with test data
        Message m1 = new Message(1, "+27834557896", "Did you get the cake?", "Developer");
        m1.setFlag("Sent"); mm.addMessage(m1);

        Message m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Developer");
        m2.setFlag("Stored"); mm.addMessage(m2);

        Message m3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.", "Developer");
        m3.setFlag("Disregard"); mm.addMessage(m3);

        Message m4 = new Message(4, "+27838884567", "It is dinner time !", "Developer");
        m4.setFlag("Sent"); mm.addMessage(m4);

        Message m5 = new Message(5, "+27838884567", "Ok, I am leaving without you.", "Developer");
        m5.setFlag("Stored"); mm.addMessage(m5);
    }

    @Test
    public void testSentArrayPopulated() {
        assertEquals(mm.getSentMessages().get(0).getMessageText(), "Did you get the cake?");
        assertEquals(mm.getSentMessages().get(1).getMessageText(), "It is dinner time !");
    }

    @Test
    public void testLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(mm.longestStoredMessage(), expected);
    }

    @Test
    public void testSearchByID() {
        String idToSearch = mm.getStoredMessages().get(0).getMessageID();
        assertTrue(mm.searchByID(idToSearch).contains("Where are you?"));
    }

    @Test
    public void testSearchByRecipient() {
        String result = mm.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you?"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        String hashToDelete = mm.getStoredMessages().get(0).getMessageHash();
        String result = mm.deleteByHash(hashToDelete);
        assertTrue(result.contains("successfully deleted"));
        assertEquals(mm.getStoredMessages().size(), 1); 
    }

    @Test
    public void testDisplayReport() {
        String report = mm.displayReport();
        assertTrue(report.contains("Hash:"));
        assertTrue(report.contains("Recipient:"));
        assertTrue(report.contains("Message:"));
    }
}