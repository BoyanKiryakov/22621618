package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLFileHandler;
import Structure.XMLElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateXML implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Creating XML file...");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("default.xml"))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<people>\n");

            Scanner scanner = new Scanner(System.in);
            int idCounter = 1;

            while (true) {
                System.out.print("Enter person name (or 'done' to finish): ");
                String personName = scanner.nextLine().trim();
                if (personName.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.print("Enter person age: ");
                String personAge = scanner.nextLine().trim();

                System.out.print("Enter person address: ");
                String personAddress = scanner.nextLine().trim();
                String personID = Integer.toString(idCounter);
                StringBuilder personElement = new StringBuilder();
                personElement.append("\t<person ID=\"").append(personID).append("\">\n");
                personElement.append("\t\t<name>").append(personName).append("</name>\n");
                personElement.append("\t\t<age>").append(personAge).append("</age>\n");
                personElement.append("\t\t<address>").append(personAddress).append("</address>\n");
                personElement.append("\t</person>\n");

                writer.write(personElement.toString());

                idCounter++;
            }

            writer.write("</people>");
            System.out.println("XML file created successfully: default.xml");

        } catch (IOException e) {
            System.out.println("Error creating XML file: " + e.getMessage());
        }
    }
}
