package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class AddNewChild implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Add New Child command...");

        if (Menu.rootElement != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID of the parent element: ");
            String id = scanner.nextLine().trim();

            XMLElement parentElement = findElementById(Menu.rootElement, id);
            if (parentElement != null) {
                XMLElement newChild = createNewChildElement();
                parentElement.addChild(newChild);
                System.out.println("New child element added successfully to element with ID '" + id + "'.");
            } else {
                System.out.println("Element with ID '" + id + "' not found.");
            }
        } else {
            System.out.println("No XML structure loaded. Use 'createxml' or 'open' command first.");
        }
    }

    private XMLElement createNewChildElement() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name for the new child element: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter the value for the new child element: ");
        String value = scanner.nextLine().trim();
        return new XMLElement(name, value);
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
