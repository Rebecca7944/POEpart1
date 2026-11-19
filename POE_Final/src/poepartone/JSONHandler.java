/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartone;
import java.io.*; 
import java.util.ArrayList;

public class JSONHandler {
    private static final String FILE = "storedMessages.txt";

    public static void saveMessages(ArrayList<Message> messages) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE))) {
            for (Message m : messages) {
                writer.println(m.getMessageID() + "|" + m.getMessageHash() 
                        + "|" + m.getRecipient() + "|" + m.getMessageText());
            }
        } catch (IOException e) {
            System.out.println("Error saving messages.");
        }
    }

    public static ArrayList<Message> loadMessages() {
        ArrayList<Message> list = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int num = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                   Message m = new Message(num++, parts[2], parts[3], "Developer");
                    m.setFlag("Stored");
                    list.add(m);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading messages.");
        }
        return list;
    }
}
