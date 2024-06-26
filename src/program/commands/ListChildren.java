package program.commands;

import program.structure.CommandWithArgs;
import program.structure.XMLElement;
import program.utils.XMLElementUtils;
import program.menu.Menu;

import java.util.Scanner;

public class ListChildren implements CommandWithArgs {

    @Override
    public void execute(String args) {
        System.out.println("Executing ListChildren command...");

        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        String elementID = args.trim();

        XMLElement element = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (element == null) {
            System.out.println("Element with ID '" + elementID + "' not found.");
        } else {
            listChildren(element);
        }
    }

    private void listChildren(XMLElement element) {
        if (element.getChildren().isEmpty()) {
            System.out.println("Element with ID '" + element.getAttribute("ID") + "' has no children.");
        } else {
            System.out.println("Children of element with ID '" + element.getAttribute("ID") + "':");
            for (XMLElement child : element.getChildren()) {
                System.out.println("\t<" + child.getTagName() + ">");
            }
        }
    }

    @Override
    public void execute() {
    }
}
