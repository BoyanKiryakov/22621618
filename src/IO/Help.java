package IO;

public class Help implements CommandHandler{
    @Override
    public void execute() {
        System.out.println("Executing Help command...");
        System.out.println("The following commands are supported:");
        System.out.println("open - Open a file");
        System.out.println("close - Close the current file");
        System.out.println("save - Save changes to the current file");
        System.out.println("saveas - Save changes to a different file");
        System.out.println("help - Display help information");
        System.out.println("createxml - Create an XML file");
        System.out.println("exit - Exit the program\n\n");
    }
}
