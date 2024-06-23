package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class AccessText implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing AccessText command...");

        // Check if XML structure is loaded
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Ask for element ID
        System.out.print("Enter element ID: ");
        String elementID = scanner.nextLine().trim();

        // Find the element with the specified ID
        XMLElement element = findElementById(elementID, Menu.rootElement);

        if (element == null) {
            System.out.println("Element with ID '" + elementID + "' not found.");
        } else {
            // Print all textual content associated with the element
            System.out.println("Text content of element with ID '" + elementID + "':");
            collectTextContent(element);
        }
    }

    private void collectTextContent(XMLElement element) {
        // Print text content of current element
        if (element.getTextContent() != null && !element.getTextContent().isEmpty()) {
            System.out.println(element.getTextContent().trim());
        }

        // Recursively print text content of children
        for (XMLElement child : element.getChildren()) {
            collectTextContent(child);
        }
    }

    private XMLElement findElementById(String id, XMLElement element) {
        if (element == null) {
            return null;
        }

        if (id.equals(element.getAttribute("ID"))) {
            return element;
        }

        for (XMLElement child : element.getChildren()) {
            XMLElement found = findElementById(id, child);
            if (found != null) {
                return found;
            }
        }

        return null;
    }
}
