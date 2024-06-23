package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class AccessChild implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing AccessChild command...");

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
            // Ask for child index
            System.out.print("Enter index of the child (starting from 1): ");
            int childIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Access and print the child element at the specified index
            accessChildAtIndex(element, childIndex);
        }
    }

    private void accessChildAtIndex(XMLElement element, int index) {
        if (index < 1 || index > element.getChildren().size()) {
            System.out.println("Invalid child index.");
        } else {
            XMLElement child = element.getChildren().get(index - 1);
            String childValue = child.getTextContent().trim();
            System.out.println("Child at index " + index + ": <" + child.getName() + "> " + childValue);
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
