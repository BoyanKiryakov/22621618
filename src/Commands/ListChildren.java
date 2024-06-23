package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class ListChildren implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing ListChildren command...");

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
            listChildren(element);
        }
    }

    private void listChildren(XMLElement element) {
        if (element.getChildren().isEmpty()) {
            System.out.println("Element with ID '" + element.getAttribute("ID") + "' has no children.");
        } else {
            System.out.println("Children of element with ID '" + element.getAttribute("ID") + "':");
            for (XMLElement child : element.getChildren()) {
                System.out.println("\t<" + child.getName() + ">");
            }
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
