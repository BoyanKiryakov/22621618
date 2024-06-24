package program.commands;

import program.structure.CommandWithArgs;
import program.structure.XMLElement;
import program.utils.XMLElementUtils;
import program.menu.Menu;

import java.util.Scanner;

public class AccessChild implements CommandWithArgs {

    @Override
    public void execute(String args) {
        System.out.println("Executing AccessChild command...");

        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        String[] parts = args.split(" ");
        if (parts.length < 2) {
            System.out.println("Invalid arguments. Usage: child <id> <n>");
            return;
        }

        String elementID = parts[0].trim();
        int childIndex = Integer.parseInt(parts[1].trim());

        XMLElement element = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (element == null) {
            System.out.println("Element with ID '" + elementID + "' not found.");
        } else {
            accessChildAtIndex(element, childIndex);
        }
    }

    @Override
    public void execute() {}

    private void accessChildAtIndex(XMLElement element, int index) {
        if (index < 1 || index > element.getChildren().size()) {
            System.out.println("Invalid child index.");
        } else {
            XMLElement child = element.getChildren().get(index - 1);
            String childValue = child.getTextContent().trim();
            System.out.println("Child at index " + index + ": <" + child.getTagName() + "> " + childValue);
        }
    }
}
