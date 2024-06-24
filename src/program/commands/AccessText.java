package program.commands;

import program.structure.CommandHandler;
import program.menu.Menu;
import program.structure.XMLElement;

import java.util.Scanner;

public class AccessText implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing AccessText command...");

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
            // Отпечатайте всички текстови съдържания, свързани с елемента
            System.out.println("Text content of element with ID '" + elementID + "':");
            collectTextContent(element);
        }
    }

    private void collectTextContent(XMLElement element) {
        // Отпечатване на текстовото съдържание на текущия елемент
        if (element.getTextContent() != null && !element.getTextContent().isEmpty()) {
            System.out.println(element.getTextContent().trim());
        }

        // Рекурсивно отпечатване на текстовото съдържание на децата
        for (XMLElement child : element.getChildren()) {
            collectTextContent(child);
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
