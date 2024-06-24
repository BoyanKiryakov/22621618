package program.commands;

import program.structure.CommandWithArgs;
import program.structure.XMLElement;
import program.utils.XMLElementUtils;
import program.menu.Menu;

public class SelectCommand implements CommandWithArgs {

    @Override
    public void execute() {
    }

    @Override
    public void execute(String args) {
        System.out.println("Executing Select command...");

        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        String[] parts = args.split(" ");
        if (parts.length < 2) {
            System.out.println("Please provide both element ID and attribute key.");
            return;
        }

        String elementID = parts[0];
        String attributeKey = parts[1];

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
