package Menu;

import Commands.*;
import Structure.CommandHandler;
import Structure.XMLElement;
import Structure.XMLFileHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static Map<String, CommandHandler> commands = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);
    public static boolean fileLoaded = false;
    public static String currentFile;
    public static XMLElement rootElement;

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
                    updateXmlContent(); // Update XML content after specific commands
                }
            } else {
                System.out.println("Invalid command. Type 'help' for a list of commands.");
            }
        }
    }

    public static void updateXmlContent() {
        // Example implementation assuming rootElement is where XML structure is stored
        if (fileLoaded && rootElement != null) {
            // Perform operations to update XML content in memory
            // Example: rootElement.setContent(updatedContent);
            System.out.println("XML content updated in memory.");
        } else {
            System.out.println("No file loaded or root element is null.");
        }
    }

    private static void printMenu() {
        System.out.println("\nAvailable commands:");
        System.out.println("open - Open a file");
        System.out.println("close - Close the current file");
        System.out.println("save - Save changes to the current file");
        System.out.println("saveas - Save changes to a different file");
        System.out.println("print - Print the current XML file");
        System.out.println("set - Set an attribute for an element");
        System.out.println("select - Select an attribute value for an element");
        System.out.println("xpath - Evaluate an XPath expression on an element");
        System.out.println("delete - Delete an attribute from an element");
        System.out.println("listchildren - List children of an element");
        System.out.println("addchild - Add a new child element");
        System.out.println("accesstext - Access text content of an element");
        System.out.println("accesschild - Access a specific child element");
        System.out.println("help - Display help information");
        System.out.println("createxml - Create a new XML file");
        System.out.println("exit - Exit the program");
        System.out.print("\nEnter command: ");
    }
}
