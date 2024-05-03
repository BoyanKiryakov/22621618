package IO;

import IO.*;

import java.util.Scanner;


public class Menu {
    static String currentFile = null;
    static boolean fileLoaded = false;
    static Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        System.out.println("\n\n\nWelcome to XML Parser IO.Menu");
        System.out.println("----------------------------");
        System.out.println("1. Open");
        System.out.println("2. Close");
        System.out.println("3. Save");
        System.out.println("4. Save As");
        System.out.println("5. Help");
        System.out.println("6. Exit");
        System.out.println("----------------------------");
        System.out.print("Enter your choice: ");

        Command choice = getCommand();
        handleChoice(choice);
    }

    private static Command getCommand() {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > Command.values().length) {
                    throw new IllegalArgumentException();
                }
                return Command.values()[choice - 1];
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid command number.");
                System.out.print("Enter your choice: ");
            }
        }
    }

    private static void handleChoice(Command choice) {
        CommandHandler commandHandler = null;
        switch (choice) {
            case OPEN:
                commandHandler = new OpenFile();
                break;
            case CLOSE:
                commandHandler = new CloseFile();
                break;
            case SAVE:
                commandHandler = new SaveFile();
                break;
            case SAVE_AS:
                commandHandler = new SaveFileAs();
                break;
            case HELP:
                commandHandler = new Help();
                break;
            case EXIT:
                commandHandler = new Exit();
                break;
        }
        if (commandHandler != null) {
            commandHandler.execute();
        }
        displayMenu();
    }
}