package IO;

import java.io.File;

public class CreateXML implements CommandHandler{
    @Override
    public void execute() {
        System.out.println("Executing Create XML command...");
        String defaultDirectory = System.getProperty("user.dir");
        String defaultFilePath = defaultDirectory + File.separator + "output.xml";
        XMLFileHandler.createXMLFileInteractive(defaultFilePath);
    }
}
