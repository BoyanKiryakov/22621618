package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenFile implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Open command...");

        String defaultDirectory = System.getProperty("user.dir");
        String defaultFilePath = defaultDirectory + File.separator + "default.xml";

        File file = new File(defaultFilePath);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("New file created: " + defaultFilePath);
                } else {
                    System.out.println("Failed to create new file: " + defaultFilePath);
                    return;
                }
            }

            String fileContent = readFromFile(defaultFilePath);
            if (fileContent != null) {
                System.out.println("File opened successfully.");
                System.out.println("File content:");
                System.out.println(fileContent);
                Menu.currentFile = defaultFilePath;
                Menu.fileLoaded = true;
            } else {
                System.out.println("Failed to read file content: " + defaultFilePath);
                Menu.currentFile = null;
                Menu.fileLoaded = false;
            }

        } catch (IOException e) {
            System.out.println("Error creating/opening the file: " + e.getMessage());
            Menu.currentFile = null;
            Menu.fileLoaded = false;
        }
    }

    private String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
}
