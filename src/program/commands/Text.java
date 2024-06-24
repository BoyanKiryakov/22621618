package program.commands;

import program.structure.CommandWithArgs;
import program.structure.XMLElement;
import program.utils.XMLElementUtils;
import program.menu.Menu;

import java.util.Scanner;

public class Text implements CommandWithArgs {

    @Override
    public void execute(String args) {
        System.out.println("Executing AccessText command...");

        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        String elementID = args.trim();

        XMLElement element = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (element == null) {
            System.out.println("Element with ID '" + elementID + "' not found.");
        } else {
            System.out.println("Text content of element with ID '" + elementID + "':");
            collectTextContent(element);
        }
    }

    @Override
    public void execute() {
    }

    private void collectTextContent(XMLElement element) {
        if (element.getTextContent() != null && !element.getTextContent().isEmpty()) {
            System.out.println(element.getTextContent().trim());
        }

        for (XMLElement child : element.getChildren()) {
            collectTextContent(child);
        }
    }
}
