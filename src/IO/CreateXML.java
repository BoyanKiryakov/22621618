package IO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreateXML implements CommandHandler {

    private Map<String, Integer> idCountMap = new HashMap<>();

    @Override
    public void execute() {
        System.out.println("Executing Create XML command...");

        if (Menu.rootElement == null) {
            Menu.rootElement = new XMLElement("people", "");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter person ID (or 'done' to finish): ");
            String personID = scanner.nextLine().trim();
            if (personID.equalsIgnoreCase("done")) {
                break;
            }

            // Handle unique ID logic
            String uniqueID = generateUniqueID(personID);

            System.out.print("Enter person name: ");
            String personName = scanner.nextLine().trim();

            System.out.print("Enter person age: ");
            String personAge = scanner.nextLine().trim();

            System.out.print("Enter person address: ");
            String personAddress = scanner.nextLine().trim();

            XMLElement personElement = new XMLElement("person", "");
            personElement.addAttribute("ID", uniqueID);
            personElement.addChild(new XMLElement("name", personName));
            personElement.addChild(new XMLElement("age", personAge));
            personElement.addChild(new XMLElement("address", personAddress));

            Menu.rootElement.addChild(personElement);
        }

        System.out.println("XML content updated in memory.");
    }

    private String generateUniqueID(String baseID) {
        if (baseID == null || baseID.isEmpty()) {
            baseID = "generatedID";
        }

        if (!idCountMap.containsKey(baseID)) {
            idCountMap.put(baseID, 0);
            return baseID;
        } else {
            int count = idCountMap.get(baseID) + 1;
            idCountMap.put(baseID, count);
            return baseID + "_" + count;
        }
    }
}
