package IO;
import java.io.FileWriter;
import java.io.IOException;
public class SaveFile implements CommandHandler{
    @Override
    public void execute() {
        System.out.println("Executing Save command...");
        if (Menu.fileLoaded) {
            try (FileWriter writer = new FileWriter(Menu.currentFile)) {
                writer.write("Sample XML content");
                System.out.println("File saved successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while saving the file: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open.");
        }
    }
}
