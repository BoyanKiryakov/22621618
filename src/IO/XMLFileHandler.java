package IO;

import java.io.*;
import java.util.Scanner;

public class XMLFileHandler {

    public static void createXMLFileInteractive(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<people>\n");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter person ID (or 'done' to finish): ");
                String personID = scanner.nextLine().trim();
                if (personID.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.print("Enter person name: ");
                String personName = scanner.nextLine().trim();

                System.out.print("Enter person age: ");
                String personAge = scanner.nextLine().trim();

                System.out.print("Enter person address: ");
                String personAddress = scanner.nextLine().trim();

                StringBuilder personElement = new StringBuilder();
                personElement.append("\t<person ID=\"").append(personID).append("\">\n");
                personElement.append("\t\t<name>").append(personName).append("</name>\n");
                personElement.append("\t\t<age>").append(personAge).append("</age>\n");
                personElement.append("\t\t<address>").append(personAddress).append("</address>\n");
                personElement.append("\t</person>\n");
                writer.write(personElement.toString());
            }

            writer.write("</people>");
            System.out.println("XML file created successfully: " + filePath);

        } catch (IOException e) {
            System.out.println("Error creating XML file: " + e.getMessage());
        }
    }

    public static XMLElement parseXML(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder xmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlContent.append(line).append("\n");
            }
            String content = xmlContent.toString();
            return new XMLElement("people", content);
        } catch (IOException e) {
            System.out.println("Error reading XML file: " + e.getMessage());
        }
        return null;
    }
}
