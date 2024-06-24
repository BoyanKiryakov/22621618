package program.commands;

import program.structure.CommandWithArgs;
import program.structure.XMLElement;
import program.utils.XMLElementUtils;
import program.menu.Menu;

public class DeleteAttribute implements CommandWithArgs {

    @Override
    public void execute(String args) {
        System.out.println("Executing Delete Attribute command...");

        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        String[] parts = args.trim().split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Invalid command format. Use: delete <id> <key>");
            return;
        }

        String elementID = parts[0].trim();
        String tagName = parts[1].trim();

        XMLElement parentElement = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (parentElement == null) {
            System.out.println("Element with ID '" + elementID + "' not found.");
        } else {
            if (deleteChildElement(parentElement, tagName)) {
                System.out.println("Child element <" + tagName + "> deleted from element with ID '" + elementID + "'.");
                Menu.updateXmlContent();
            } else {
                System.out.println("Child element <" + tagName + "> not found for element with ID '" + elementID + "'.");
                System.out.println("Current children elements for element with ID '" + elementID + "':");
                for (XMLElement child : parentElement.getChildren()) {
                    System.out.println("\t<" + child.getTagName() + ">");
                }
            }
        }
    }

    private boolean deleteChildElement(XMLElement parentElement, String tagName) {
        for (XMLElement child : parentElement.getChildren()) {
            if (child.getTagName().equalsIgnoreCase(tagName)) {
                parentElement.removeChild(child);
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
    }
}
