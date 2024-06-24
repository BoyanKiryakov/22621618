package program.commands;

import program.structure.CommandHandler;
import program.menu.Menu;
import program.structure.XMLElement;

import java.util.Scanner;

public class AddNewChild implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing AddNewChild command...");

        // Проверка дали е заредена XML структурата
        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Заявете ID на новия човек
        System.out.print("Enter ID for the new person: ");
        String id = scanner.nextLine().trim();

        // Проверка дали ID вече съществува, ако да, модифицирайте го
        id = generateUniqueID(id);

        // Създайте нов XMLElement за човека
        XMLElement newPerson = new XMLElement(id, "person", null);
        newPerson.setAttribute("ID", id);

        // Добавете новия човек като дете на кореновия елемент <people>
        Menu.rootElement.addChild(newPerson);

        System.out.println("New person with ID '" + id + "' added.");
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
