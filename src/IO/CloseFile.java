package IO;

public class CloseFile implements CommandHandler{
    @Override
    public void execute() {
        System.out.println("Executing Close command...");
        if (Menu.fileLoaded) {
            Menu.currentFile = null;
            Menu.fileLoaded = false;
            System.out.println("File closed.");
        } else {
            System.out.println("No file is currently open.");
        }
    }
}
