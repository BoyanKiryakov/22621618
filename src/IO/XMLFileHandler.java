package IO;

import java.io.*;
import java.util.*;
public class XMLFileHandler {
    public static void createXMLFileInteractive(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<people>\n");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter person ID (or 'done' to finish): ");
                String personID = scanner.nextLine();
                if (personID.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.print("Enter person name: ");
                String personName = scanner.nextLine();

                System.out.print("Enter person age: ");
                String personAge = scanner.nextLine();

                System.out.print("Enter person address: ");
                String personAddress = scanner.nextLine();

                writer.write("\t<person ID=\"" + personID + "\">\n");
                writer.write("\t\t<name>" + personName + "</name>\n");
                writer.write("\t\t<age>" + personAge + "</age>\n");
                writer.write("\t\t<address>" + personAddress + "</address>\n");
                writer.write("\t</person>\n");
            }
            writer.write("</people>");
            System.out.println("XML file created successfully: " + filePath);
        } catch (IOException e) {
            System.out.println("Error creating XML file: " + e.getMessage());
        }
    }
}
