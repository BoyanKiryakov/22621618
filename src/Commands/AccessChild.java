package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class AccessChild implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Access Child command...");

        if (Menu.rootElement != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID of the parent element: ");
            String id = scanner.nextLine().trim();

            XMLElement parentElement = findElementById(Menu.rootElement, id);
            if (parentElement != null) {
                System.out.print("Enter the index of the child element: ");
                int index = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (index >= 0 && index < parentElement.getChildren().size()) {
                    XMLElement childElement = parentElement.getChildren().get(index);
                    System.out.println("Child element " + index + ":");
                    System.out.println(childElement.toString());
                } else {
                    System.out.println("Invalid child index.");
                }
            } else {
                System.out.println("Element with ID '" + id + "' not found.");
            }
        } else {
            System.out.println("No XML structure loaded. Use 'createxml' or 'open' command first.");
        }
    }

    private XMLElement findElementById(XMLElement element, String id) {
        if (element == null) {
            return null;
        }

        if (id.equals(element.getAttribute("ID"))) {
            return element;
        }

        for (XMLElement child : element.getChildren()) {
            XMLElement foundElement = findElementById(child, id);
            if (foundElement != null) {
                return foundElement;
            }
        }

        return null;
    }
}
