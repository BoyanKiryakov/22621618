package Commands;

import Structure.CommandHandler;
import Structure.XMLElement;
import Utils.XMLElementUtils;
import Menu.Menu;

import java.util.Scanner;

public class DeleteAttribute implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Delete Child Element command...");

        // Check if XML structure is loaded
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Ask for element ID
        System.out.print("Enter element ID: ");
        String elementID = scanner.nextLine().trim();

        // Ask for child element tag name
        System.out.print("Enter child element tag name to delete: ");
        String childTagName = scanner.nextLine().trim();

        // Find the element with the specified ID
        XMLElement parentElement = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (parentElement != null) {
            // Remove the child element from the parent element
            if (deleteChildElement(parentElement, childTagName)) {
                System.out.println("Child element <" + childTagName + "> deleted from element with ID '" + elementID + "'.");
                // Update XML content in memory
                Menu.updateXmlContent();
            } else {
                System.out.println("Child element <" + childTagName + "> not found for element with ID '" + elementID + "'.");
                System.out.println("Current children elements for element with ID '" + elementID + "':");
                for (XMLElement child : parentElement.getChildren()) {
                    System.out.println("\t<" + child.getName() + ">");
                }
            }
        } else {
            System.out.println("Element with ID '" + elementID + "' not found.");
        }
    }

    private boolean deleteChildElement(XMLElement parentElement, String childTagName) {
        // Iterate through children elements
        for (XMLElement child : parentElement.getChildren()) {
            // Check if the current child element matches the tag name
            if (child.getName().equalsIgnoreCase(childTagName)) {
                // Remove the child element
                parentElement.removeChild(child);
                return true; // Return true if successfully deleted
            }
        }
        return false; // Return false if child element not found or deleted
    }
}
