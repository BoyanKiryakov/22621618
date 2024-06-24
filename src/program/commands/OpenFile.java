package program.commands;

import program.structure.CommandHandler;
import program.menu.Menu;
import program.structure.XMLElement;
import program.structure.XMLFileHandler;

import java.io.File;
import java.io.IOException;

public class OpenFile implements CommandHandler {

    @Override
    public void execute() {
        String defaultFilePath = "default.xml";
        File file = new File(defaultFilePath);

        if (!file.exists()) {
            System.out.println("Default XML file does not exist: " + defaultFilePath);
            CommandHandler createXmlCommand = Menu.commands.get("createxml");
            if (createXmlCommand != null) {
                createXmlCommand.execute();
                file = new File(defaultFilePath);
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
            XMLElement rootElement = XMLFileHandler.parseXML(defaultFilePath);

            if (rootElement != null) {
                Menu.rootElement = rootElement;
                Menu.fileLoaded = true;
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
