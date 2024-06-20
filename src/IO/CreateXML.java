package IO;

import java.util.Scanner;

public class CreateXML implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing Create XML command...");
        Menu.rootElement = new XMLElement("people", "");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter person ID (or 'done' to finish): ");
            String personID = scanner.nextLine().trim();
            if (personID.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter person name: ");
            String personName = scanner.nextLine().trim();

            System.out.print("Enter person age: ");
            String personAge = scanner.nextLine().trim();

            System.out.print("Enter person address: ");
            String personAddress = scanner.nextLine().trim();

            XMLElement personElement = new XMLElement("person", "");
            personElement.addAttribute("ID", personID);
            personElement.addChild(new XMLElement("name", personName));
            personElement.addChild(new XMLElement("age", personAge));
            personElement.addChild(new XMLElement("address", personAddress));

            Menu.rootElement.addChild(personElement);
        }

        System.out.println("XML content created in memory.");
    }
}
