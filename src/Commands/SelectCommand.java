package Commands;

import Structure.CommandHandler;
import Structure.XMLElement;
import Utils.XMLElementUtils;
import Menu.Menu;

import java.util.Scanner;

public class SelectCommand implements CommandHandler {
    @Override
    public void execute() {
        System.out.println("Executing Select command...");

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
        System.out.print("Enter attribute key: ");
        String attributeKey = scanner.nextLine().trim();

        // Find the element with the specified ID
        XMLElement selectedElement = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (selectedElement != null) {
            // Extract the entire element content as a string
            String elementContent = selectedElement.toXMLString();

            // Use pattern matching to find the attribute value
            String attributeValue = XMLElementUtils.extractAttributeFromElement(elementContent, attributeKey);

            if (attributeValue != null) {
                System.out.println("Attribute value of '" + attributeKey + "' for element with ID '" + elementID + "': " + attributeValue);
            } else {
                System.out.println("Attribute '" + attributeKey + "' not found for element with ID '" + elementID + "'.");
                System.out.println("Available attributes for element with ID '" + elementID + "': " + selectedElement.getAttributes());
            }
        } else {
            System.out.println("Element with ID '" + elementID + "' not found.");
        }
    }
}
