package program.commands;

import program.structure.CommandHandler;
import program.menu.Menu;
import program.structure.XMLElement;

import java.util.Scanner;

public class AccessChild implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing AccessChild command...");

        // Проверка дали е заредена XML структурата
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Заявете ID на елемента
        System.out.print("Enter element ID: ");
        String elementID = scanner.nextLine().trim();

        // Намерете елемента с посоченото ID
        XMLElement element = findElementById(elementID, Menu.rootElement);

        if (element == null) {
            System.out.println("Element with ID '" + elementID + "' not found.");
        } else {
            // Заявете индекса на детето
            System.out.print("Enter index of the child (starting from 1): ");
            int childIndex = scanner.nextInt();
            scanner.nextLine(); // Консумирайте новия ред

            // Достъп и отпечатване на детето на посочения индекс
            accessChildAtIndex(element, childIndex);
        }
    }

    private void accessChildAtIndex(XMLElement element, int index) {
        if (index < 1 || index > element.getChildren().size()) {
            System.out.println("Invalid child index.");
        } else {
            XMLElement child = element.getChildren().get(index - 1);
            String childValue = child.getTextContent().trim();
            System.out.println("Child at index " + index + ": <" + child.getTagName() + "> " + childValue);
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
