package Commands;

import Structure.CommandHandler;
import Menu.Menu;
import Structure.XMLElement;

import java.util.ArrayList;
import java.util.List;

public class XPathCommands implements CommandHandler {
    @Override
    public void execute() {
        System.out.print("Enter element ID: ");
        String id = Menu.scanner.nextLine().trim();
        System.out.print("Enter XPath query: ");
        String xPath = Menu.scanner.nextLine().trim();

        XMLElement element = Menu.rootElement.findElementById(id);
        if (element != null) {
            List<XMLElement> result = evaluateXPath(element, xPath);
            System.out.println("XPath query result:");
            for (XMLElement elem : result) {
                System.out.println(elem.toXMLString());
            }
        } else {
            System.out.println("Element with ID '" + id + "' not found.");
        }
    }

    private List<XMLElement> evaluateXPath(XMLElement element, String xPath) {
        List<XMLElement> result = new ArrayList<>();

        String[] parts = xPath.split("/");
        for (String part : parts) {
            if (part.contains("[")) {
                String tagName = part.substring(0, part.indexOf("["));
                int index = Integer.parseInt(part.substring(part.indexOf("[") + 1, part.indexOf("]")));
                result.add(element.getChildren().get(index));
            } else if (part.contains("@")) {
                String attributeName = part.substring(part.indexOf("@") + 1);
                result.add(new XMLElement("attribute",null, element.getAttribute(attributeName)));
            } else if (part.contains("=")) {
                String[] comparison = part.split("=");
                String tagName = comparison[0];
                String value = comparison[1].replace("\"", "");
                for (XMLElement child : element.getChildren()) {
                    if (child.getTextContent().equals(value)) {
                        result.add(child);
                    }
                }
            } else {
                for (XMLElement child : element.getChildren()) {
                    if (child.getName().equals(part)) {
                        result.add(child);
                    }
                }
            }
        }
        return result;
    }
}
