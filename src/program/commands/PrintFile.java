package program.commands;

import program.structure.CommandHandler;
import program.structure.XMLElement;
import program.menu.Menu;

public class PrintFile implements CommandHandler {

    @Override
    public void execute() {
        printXMLContents();
    }

    public static void printXMLContents() {
        XMLElement rootElement = Menu.getRootElement();

        if (rootElement != null) {
            System.out.println("XML Content:");
            System.out.println(rootElement.toXMLString());
        } else {
            System.out.println("No XML content loaded in memory.");
        }
    }
}
