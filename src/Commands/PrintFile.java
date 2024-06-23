package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

public class PrintFile implements CommandHandler {
    @Override
    public void execute() {
        System.out.println("Executing Print command...");

        // Check if a file is currently loaded
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        // Print the XML structure from the root element
        String xmlString = Menu.rootElement.toXMLString();
        System.out.println(xmlString);
    }
}
