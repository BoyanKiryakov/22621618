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
    }
}
