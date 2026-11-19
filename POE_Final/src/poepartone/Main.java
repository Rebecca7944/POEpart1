/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;

        // Note: Although the POE mentions console only, the module lecturer 
        // requested the use of JOptionPane for GUI interactions. 
        // HTML tags are used inside the strings to style the popups with colors.
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Login loginSystem = new Login();
        MessageManager manager = new MessageManager();

        // Styled Welcome
        String welcomeMsg = "<html><body style='width: 250px; font-family: Arial; color: #333333;'>"
                + "<h2 style='color: #5E35B1; text-align: center;'>Welcome to QuickChat</h2>"
                + "<p style='text-align: center;'>Let's get you registered.</p>"
                + "</body></html>";
        JOptionPane.showMessageDialog(null, welcomeMsg, "QuickChat Setup", JOptionPane.INFORMATION_MESSAGE);

        // ================= REGISTRATION =================
        String firstName = JOptionPane.showInputDialog(null, "Enter your first name:", "Registration",
                JOptionPane.QUESTION_MESSAGE);
        
        String lastName = JOptionPane.showInputDialog(null, "Enter your last name:", "Registration",
                JOptionPane.QUESTION_MESSAGE);
        
        if (firstName == null || lastName == null) System.exit(0);

        String username, password, cellphone;
        boolean allValid = false;

        while (!allValid) {
            username = JOptionPane.showInputDialog(null, "Enter username:", "Registration", JOptionPane.QUESTION_MESSAGE);
            password = JOptionPane.showInputDialog(null, "Enter password:", "Registration", JOptionPane.QUESTION_MESSAGE);
            cellphone = JOptionPane.showInputDialog(null, "Enter cellphone (+27XXXXXXXXX):", "Registration", JOptionPane.QUESTION_MESSAGE);

            if (username == null || password == null || cellphone == null) System.exit(0);

            boolean usernameOK = loginSystem.checkUserName(username);
            boolean passwordOK = loginSystem.checkPasswordComplexity(password);
            boolean cellphoneOK = loginSystem.checkCellPhoneNumber(cellphone);

            if (usernameOK && passwordOK && cellphoneOK) {
                allValid = true;
                loginSystem.registerUser(username, password, cellphone);
                loginSystem.setUserDetails(firstName, lastName);
                
                // Styled Success (Light Green)
                String successMsg = "<html><body style='width: 250px; font-family: Arial; color: #333333;'>"
                        + "<h3 style='color: #2E7D32;'>✅ Registration Successful!</h3>"
                        + "<p>Welcome, <b>" + firstName + "</b>.</p>"
                        + "</body></html>";
                JOptionPane.showMessageDialog(null, successMsg, "QuickChat | Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Styled Error Message (Light Red)
                StringBuilder errorMsg = new StringBuilder("<html><body style='width: 300px; font-family: Arial; color: #333333;'>");
                errorMsg.append("<h3 style='color: #C62828;'>⚠️ Registration Failed</h3>");
                errorMsg.append("<p>Please fix the following:</p><ul>");
                if (!usernameOK) errorMsg.append("<li style='color: #D32F2F;'>Username must contain '_' and be max 5 chars.</li>");
                if (!passwordOK) errorMsg.append("<li style='color: #D32F2F;'>Password needs 8+ chars, 1 capital, 1 number, 1 special.</li>");
                if (!cellphoneOK) errorMsg.append("<li style='color: #D32F2F;'>Cellphone must start with +27 and have 9 digits.</li>");
                errorMsg.append("</ul></body></html>");
                
                JOptionPane.showMessageDialog(null, errorMsg.toString(), "QuickChat | Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // ================= LOGIN =================
        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < 3 && !loggedIn) {
            String lUser = JOptionPane.showInputDialog(null, "Enter username to login:", "Login", JOptionPane.QUESTION_MESSAGE);
            String lPass = JOptionPane.showInputDialog(null, "Enter password to login:", "Login", JOptionPane.QUESTION_MESSAGE);

            if (lUser == null || lPass == null) System.exit(0);

            if (loginSystem.loginUser(lUser, lPass)) {
                loggedIn = true;
                // Styled Login Success 
                String loginMsg = "<html><body style='width: 250px; font-family: Arial; color: #333333;'>"
                        + "<h3 style='color: #2E7D32;'>✅ Login Successful</h3>"
                        + "<p><b>" + loginSystem.returnLoginStatus(true) + "</b></p>"
                        + "</body></html>";
                JOptionPane.showMessageDialog(null, loginMsg, "QuickChat | Login", JOptionPane.INFORMATION_MESSAGE);
            } else {
                attempts++;
                
                String failMsg = "<html><body style='width: 250px; font-family: Arial; color: #333333;'>"
                        + "<h3 style='color: #C62828;'>❌ Incorrect Details</h3>"
                        + "<p>Attempts remaining: <b style='color: #D32F2F;'>" + (3 - attempts) + "</b></p>"
                        + "</body></html>";
                JOptionPane.showMessageDialog(null, failMsg, "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (!loggedIn) {
            JOptionPane.showMessageDialog(null, "Maximum login attempts exceeded. Exiting.", "Exiting", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Message m : JSONHandler.loadMessages()) manager.addMessage(m);

        // ================= QUICKCHAT MENU =================
        int totalMessages = 0;
        while (true) {
            String input = JOptionPane.showInputDialog(null, "How many messages do you wish to enter?", "QuickChat", JOptionPane.QUESTION_MESSAGE);
            if (input == null) System.exit(0);
            try {
                totalMessages = Integer.parseInt(input);
                if (totalMessages >= 0) break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        int messageCounter = 0;
        boolean running = true;

        while (running) {
            // Styled Main Menu (Light Blue)
            String menu = "<html><body style='width: 250px; font-family: Arial; color: #333333;'>"
                        + "<h2 style='color: #0277BD;'>QuickChat Menu</h2>"
                        + "<ol style='color: #01579B;'>"
                        + "<li>Send Messages</li>"
                        + "<li>Show recently sent messages</li>"
                        + "<li>Quit</li>"
                        + "<li>Stored Messages</li>"
                        + "</ol>"
                        + "<p>Select an option (1-4):</p>"
                        + "</body></html>";
            
            String choice = JOptionPane.showInputDialog(null, menu, "Main Menu", JOptionPane.QUESTION_MESSAGE);
            if (choice == null) { running = false; break; }

            switch (choice) {
                case "1":
                    if (messageCounter >= totalMessages) {
                        JOptionPane.showMessageDialog(null, "You have reached your message limit.", "Limit Reached", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    String rec = JOptionPane.showInputDialog(null, "Enter recipient cell number (+27...):", "Send Message", JOptionPane.QUESTION_MESSAGE);
                    if (rec == null) break;
                    
                    Message tempMsg = new Message(messageCounter + 1, rec, "test", firstName);
                    if (!tempMsg.checkRecipientCell().equals("Cell phone number successfully captured.")) {
                        String errRec = "<html><body style='color: #C62828;'><b>❌ " + tempMsg.checkRecipientCell() + "</b></body></html>";
                        JOptionPane.showMessageDialog(null, errRec, "Invalid Number", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    
                    String text = JOptionPane.showInputDialog(null, "Enter your message (max 250 chars):", "Send Message", JOptionPane.QUESTION_MESSAGE);
                    if (text == null) break;
                    if (text.length() > 250) {
                        JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (text.length() - 250) + "; please reduce the size.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    messageCounter++;
                    Message msg = new Message(messageCounter, rec, text, firstName);
                    
                    // Styled Action Menu
                    String actionMenu = "<html><body style='width: 200px; font-family: Arial; color: #333333;'>"
                            + "<h3 style='color: #0277BD;'>What do you want to do?</h3>"
                            + "<p><b>1)</b> Send<br><b>2)</b> Disregard<br><b>3)</b> Store</p>"
                            + "</body></html>";
                    String actionStr = JOptionPane.showInputDialog(null, actionMenu, "Message Action", JOptionPane.QUESTION_MESSAGE);
                    if (actionStr == null) break;
                    
                    try {
                        int action = Integer.parseInt(actionStr);
                        String result = msg.SentMessage(action);
                        
                        // Styled Status Result
                        String color = msg.getFlag().equals("Disregard") ? "#C62828" : "#2E7D32";
                        String resultMsg = "<html><body style='width: 250px; font-family: Arial;'>"
                                + "<h3 style='color: " + color + ";'>" + result + "</h3>"
                                + "</body></html>";
                        JOptionPane.showMessageDialog(null, resultMsg, "Status", JOptionPane.INFORMATION_MESSAGE);
                        
                        if (msg.getFlag().equals("Sent") || msg.getFlag().equals("Stored")) {
                            // Styled Message Details
                            String details = "<html><body style='width: 300px; font-family: Arial; color: #333333;'>"
                                    + "<h3 style='color: #0277BD;'>Message Details</h3>"
                                    + "<p><b>ID:</b> " + msg.getMessageID() + "<br>"
                                    + "<b>Hash:</b> " + msg.getMessageHash() + " <i>(ID + Msg No + First/Last Word)</i><br>"
                                    + "<b>Recipient:</b> " + msg.getRecipient() + "<br>"
                                    + "<b>Message:</b> " + msg.getMessageText() + "</p>"
                                    + "</body></html>";
                            JOptionPane.showMessageDialog(null, details, "Message Details", JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        manager.addMessage(msg);
                        if (msg.getFlag().equals("Stored")) {
                            JSONHandler.saveMessages(manager.getStoredMessages());
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid action choice.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                case "3":
                    running = false;
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + manager.getSentMessages().size() + "\nGoodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                case "4":
                    storedMessagesMenuGUI(manager);
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    private static void storedMessagesMenuGUI(MessageManager manager) {
        boolean back = false;
        while (!back) {
            String menu = "<html><body style='width: 300px; font-family: Arial; color: #333333;'>"
                        + "<h2 style='color: #0277BD;'>Stored Messages</h2>"
                        + "<p><b>a)</b> Show sender & recipient<br>"
                        + "<b>b)</b> Longest message<br>"
                        + "<b>c)</b> Search by ID<br>"
                        + "<b>d)</b> Search by recipient<br>"
                        + "<b>e)</b> Delete by hash<br>"
                        + "<b>f)</b> Full report<br>"
                        + "<b>x)</b> Back</p>"
                        + "</body></html>";
            
            String opt = JOptionPane.showInputDialog(null, menu, "Stored Messages", JOptionPane.QUESTION_MESSAGE);
            if (opt == null) break;
            opt = opt.toLowerCase();

            switch (opt) {
                case "a": JOptionPane.showMessageDialog(null, manager.displayStoredSenderRecipient(),
                        "Sender & Recipient", JOptionPane.INFORMATION_MESSAGE); break;
                case "b": JOptionPane.showMessageDialog(null, manager.longestStoredMessage(), 
                        "Longest Message", JOptionPane.INFORMATION_MESSAGE); break;
                case "c": 
                    String id = JOptionPane.showInputDialog("Enter ID:");
                    if (id != null) JOptionPane.showMessageDialog(null, manager.searchByID(id), "Search Result", JOptionPane.INFORMATION_MESSAGE); 
                    break;
                case "d": 
                    String rcp = JOptionPane.showInputDialog("Enter Recipient (+27...):");
                    if (rcp != null) JOptionPane.showMessageDialog(null, manager.searchByRecipient(rcp), "Search Result", JOptionPane.INFORMATION_MESSAGE); 
                    break;
                case "e": 
                    String hash = JOptionPane.showInputDialog("Enter Hash:");
                    if (hash != null) {
                        JOptionPane.showMessageDialog(null, manager.deleteByHash(hash), "Delete Status", JOptionPane.INFORMATION_MESSAGE); 
                        JSONHandler.saveMessages(manager.getStoredMessages());
                    }
                    break;
                case "f": JOptionPane.showMessageDialog(null, manager.displayReport(), "Full Report", JOptionPane.INFORMATION_MESSAGE); break;
                case "x": back = true; break;
                default: JOptionPane.showMessageDialog(null, "Invalid choice.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
