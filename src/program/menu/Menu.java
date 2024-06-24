package program.menu;

import program.commands.*;
import program.structure.CommandHandler;
import program.structure.XMLElement;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static Map<String, CommandHandler> commands = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);
    public static boolean fileLoaded = false;
    public static String currentFile;
    public static XMLElement rootElement;

    public static void setRootElement(XMLElement element) {
        rootElement = element;
    }

    public static XMLElement getRootElement() {
        return rootElement;
    }

    static {
        commands.put("open", new OpenFile());
        commands.put("close", new CloseFile());
        commands.put("save", new SaveFile());
        commands.put("saveas", new SaveFileAs());
        commands.put("help", new Help());
        commands.put("createxml", new CreateXML());
        commands.put("exit", new Exit());
        commands.put("print", new PrintFile());
        commands.put("set", new SetCommand());
        commands.put("select", new SelectCommand());
        commands.put("xpath", new XPathCommands());
        commands.put("delete", new DeleteAttribute());
        commands.put("listchildren", new ListChildren());
        commands.put("addchild", new AddNewChild());
        commands.put("accesstext", new AccessText());
        commands.put("accesschild", new AccessChild());
    }

    public static void main(String[] args) {
        System.out.println("Welcome to XML Parser!");

        while (true) {
            printMenu();
            String command = scanner.nextLine().trim();

            if (commands.containsKey(command)) {
                commands.get(command).execute();
                if (command.equals("save") || command.equals("saveas") || command.equals("set") || command.equals("delete") || command.equals("addchild")) {
                    updateXmlContent();
                }
            } else {
                System.out.println("Invalid command. Type 'help' for a list of commands.");
            }
        }
    }

    public static void updateXmlContent() {
        if (fileLoaded && rootElement != null) {
            System.out.println("XML content updated in memory.");
        } else {
            System.out.println("No file loaded or root element is null.");
        }
    }

    private static void printMenu() {
        System.out.println("\nAvailable commands:\n" +
                "open\n" +
                "close\n" +
                "save\n" +
                "saveAs\n" +
                "print\n" +
                "set\n" +
                "select\n" +
                "xpath\n" +
                "delete\n" +
                "listchildren\n" +
                "addchild\n" +
                "accesstext\n" +
                "accesschild\n" +
                "help\n" +
                "createxml\n" +
                "exit");
        System.out.print("\nEnter command: ");
    }
}
