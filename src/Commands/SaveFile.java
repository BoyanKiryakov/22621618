package Commands;

import Structure.CommandHandler;
import Menu.Menu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile implements CommandHandler {
    @Override
    public void execute() {
        System.out.println("Executing Save command...");
        if (Menu.fileLoaded && Menu.rootElement != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Menu.currentFile))) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write(Menu.rootElement.toString());
                System.out.println("File saved successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while saving the file: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open or no XML content found.");
        }
    }
}
