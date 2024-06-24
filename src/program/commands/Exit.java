package program.commands;

import program.structure.CommandHandler;

public class Exit implements CommandHandler {
    @Override
    public void execute() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
