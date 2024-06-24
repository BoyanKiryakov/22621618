package program.commands;

import program.structure.CommandHandler;
import program.structure.XMLElement;
import program.utils.XMLElementUtils;
import program.menu.Menu;

import java.util.Scanner;

public class DeleteAttribute implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Delete Child Element command...");

        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter element ID: ");
        String elementID = scanner.nextLine().trim();


        System.out.print("Enter child element tag name to delete: ");
        String childTagName = scanner.nextLine().trim();

        XMLElement parentElement = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (parentElement != null) {
            if (deleteChildElement(parentElement, childTagName)) {
                System.out.println("Child element <" + childTagName + "> deleted from element with ID '" + elementID + "'.");
                Menu.updateXmlContent();
            } else {
                System.out.println("Child element <" + childTagName + "> not found for element with ID '" + elementID + "'.");
                System.out.println("Current children elements for element with ID '" + elementID + "':");
                for (XMLElement child : parentElement.getChildren()) {
                    System.out.println("\t<" + child.getTagName() + ">");
                }
            }
        } else {
            System.out.println("Element with ID '" + elementID + "' not found.");
        }
    }

    private boolean deleteChildElement(XMLElement parentElement, String childTagName) {
        for (XMLElement child : parentElement.getChildren()) {
            if (child.getTagName().equalsIgnoreCase(childTagName)) {
                parentElement.removeChild(child);
                return true;
            }
        }
        return false;
    }
}
