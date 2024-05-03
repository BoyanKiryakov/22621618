package IO;

import java.io.File;
import java.io.IOException;

public class OpenFile implements CommandHandler{
    @Override
    public void execute() {
        System.out.println("Executing Open command...");
        String defaultDirectory = System.getProperty("user.dir");
        String defaultFilePath = defaultDirectory + File.separator + "default.xml";
        File file = new File(defaultFilePath);
        try {
            if (file.createNewFile()) {
                System.out.println("New file created: " + defaultFilePath);
            } else {
                System.out.println("File already exists: " + defaultFilePath);
            }
            Menu.currentFile = defaultFilePath;
            Menu.fileLoaded = true;
            System.out.println("File opened successfully.");
        } catch (IOException e) {
            System.out.println("Error creating/opening the file: " + e.getMessage());
        }
    }
}
