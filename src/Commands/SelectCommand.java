package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class SelectCommand implements CommandHandler {

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Executing Select command...");

        if (Menu.rootElement != null) {
            System.out.print("Enter the ID of the element to select: ");
            String id = scanner.nextLine().trim();

            System.out.print("Enter the attribute key to select: ");
            String key = scanner.nextLine().trim();

            XMLElement selectedElement = findElementById(Menu.rootElement, id);

            if (selectedElement != null) {
                String attributeValue = selectedElement.getAttribute(key);
                if (attributeValue != null) {
                    System.out.println("Attribute value found: " + attributeValue);
                } else {
                    System.out.println("Attribute '" + key + "' not found for element with ID '" + id + "'.");
                }
            } else {
                System.out.println("Element with ID '" + id + "' not found.");
            }
        } else {
            System.out.println("No XML structure loaded. Use 'createxml' or 'open' command first.");
        }
    }

    private XMLElement findElementById(XMLElement element, String id) {
        if (element.getAttribute("ID") != null && element.getAttribute("ID").equals(id)) {
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
