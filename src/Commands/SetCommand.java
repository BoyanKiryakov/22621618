package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class SetCommand implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Set command...");

        if (Menu.rootElement != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID of the element to set attribute: ");
            String id = scanner.nextLine().trim();

            XMLElement selectedElement = findElementById(Menu.rootElement, id);
            if (selectedElement != null) {
                System.out.print("Enter the attribute key: ");
                String key = scanner.nextLine().trim();

                System.out.print("Enter the attribute value: ");
                String value = scanner.nextLine().trim();

                selectedElement.setAttribute(key, value);
                System.out.println("Attribute '" + key + "' set successfully for element with ID '" + id + "'.");
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
