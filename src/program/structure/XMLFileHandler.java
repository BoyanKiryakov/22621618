package program.structure;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLFileHandler {

    public static XMLElement parseXML(String filePath) throws IOException {
        StringBuilder xmlContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                xmlContent.append(line).append("\n");
            }
        }
        return parseElement(xmlContent.toString());
    }

    private static XMLElement parseElement(String xml) {
        Pattern elementPattern = Pattern.compile("<(\\w+)([^>]*)>(.*?)</\\1>", Pattern.DOTALL);
        Matcher matcher = elementPattern.matcher(xml);
        if (matcher.find()) {
            String tagName = matcher.group(1);
            String attributesString = matcher.group(2).trim();
            String content = matcher.group(3);

            XMLElement element = new XMLElement(null, tagName, null);  // Initial textContent is null

            // Четене на атрибути
            Pattern attributePattern = Pattern.compile("(\\w+)=\"([^\"]*)\"");
            Matcher attrMatcher = attributePattern.matcher(attributesString);
            while (attrMatcher.find()) {
                element.setAttribute(attrMatcher.group(1), attrMatcher.group(2));
            }

            // Прочитане на вложени елементи и текст
            Matcher nestedMatcher = elementPattern.matcher(content);
            int lastIndex = 0;
            boolean hasChildren = false;
            while (nestedMatcher.find()) {
                hasChildren = true;
                if (nestedMatcher.start() > lastIndex) {
                    String textSegment = content.substring(lastIndex, nestedMatcher.start()).trim();
                    if (!textSegment.isEmpty()) {
                        element.setTextContent(textSegment);
                    }
                }
                element.addChild(parseElement(nestedMatcher.group(0)));
                lastIndex = nestedMatcher.end();
            }
            if (!hasChildren) {
                element.setTextContent(content.trim());
            } else if (lastIndex < content.length()) {
                String textSegment = content.substring(lastIndex).trim();
                if (!textSegment.isEmpty()) {
                    element.setTextContent(textSegment);
                }
            }

            return element;
        }
        return null;
    }

    // Метод за запис в определн случай
    public static void writeXMLFile(String filePath, String xmlContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(xmlContent);
            System.out.println("XML content successfully written to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to write XML content to file: " + filePath);
            e.printStackTrace();
        }
    }
}