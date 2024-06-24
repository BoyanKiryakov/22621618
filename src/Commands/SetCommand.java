package Commands;

import Structure.CommandHandler;
import Structure.XMLElement;
import Structure.XMLFileHandler;
import Utils.XMLElementUtils;
import Menu.Menu;

import java.util.Scanner;

public class SetCommand implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Set command...");

        // Check if XML structure is loaded
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Ask for element ID
        System.out.print("Enter element ID: ");
        String elementID = scanner.nextLine().trim();

        // Find the element to update
        XMLElement elementToUpdate = XMLElementUtils.findElementByID(Menu.rootElement, elementID);

        if (elementToUpdate != null) {
            // Ask for child element to edit
            System.out.print("Enter child element to edit (e.g., name, age, address): ");
            String childElementName = scanner.nextLine().trim();

            // Find the child element to edit by tag name
            XMLElement childElement = findChildElementByName(elementToUpdate, childElementName);

            if (childElement != null) {
                // Ask for new value
                System.out.print("Enter new value: ");
                String newValue = scanner.nextLine().trim();

                // Update the text content of the child element
                childElement.setTextContent(newValue);

                // After updating the element, re-populate the XML content
                String updatedContent = Menu.rootElement.toXMLString();

                // Update the XML content in memory using Menu.updateXmlContent()
                Menu.updateXmlContent();

                // Save the updated XML content to file
                XMLFileHandler.writeXMLFile(Menu.currentFile, updatedContent);
                System.out.println("Child element '" + childElementName + "' updated successfully.");
            } else {
                System.out.println("Child element '" + childElementName + "' not found in element with ID '" + elementID + "'.");
            }
        } else {
            System.out.println("Element with ID '" + elementID + "' not found.");
        }
    }

    /**
     * Helper method to find a child element by its tag name.
     *
     * @param parentElement The parent XMLElement in which to search for the child.
     * @param tagName       The tag name of the child element to find.
     * @return The XMLElement representing the child element if found, otherwise null.
     */
    private XMLElement findChildElementByName(XMLElement parentElement, String tagName) {
        for (XMLElement child : parentElement.getChildren()) {
            if (child.getTagName().equalsIgnoreCase(tagName)) {
                return child;
            }
        }
        return null;
    }
}
