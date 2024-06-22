package Commands;
import Structure.CommandHandler;
import Menu.Menu;

import java.io.FileWriter;
import java.io.IOException;

public class SaveFileAs implements CommandHandler {
    @Override
    public void execute() {
        System.out.println("Executing Save As command...");
        if (Menu.fileLoaded) {
            System.out.print("Enter file path to save as: ");
            String filePath = Menu.scanner.nextLine();
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(Menu.rootElement.toString());
                System.out.println("File saved as " + filePath);
            } catch (IOException e) {
                System.out.println("Error occurred while saving the file: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open.");
        }
    }
}
