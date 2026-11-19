/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;

import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String flag; 
    private String senderName; 

    public Message(int messageNumber, String recipient, String messageText, String senderName) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.senderName = senderName;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    private String generateID() {
        Random rand = new Random();
        long id = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }

    public boolean checkMessageID() { return messageID != null && messageID.length() <= 10; }

    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+27[0-9]{9}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
// This method creates the Hash 
    // First 2 numbers of ID + ":" + Message Number + ":" + First & Last Word 
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].replaceAll("[^a-zA-Z]", "");
        String last = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return (firstTwo + ":" + messageNumber + ":" + first + last).toUpperCase();
    }

    public String SentMessage(int choice) {
        switch (choice) {
            case 1: flag = "Sent"; return "Message successfully sent.";
            case 2: flag = "Disregard"; return "Press 0 to delete the message.";
            case 3: flag = "Stored"; return "Message successfully stored.";
            default: flag = "Disregard"; return "Invalid choice, message disregarded.";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\nMessage Hash: " + messageHash 
             + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getFlag() { return flag; }
    public String getSenderName() { return senderName; }
    public void setFlag(String flag) { this.flag = flag; }
}