package IO;

import java.io.File;

public class CreateXML implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Create XML command...");
        String defaultDirectory = System.getProperty("user.dir");
        String defaultFilePath = defaultDirectory + File.separator + "default.xml";
        XMLFileHandler.createXMLFileInteractive(defaultFilePath);

        Menu.rootElement = XMLFileHandler.parseXML(defaultFilePath);
        if (Menu.rootElement != null) {
            System.out.println("XML content loaded into memory.");
        } else {
            System.out.println("Failed to load XML content into memory.");
        }
    }
}
