package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class DeleteAttribute implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Delete Attribute command...");

        if (Menu.rootElement != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID of the element to delete attribute: ");
            String id = scanner.nextLine().trim();

            XMLElement selectedElement = findElementById(Menu.rootElement, id);
            if (selectedElement != null) {
                System.out.print("Enter the key of the attribute to delete: ");
                String key = scanner.nextLine().trim();

                selectedElement.removeAttribute(key);
                System.out.println("Attribute '" + key + "' deleted successfully.");
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
