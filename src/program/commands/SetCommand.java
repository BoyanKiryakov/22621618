package program.commands;

import program.structure.CommandHandler;
import program.structure.XMLElement;
import program.structure.XMLFileHandler;
import program.utils.XMLElementUtils;
import program.menu.Menu;

import java.util.Scanner;

public class SetCommand implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Set command...");
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter element ID: ");
        String elementID = scanner.nextLine().trim();

        XMLElement elementToUpdate = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (elementToUpdate != null) {

            System.out.print("Enter child element to edit (e.g., name, age, address): ");
            String childElementName = scanner.nextLine().trim();

            XMLElement childElement = findChildElementByName(elementToUpdate, childElementName);

            if (childElement != null) {
                System.out.print("Enter new value: ");
                String newValue = scanner.nextLine().trim();
                childElement.setTextContent(newValue);
                String updatedContent = Menu.rootElement.toXMLString();
                Menu.updateXmlContent();

                XMLFileHandler.writeXMLFile(Menu.currentFile, updatedContent);
                System.out.println("Child element '" + childElementName + "' updated successfully.");
            } else {
                System.out.println("Child element '" + childElementName + "' not found in element with ID '" + elementID + "'.");
            }
        } else {
            System.out.println("Element with ID '" + elementID + "' not found.");
        }
    }

    private XMLElement findChildElementByName(XMLElement parentElement, String tagName) {
        for (XMLElement child : parentElement.getChildren()) {
            if (child.getTagName().equalsIgnoreCase(tagName)) {
                return child;
            }
        }
        return null;
    }
}
