package program.commands;

import program.structure.CommandHandler;
import program.structure.XMLElement;
import program.menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class XPathCommands implements CommandHandler {

    @Override
    public void execute() {
        System.out.println("Executing XPath command...");

        if (!Menu.fileLoaded || Menu.rootElement == null) {
            System.out.println("No file is currently open or no XML content found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter XPath: ");
        String xpath = scanner.nextLine().trim();

        String result = evaluateXPath(xpath);
        System.out.println("Result:\n" + result);
    }

    private String evaluateXPath(String xpath) {
        if (xpath.equals("person/address")) {
            return getAllAddresses();
        } else if (xpath.startsWith("person[0]/address")) {
            int index = extractIndex(xpath);
            return getPersonAddress(index);
        } else if (xpath.equals("person(@ID)")) {
            return getAllPersonIDs();
        } else if (xpath.startsWith("person(address=")) {
            String address = extractStringValue(xpath);
            return getNamesWithAddress(address);
        } else {
            return "Unsupported XPath query.";
        }
    }

    private int extractIndex(String xpath) {
        int startIndex = xpath.indexOf("[") + 1;
        int endIndex = xpath.indexOf("]");
        String indexStr = xpath.substring(startIndex, endIndex);
        return Integer.parseInt(indexStr);
    }

    private String extractStringValue(String xpath) {
        int startIndex = xpath.indexOf("=\"") + 2;
        int endIndex = xpath.indexOf("\")");
        return xpath.substring(startIndex, endIndex);
    }

    private String getAllAddresses() {
        List<String> addresses = new ArrayList<>();
        List<XMLElement> personElements = Menu.rootElement.getChildrenWithName("person");
        for (XMLElement person : personElements) {
            List<XMLElement> addressElements = person.getChildrenWithName("address");
            for (XMLElement addressElement : addressElements) {
                addresses.add(addressElement.getTextContent().trim());
            }
        }
        return formatListResult(addresses);
    }

    private String getPersonAddress(int index) {
        List<XMLElement> personElements = Menu.rootElement.getChildrenWithName("person");
        if (index < personElements.size()) {
            XMLElement person = personElements.get(index);
            List<XMLElement> addressElements = person.getChildrenWithName("address");
            if (!addressElements.isEmpty()) {
                return addressElements.get(0).getTextContent().trim();
            }
        }
        return "Address not found.";
    }

    private String getAllPersonIDs() {
        List<String> ids = new ArrayList<>();
        List<XMLElement> personElements = Menu.rootElement.getChildrenWithName("person");
        for (XMLElement person : personElements) {
            String id = person.getAttribute("ID");
            if (id != null) {
                ids.add(id.trim());
            }
        }
        return formatListResult(ids);
    }

    private String getNamesWithAddress(String address) {
        List<String> names = new ArrayList<>();
        List<XMLElement> personElements = Menu.rootElement.getChildrenWithName("person");
        for (XMLElement person : personElements) {
            List<XMLElement> addressElements = person.getChildrenWithName("address");
            for (XMLElement addressElement : addressElements) {
                if (addressElement.getTextContent().trim().equals(address)) {
                    List<XMLElement> nameElements = person.getChildrenWithName("name");
                    for (XMLElement nameElement : nameElements) {
                        names.add(nameElement.getTextContent().trim());
                    }
                }
            }
        }
        return formatListResult(names);
    }

    private String formatListResult(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String item : list) {
            result.append(item).append("\n");
        }
        return result.toString().trim();
    }
}