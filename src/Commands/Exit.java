package Commands;

import Structure.CommandHandler;

public class Exit implements CommandHandler {
    @Override
    public void execute() {
        System.out.println("Executing Exit command...");
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
