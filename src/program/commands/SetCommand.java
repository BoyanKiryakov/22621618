package program.commands;

import program.structure.CommandWithArgs;
import program.structure.XMLElement;
import program.structure.XMLFileHandler;
import program.utils.XMLElementUtils;
import program.menu.Menu;

public class SetCommand implements CommandWithArgs {

    @Override
    public void execute(String args) {
        System.out.println("Executing Set command...");
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        String[] parts = args.split(" ", 3);
        if (parts.length < 3) {
            System.out.println("Please provide element ID, child element name, and new value.");
            return;
        }

        String elementID = parts[0];
        String childElementName = parts[1];
        String newValue = parts[2];

        XMLElement elementToUpdate = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (elementToUpdate != null) {
            XMLElement childElement = findChildElementByName(elementToUpdate, childElementName);

            if (childElement != null) {
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

    @Override
    public void execute() {
    }
}
