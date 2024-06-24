package program.commands;

import program.structure.CommandWithArgs;
import program.structure.XMLElement;
import program.utils.XMLElementUtils;
import program.menu.Menu;

import java.util.Scanner;

public class NewChild implements CommandWithArgs {

    @Override
    public void execute(String args) {
        System.out.println("Executing NewChild command...");

        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        String id = args.trim();

        id = generateUniqueID(id);

        XMLElement newPerson = new XMLElement(id, "person", null);
        newPerson.setAttribute("ID", id);

        Menu.rootElement.addChild(newPerson);

        System.out.println("New person with ID '" + id + "' added.");
        Menu.updateXmlContent();
    }

    @Override
    public void execute() {
    }

    private String generateUniqueID(String id) {
        int counter = 1;
        String newID = id;

        while (Menu.rootElement.findElementById(newID) != null) {
            newID = id + "_" + counter;
            counter++;
        }

        return newID;
    }
}
