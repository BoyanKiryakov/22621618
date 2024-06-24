package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;
import Structure.XMLFileHandler;

import java.io.File;
import java.io.IOException;

public class OpenFile implements CommandHandler {

    @Override
    public void execute() {
        String defaultFilePath = "default.xml";
        File file = new File(defaultFilePath);

        if (!file.exists()) {
            System.out.println("Default XML file does not exist: " + defaultFilePath);
            // Call the CreateXML command handler to create a new default.xml file
            CommandHandler createXmlCommand = Menu.commands.get("createxml");
            if (createXmlCommand != null) {
                createXmlCommand.execute(); // Execute the CreateXML command
                // Now attempt to load the file again
                file = new File(defaultFilePath); // Update the file reference after creation
                if (!file.exists()) {
                    System.out.println("Failed to create XML file: " + defaultFilePath);
                    return;
                }
            } else {
                System.out.println("CreateXML command not found. Cannot create XML file.");
                return;
            }
        }

        try {
            // Parse the XML file
            XMLElement rootElement = XMLFileHandler.parseXML(defaultFilePath);

            if (rootElement != null) {
                Menu.rootElement = rootElement;  // Store the root element in Menu for access throughout the program
                Menu.fileLoaded = true;          // Mark that a file is successfully loaded
                Menu.currentFile = defaultFilePath;
                System.out.println("File opened successfully: " + defaultFilePath);
            } else {
                System.out.println("Failed to load XML structure from file: " + defaultFilePath);
            }
        } catch (IOException e) {
            System.out.println("Error reading XML file: " + e.getMessage());
        }
    }
}
