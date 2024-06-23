package Utils;

import Structure.XMLElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLElementUtils {

    public static XMLElement findElementByID(XMLElement root, String id) {
        if (root == null) {
            return null;
        }

        // Check if the current element matches the desired ID using attribute
        if (id.equals(root.getAttribute("ID"))) {
            return root;
        }

        // Recursively search through child elements
        for (XMLElement child : root.getChildren()) {
            XMLElement found = findElementByID(child, id);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    public static boolean updateChildElement(XMLElement element, String childTagName, String newValue) {
        // Iterate through children elements
        for (XMLElement child : element.getChildren()) {
            // Check if the current child element matches the tag name
            if (child.getName().equalsIgnoreCase(childTagName)) {
                // Update the text content of the child element
                child.setTextContent(newValue);
                return true; // Return true if successfully updated
            }
        }
        return false; // Return false if child element not found or updated
    }

    public static String extractAttributeFromElement(String xmlContent, String attributeKey) {
        Pattern pattern = Pattern.compile("<person\\s+ID=['\"](\\d+)['\"]>(.*?)</person>");
        Matcher matcher = pattern.matcher(xmlContent);
        if (matcher.find()) {
            String id = matcher.group(1);
            if (id.equals(attributeKey)) {
                String personContent = matcher.group(2);
                return extractTagContent(personContent, attributeKey);
            }
        }
        return ""; // Return empty string if attribute key not found in the XML content
    }

    public static String extractTagContent(String xmlContent, String tagName) {
        Pattern pattern = Pattern.compile("<" + tagName + ">(.*?)</" + tagName + ">");
        Matcher matcher = pattern.matcher(xmlContent);
        if (matcher.find()) {
            return matcher.group(1).trim(); // Extract and trim tag content
        } else {
            return ""; // Return empty string if tag not found
        }
    }
}
