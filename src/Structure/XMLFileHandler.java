package Structure;


import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLFileHandler {
    public static XMLElement parseXML(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder xmlContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            xmlContent.append(line.trim());
        }
        reader.close();

        return parseElement(xmlContent.toString());
    }

    private static XMLElement parseElement(String xml) {
        Pattern elementPattern = Pattern.compile("<(\\w+)([^>]*)>(.*?)</\\1>");
        Matcher matcher = elementPattern.matcher(xml);
        if (matcher.find()) {
            String tagName = matcher.group(1);
            String attributesString = matcher.group(2).trim();
            String content = matcher.group(3).trim();

            XMLElement element = new XMLElement(tagName, content);

            // Parse attributes
            Pattern attributePattern = Pattern.compile("(\\w+)=\"([^\"]*)\"");
            Matcher attrMatcher = attributePattern.matcher(attributesString);
            while (attrMatcher.find()) {
                element.setAttribute(attrMatcher.group(1), attrMatcher.group(2));
            }

            // Recursively parse child elements
            Pattern childPattern = Pattern.compile("<(\\w+)([^>]*)>(.*?)</\\1>");
            Matcher childMatcher = childPattern.matcher(content);
            while (childMatcher.find()) {
                element.addChild(parseElement(childMatcher.group(0)));
            }

            return element;
        }
        return null;
    }
    public static boolean writeXMLFile(String filePath, String xmlContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(xmlContent);
            System.out.println("XML content successfully written to file: " + filePath);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to write XML content to file: " + filePath);
            e.printStackTrace();
            return false;
        }
    }
}
