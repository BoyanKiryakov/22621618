package IO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    static String currentFile = null;
    static boolean fileLoaded = false;
    static XMLElement rootElement = null;
    static Scanner scanner = new Scanner(System.in);
    static Map<String, CommandHandler> commands = new HashMap<>();

    public static void main(String[] args) {
        initializeCommands();
        displayMenu();
    }

    private static void initializeCommands() {
        commands.put("open", new OpenFile());
        commands.put("close", new CloseFile());
        commands.put("save", new SaveFile());
        commands.put("saveas", new SaveFileAs());
        commands.put("help", new Help());
        commands.put("createxml", new CreateXML());
        commands.put("exit", new Exit());
    }

    private static void displayMenu() {
        System.out.println("Welcome to XML Parser Menu");
        System.out.println("----------------------------");
        System.out.println("Available commands:");
        System.out.println("open");
        System.out.println("close");
        System.out.println("save");
        System.out.println("saveas");
        System.out.println("help");
        System.out.println("createxml");
        System.out.println("exit");
        System.out.println("----------------------------");
        System.out.print("Enter your command: ");

        handleChoice();
    }

    private static void handleChoice() {
        String input = scanner.nextLine().trim().toLowerCase();
        CommandHandler commandHandler = commands.get(input);

        if (commandHandler != null) {
            commandHandler.execute();
        } else {
            System.out.println("Invalid command. Please enter a valid command.");
        }

        displayMenu();
    }
}
