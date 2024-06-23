package Commands;

import Structure.CommandHandler;
import Menu.Menu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFileAs implements CommandHandler {
    @Override
    public void execute() {
        if (Menu.fileLoaded && Menu.rootElement != null) {
            System.out.print("Enter new file path: ");
            String filePath = Menu.scanner.nextLine().trim();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(Menu.rootElement.toXMLString());
                System.out.println("File saved successfully as " + filePath);
                Menu.currentFile = filePath;
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open or no XML content found.");
        }
    }
}
