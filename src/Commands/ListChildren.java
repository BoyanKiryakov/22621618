package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class ListChildren implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing List Children command...");

        if (Menu.rootElement != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID of the parent element to list children: ");
            String id = scanner.nextLine().trim();

            XMLElement parentElement = findElementById(Menu.rootElement, id);
            if (parentElement != null) {
                listChildrenElements(parentElement);
            } else {
                System.out.println("Element with ID '" + id + "' not found.");
            }
        } else {
            System.out.println("No XML structure loaded. Use 'createxml' or 'open' command first.");
        }
    }

    private void listChildrenElements(XMLElement element) {
        if (element.getChildren().isEmpty()) {
            System.out.println("No children found for element with ID '" + element.getAttribute("ID") + "'.");
        } else {
            System.out.println("Children of element with ID '" + element.getAttribute("ID") + "':");
            for (XMLElement child : element.getChildren()) {
                System.out.println("\t- " + child.getName());
            }
        }
    }

    private XMLElement findElementById(XMLElement element, String id) {
        if (element == null) return null;
        if (id.equals(element.getAttribute("ID"))) return element;
        for (XMLElement child : element.getChildren()) {
            XMLElement foundElement = findElementById(child, id);
            if (foundElement != null) return foundElement;
        }
        return null;
    }
}
