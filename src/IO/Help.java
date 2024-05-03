package IO;

public class Help implements CommandHandler{
    @Override
    public void execute() {
        System.out.println("Executing Help command...");
        System.out.println("The following commands are supported:");
        System.out.println("1. Open - Open a file");
        System.out.println("2. Close - Close the current file");
        System.out.println("3. Save - Save changes to the current file");
        System.out.println("4. Save As - Save changes to a different file");
        System.out.println("5. Help - Display help information");
        System.out.println("6. Exit - Exit the program");
    }
}
