package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.Scanner;

public class AddNewChild implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing AddNewChild command...");

        // Check if XML structure is loaded
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Ask for the ID of the new person
        System.out.print("Enter ID for the new person: ");
        String id = scanner.nextLine().trim();

        // Create a new XMLElement for the person
        XMLElement newPerson = new XMLElement("person", null);
        newPerson.setAttribute("ID", id);

        // Add the new person as a child to the root <people> element
        Menu.rootElement.addChild(newPerson);

        System.out.println("New person with ID '" + id + "' added.");
    }
}
