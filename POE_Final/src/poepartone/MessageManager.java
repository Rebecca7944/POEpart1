/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;


import java.util.ArrayList;
// These ArrayLists are used to populate the required arrays .
    // They store the Message objects dynamically instead of using hard coded data.
public class MessageManager {
    private ArrayList<Message> sentMessages = new ArrayList<>();
    private ArrayList<Message> disregardedMessages = new ArrayList<>();
    private ArrayList<Message> storedMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();

    public void addMessage(Message m) {
        messageHashes.add(m.getMessageHash());
        messageIDs.add(m.getMessageID());
        switch (m.getFlag()) {
            case "Sent": sentMessages.add(m); break;
            case "Disregard": disregardedMessages.add(m); break;
            case "Stored": storedMessages.add(m); break;
        }
    }

    public String displayStoredSenderRecipient() {
        if (storedMessages.isEmpty()) return "No stored messages.";
        StringBuilder sb = new StringBuilder("<html><body style='width: 250px; font-family: Arial; color: #333333;'>");
        sb.append("<h3 style='color: #0277BD;'>Stored Contacts</h3>"); // Light Blue Header
        for (Message m : storedMessages) {
            sb.append("<p>👤 <b>Sender:</b> ").append(m.getSenderName())
              .append("<br>📱 <b>Recipient:</b> ").append(m.getRecipient()).append("</p>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }

    public String longestStoredMessage() {
        if (storedMessages.isEmpty()) return "No stored messages.";
        Message longest = storedMessages.get(0);
        for (Message m : storedMessages) {
            if (m.getMessageText().length() > longest.getMessageText().length()) {
                longest = m;
            }
        }
        return "<html><body style='width: 250px; font-family: Arial; color: #333333;'>"
             + "<h3 style='color: #2E7D32;'>Longest Message</h3>" // 
             + "<p>" + longest.getMessageText() + "</p>"
             + "</body></html>";
    }

    public String searchByID(String id) {
        for (Message m : storedMessages) {
            if (m.getMessageID().equals(id)) {
                return "<html><body style='width: 250px; font-family: Arial; color: #333333;'>"
                     + "<h3 style='color: #0277BD;'>Search Result</h3>"
                     + "<p><b>Recipient:</b> " + m.getRecipient() + "<br>"
                     + "<b>Message:</b> " + m.getMessageText() + "</p></body></html>";
            }
        }
        return "<html><body style='color: #C62828;'>❌ Message ID not found.</body></html>";
    }

    public String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder("<html><body style='width: 250px; font-family: Arial; color: #333333;'>");
        sb.append("<h3 style='color: #0277BD;'>Messages for ").append(recipient).append("</h3>");
        boolean found = false;
        for (Message m : storedMessages) {
            if (m.getRecipient().equals(recipient)) {
                sb.append("<p>💬 ").append(m.getMessageText()).append("</p>");
                found = true;
            }
        }
        sb.append("</body></html>");
        return found ? sb.toString() : "<html><body style='color: #C62828;'>❌ No messages found for this recipient.</body></html>";
    }

    public String deleteByHash(String hash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                String msg = storedMessages.get(i).getMessageText();
                storedMessages.remove(i);
                messageHashes.remove(hash);
                return "<html><body style='color: #2E7D32;'>✅ Message: \"<b>" + msg + "</b>\" successfully deleted.</body></html>";
            }
        }
        return "<html><body style='color: #C62828;'>❌ Message hash not found.</body></html>";
    }

    // Beautiful Pastel Table for the Report
    public String displayReport() {
        if (storedMessages.isEmpty()) return "No stored messages.";
        
        StringBuilder sb = new StringBuilder("<html><body style='width: 400px; font-family: Arial; color: #333333;'>");
        sb.append("<h3 style='color: #0277BD;'>Stored Messages Report</h3>");
        sb.append("<table border='1' cellpadding='5' style='border-collapse: collapse; border-color: #B3E5FC;'>");
        // Light Blue Table Header
        sb.append("<tr style='background-color: #E1F5FE; color: #0277BD;'>"
                + "<th>Hash</th><th>Recipient</th><th>Message</th></tr>");
        
        for (Message m : storedMessages) {
            sb.append("<tr>")
              .append("<td>").append(m.getMessageHash()).append("</td>")
              .append("<td>").append(m.getRecipient()).append("</td>")
              .append("<td>").append(m.getMessageText()).append("</td>")
              .append("</tr>");
        }
        sb.append("</table></body></html>");
        return sb.toString();
    }

    public ArrayList<Message> getSentMessages() { return sentMessages; }
    public ArrayList<Message> getDisregardedMessages() { return disregardedMessages; }
    public ArrayList<Message> getStoredMessages() { return storedMessages; }
}