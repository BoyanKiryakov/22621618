package program.commands;

import program.structure.CommandHandler;

public class Help implements CommandHandler {
    @Override
    public void execute() {
        System.out.println("Available commands:");
        System.out.println("open - Open a file");
        System.out.println("close - Close the current file");
        System.out.println("save - Save changes to the current file");
        System.out.println("saveas - Save changes to a different file");
        System.out.println("print - Print the current XML file");
        System.out.println("set <id> <key> <value> - Set an attribute for an element");
        System.out.println("select <id> <key> - Select an attribute value for an element");
        System.out.println("xpath <id> <XPath> - Evaluate an XPath expression on an element");
        System.out.println("delete <id> <key> - Delete an attribute from an element");
        System.out.println("children <id> - List children of an element");
        System.out.println("newchild <id> - Add a new child element");
        System.out.println("text <id> - Access text content of an element");
        System.out.println("child <id> <n> - Access a specific child element");
        System.out.println("help - Display help information");
        System.out.println("createxml - Create a new XML file");
        System.out.println("exit - Exit the program");
    }
}
