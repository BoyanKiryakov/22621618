package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PrintFile implements CommandHandler {

    @Override
    public void execute() {
        if (Menu.currentFile != null && !Menu.currentFile.isEmpty()) {
            printFileContents(Menu.currentFile);
        } else {
            System.out.println("No file is currently open.");
        }
    }

    public void printFileContents(String filePath) {
        System.out.println("Printing file contents...");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
