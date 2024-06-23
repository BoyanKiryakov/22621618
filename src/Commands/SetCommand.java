package Commands;

import Structure.CommandHandler;
import Structure.XMLElement;
import Structure.XMLFileHandler;
import Utils.XMLElementUtils;
import Menu.Menu;

import java.util.Scanner;

public class SetCommand implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Set command...");

        // Check if XML structure is loaded
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Ask for element ID
        System.out.print("Enter element ID: ");
        String elementID = scanner.nextLine().trim();

        // Ask for attribute key
        System.out.print("Enter attribute key (or 'text' for text content): ");
        String attributeKey = scanner.nextLine().trim();

        // Ask for new value
        System.out.print("Enter new value: ");
        String newValue = scanner.nextLine().trim();

        // Find the element to update
        XMLElement elementToUpdate = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (elementToUpdate != null) {
            if (attributeKey.equals("text")) {
                // Update text content
                elementToUpdate.setTextContent(newValue);
            } else {
                // Update attribute
                elementToUpdate.setAttribute(attributeKey, newValue);
            }

            // After updating the element, re-populate the XML content
            String updatedContent = elementToUpdate.toXMLString();

            // Update the XML content in memory using Menu.updateXmlContent()
            Menu.updateXmlContent();

            // Save the updated XML content to file
            XMLFileHandler.writeXMLFile(Menu.currentFile, updatedContent);
            System.out.println("Attribute or text content updated successfully.");
        } else {
            System.out.println("Element with ID '" + elementID + "' not found.");
        }
    }
}
