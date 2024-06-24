package program.utils;

import program.structure.XMLElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLElementUtils {

    //Метод за намиране на ID
    public static XMLElement findElementByID(XMLElement root, String id) {
        if (root == null) {
            return null;
        }

        if (id.equals(root.getAttribute("ID"))) {
            return root;
        }

        for (XMLElement child : root.getChildren()) {
            XMLElement found = findElementByID(child, id);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    // Метод за намиране на текст на атрибут
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
