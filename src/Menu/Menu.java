package Menu;

        import Commands.*;
        import Structure.CommandHandler;
        import Structure.XMLElement;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.Scanner;

public class Menu {
    public static String currentFile = null;
    public static boolean fileLoaded = false;
    public static XMLElement rootElement = null;
    public static Scanner scanner = new Scanner(System.in);
    static Map<String, CommandHandler> commands = new HashMap<>();

    public static void main(String[] args) {
        initializeCommands();
        displayMenu();
    }

    private static void initializeCommands() {
        commands.put("open", new OpenFile());
        commands.put("close", new CloseFile());
        commands.put("print", new PrintFile());
        commands.put("save", new SaveFile());
        commands.put("saveas", new SaveFileAs());
        commands.put("help", new Help());
        commands.put("createxml", new CreateXML());
        commands.put("exit", new Exit());
        commands.put("select", new SelectCommand());
        commands.put("set", new SetCommand());
        commands.put("children", new ListChildren());
        commands.put("child", new AccessChild());
        commands.put("text", new AccessText());
        commands.put("delete", new DeleteAttribute());
        commands.put("newchild", new AddNewChild());
    }

    private static void displayMenu() {
        System.out.println("Welcome to XML Parser Menu");
        System.out.println("----------------------------");
        System.out.println("Available commands:");
        System.out.println("open");
        System.out.println("close");
        System.out.println("print");
        System.out.println("save");
        System.out.println("saveas");
        System.out.println("help");
        System.out.println("createxml");
        System.out.println("exit");
        System.out.println("select <id> <key>");
        System.out.println("set <id> <key> <value>");
        System.out.println("children <id>");
        System.out.println("child <id> <n>");
        System.out.println("text <id>");
        System.out.println("delete <id> <key>");
        System.out.println("newchild <id>");

        System.out.println("----------------------------");
        System.out.print("Enter your command: ");

        handleChoice();
    }

    private static void handleChoice() {
        String input = scanner.nextLine().trim().toLowerCase();
        CommandHandler commandHandler = commands.get(input.split(" ")[0]);

        if (commandHandler != null) {
            commandHandler.execute();
        } else {
            System.out.println("Invalid command. Please enter a valid command.");
        }

        displayMenu();
    }
}
