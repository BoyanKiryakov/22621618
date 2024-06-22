package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class AccessText implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Access Text command...");

        if (Menu.rootElement != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID of the element to access text content: ");
            String id = scanner.nextLine().trim();

            XMLElement selectedElement = findElementById(Menu.rootElement, id);
            if (selectedElement != null) {
                String textContent = selectedElement.getValue();
                System.out.println("Text content of element with ID '" + id + "': " + textContent);
            } else {
                System.out.println("Element with ID '" + id + "' not found.");
            }
        } else {
            System.out.println("No XML structure loaded. Use 'createxml' or 'open' command first.");
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
