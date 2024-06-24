package program.menu;

import program.commands.*;
import program.structure.*;
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
        commands.put("xpath", new XPathCommands(""));
        commands.put("delete", new DeleteAttribute());
        commands.put("children", new ListChildren());
        commands.put("newchild", new NewChild());
        commands.put("text", new Text());
        commands.put("child", new AccessChild());
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to XML Parser!");

        while (true) {
            System.out.println("(type 'help' to see all available commands)");
            System.out.println("Enter command: ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String commandArgs = parts.length > 1 ? parts[1] : "";

            if (commands.containsKey(command)) {
                CommandHandler handler = commands.get(command);
                if (handler instanceof CommandWithArgs) {
                    ((CommandWithArgs) handler).execute(commandArgs);
                } else {
                    handler.execute();
                }
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

}
