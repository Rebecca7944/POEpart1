
package poepartone;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Message {

    // Parallel ArrayLists to store message data (dynamic arrays for Part 2)
    ArrayList<String> messageIds = new ArrayList<>();
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> msgHash = new ArrayList<>();
    ArrayList<String> recipientNumber = new ArrayList<>();
    ArrayList<String> messageStatus = new ArrayList<>();

    Random random = new Random();
    Scanner input = new Scanner(System.in);

    int numMessageSent = 0;

    
    Registration helper = new Registration();

    //Main message loop - uses FOR loop.

    public void sendingMessages() {

        System.out.println("\nHow many messages do you wish to enter?");
        int numberOfMessages = input.nextInt();
        input.nextLine(); 

        for (int i = 1; i <= numberOfMessages; i++) {

            System.out.println("\n----- MESSAGE " + i + " -----");

            // Generate unique 10-digit Message ID
            String messageID = generateMessageID();

            // Get recipient and validate 
            System.out.print("Enter recipient cell number: ");
            String recipient = input.nextLine();

            String recipientResult = checkRecipientCell(recipient);
            if (!recipientResult.equals("Cell phone number successfully captured.")) {
                System.out.println(recipientResult);
                i = i - 1; // retry this message
                continue;
            }

            // Get message text and validate length
            System.out.print("Enter your message: ");
            String messageText = input.nextLine();

            String lengthCheck = checkMessageLength(messageText);
            if (!lengthCheck.equals("Message ready to send.")) {
                System.out.println(lengthCheck);
                i = i - 1; 
                continue;
            }

            // Store data
            recipientNumber.add(recipient);
            messages.add(messageText);

            // Create message hash 
            String messageHash = createMessageHash(messageID, i, messageText);
            msgHash.add(messageHash);

            // Ask user: send / disregard / store
            String status = SentMessage();
            messageStatus.add(status);

            if (status.equals("Sent")) {
                numMessageSent++;
            }

            // Display details in the exact order required by rubric:
            // Message ID, Message Hash, Recipient, Message
            System.out.println("\n===== Message Details =====");
            System.out.println("Message ID   : " + messageID);
            System.out.println("Message Hash : " + messageHash);
            System.out.println("Recipient    : " + recipient);
            System.out.println("Message      : " + messageText);
            System.out.println("===========================");
        }

        // Save to JSON file
        storeMessages();

        // Show all messages + total
        printMessage();
        System.out.println("\nTotal messages sent: " + returnTotalMessages());
    }

    
     // Returns total sent messages.
   
    public int returnTotalMessages() {
        return numMessageSent;
    }

    
     // Generates a unique random 10-digit message ID.
     
    public String generateMessageID() {
        String messageID;
        do {
            long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
            messageID = String.valueOf(number);
        } while (messageIds.contains(messageID));

        messageIds.add(messageID);
        return messageID;
    }

    
     // Checks Message ID ≤ 10 characters.
   
    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell(String cellphone) {
        if (cellphone != null &&
            (cellphone.matches("^\\+27[0-9]{9}$") || cellphone.matches("^0[0-9]{9}$"))) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain "
                 + "an international code. Please correct the number and try again.";
        }
    }

    
     // it Validates message length (max 250 chars).
    
    public String checkMessageLength(String message) {
        if (message == null || message.trim().isEmpty()) {
            return "Message exceeds 250 characters by 0; please reduce the size.";
        }
        if (message.length() > 250) {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
        return "Message ready to send.";
    }

    
    public String createMessageHash(String messageID, int messageNumber, String messageText) {
        String firstTwoNumbers = messageID.substring(0, 2);
        String trimmed = messageText.trim();
        String[] words = trimmed.split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String hash = firstTwoNumbers + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    public String SentMessage() {
        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        System.out.print("Enter your choice: ");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.println("Message successfully sent");
                return "Sent";
            }
            case 2 -> {
                System.out.println("Press 0 to delete the message");
                int deleteChoice = input.nextInt();
                input.nextLine();
                if (deleteChoice == 0) {
                    System.out.println("Message deleted");
                    int lastIndex = messages.size() - 1;
                    messageIds.remove(lastIndex);
                    recipientNumber.remove(lastIndex);
                    messages.remove(lastIndex);
                    msgHash.remove(lastIndex);
                    return "Disregarded";
                } else {
                    System.out.println("Invalid option - message disregarded");
                    return "Disregarded";
                }
            }
            case 3 -> {
                System.out.println("Message successfully stored");
                return "Stored";
            }
            default -> {
                System.out.println("Invalid option - message disregarded");
                return "Disregarded";
            }
        }
    }

    
    public void storeMessages() {
        try (FileWriter writer = new FileWriter("messages.json")) {
            writer.write("[\n");

            for (int i = 0; i < messages.size(); i++) {
                writer.write("  {\n");
                writer.write("    \"messageID\": \"" + messageIds.get(i) + "\",\n");
                writer.write("    \"messageHash\": \"" + msgHash.get(i) + "\",\n");
                writer.write("    \"recipient\": \"" + recipientNumber.get(i) + "\",\n");
                writer.write("    \"message\": \"" + messages.get(i).replace("\"", "'") + "\",\n");
                writer.write("    \"status\": \"" + messageStatus.get(i) + "\"\n");
                writer.write("  }");

                if (i < messages.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }

            writer.write("]");
            System.out.println("\nMessages successfully saved to messages.json");

        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    public String printMessage() {
        StringBuilder allMessages = new StringBuilder();

        System.out.println("\n===== ALL MESSAGES =====");
        for (int i = 0; i < messages.size(); i++) {
            System.out.println("\nMessage Number : " + (i + 1));
            System.out.println("Message ID     : " + messageIds.get(i));
            System.out.println("Message Hash   : " + msgHash.get(i));
            System.out.println("Recipient      : " + recipientNumber.get(i));
            System.out.println("Message        : " + messages.get(i));
            System.out.println("Status         : " + messageStatus.get(i));

            allMessages.append("Message ").append(i + 1)
                       .append(": ").append(messages.get(i)).append("\n");
        }
        return allMessages.toString();
    }

    // Helper getters used by unit tests
    public ArrayList<String> getMessages() { return messages; }
    public ArrayList<String> getMsgHash() { return msgHash; }
    public ArrayList<String> getRecipientNumber() { return recipientNumber; }
    public ArrayList<String> getMessageIds() { return messageIds; }
    public ArrayList<String> getMessageStatus() { return messageStatus; }
} // end of class

