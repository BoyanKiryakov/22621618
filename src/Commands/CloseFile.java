package Commands;

import Structure.CommandHandler;
import Menu.Menu;

public class CloseFile implements CommandHandler {
    @Override
    public void execute() {
        if (Menu.fileLoaded) {
            Menu.currentFile = null;
            Menu.rootElement = null;
            Menu.fileLoaded = false;
            System.out.println("Successfully closed the file.");
        } else {
            System.out.println("No file is currently open.");
        }
    }
}
