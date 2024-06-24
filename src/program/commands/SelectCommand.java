package program.commands;

import program.structure.CommandHandler;
import program.structure.XMLElement;
import program.utils.XMLElementUtils;
import program.menu.Menu;

import java.util.Scanner;

public class SelectCommand implements CommandHandler {
    @Override
    public void execute() {
        System.out.println("Executing Select command...");

        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter element ID: ");
        String elementID = scanner.nextLine().trim();

        System.out.print("Enter attribute key: ");
        String attributeKey = scanner.nextLine().trim();

        XMLElement selectedElement = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (selectedElement != null) {
            String elementContent = selectedElement.toXMLString();

            String attributeValue = XMLElementUtils.extractTagContent(elementContent, attributeKey);

            if (attributeValue != null) {
                System.out.println("Attribute value of '" + attributeKey + "' for element with ID '" + elementID + "': " + attributeValue);
            } else {
                System.out.println("Attribute '" + attributeKey + "' not found for element with ID '" + elementID + "'.");
                System.out.println("Available attributes for element with ID '" + elementID + "': " + selectedElement.getAttributes());
            }
        } else {
            System.out.println("Element with ID '" + elementID + "' not found.");
        }
    }

}
