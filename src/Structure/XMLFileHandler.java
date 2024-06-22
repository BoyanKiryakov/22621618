package Structure;

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
            return parseXMLElement(xmlContent.toString());
        } catch (IOException e) {
            System.out.println("Error reading XML file: " + e.getMessage());
        }
        return null;
    }

    private static XMLElement parseXMLElement(String content) {
        if (content.contains("<people>")) {
            XMLElement root = new XMLElement("people", null);
            int startIndex = content.indexOf("<person");
            while (startIndex != -1) {
                int endIndex = content.indexOf("</person>", startIndex) + "</person>".length();
                String personContent = content.substring(startIndex, endIndex);

                int idStart = personContent.indexOf("ID=\"") + 4;
                int idEnd = personContent.indexOf("\"", idStart);
                String personID = personContent.substring(idStart, idEnd);

                String personName = extractTagValue(personContent, "name");
                String personAge = extractTagValue(personContent, "age");
                String personAddress = extractTagValue(personContent, "address");

                XMLElement person = new XMLElement("person", null);
                person.setAttribute("ID", personID);
                person.addChild(new XMLElement("name", personName));
                person.addChild(new XMLElement("age", personAge));
                person.addChild(new XMLElement("address", personAddress));

                root.addChild(person);

                startIndex = content.indexOf("<person", endIndex);
            }
            return root;
        }
        return null;
    }

    private static String extractTagValue(String content, String tagName) {
        int startIndex = content.indexOf("<" + tagName + ">") + tagName.length() + 2;
        int endIndex = content.indexOf("</" + tagName + ">", startIndex);
        return content.substring(startIndex, endIndex);
    }
}
