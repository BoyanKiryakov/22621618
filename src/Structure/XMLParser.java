package Structure;

import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    public static XMLElement parse(String xml) {
        XMLParser parser = new XMLParser();
        return parser.parseElement(xml);
    }

    private int currentIndex;
    private String xml;

    private XMLElement parseElement(String xml) {
        this.xml = xml;
        currentIndex = 0;
        return parseElement();
    }

    private XMLElement parseElement() {
        skipWhitespace();
        if (xml.charAt(currentIndex) != '<') {
            return null; // Not a valid XML element
        }

        currentIndex++;
        String elementName = parseElementName();
        XMLElement element = new XMLElement(elementName, null, null);

        parseAttributes(element);
        if (xml.charAt(currentIndex) == '>') {
            currentIndex++;
            parseChildrenAndText(element);
        }

        if (xml.charAt(currentIndex) == '<' && xml.charAt(currentIndex + 1) == '/') {
            parseEndTag();
        }

        return element;
    }

    private String parseElementName() {
        int start = currentIndex;
        while (currentIndex < xml.length() && !Character.isWhitespace(xml.charAt(currentIndex)) && xml.charAt(currentIndex) != '>') {
            currentIndex++;
        }
        return xml.substring(start, currentIndex);
    }

    private void parseAttributes(XMLElement element) {
        while (xml.charAt(currentIndex) != '>' && xml.charAt(currentIndex) != '/') {
            skipWhitespace();
            if (xml.charAt(currentIndex) == '/') {
                break;
            }

            int attrNameStart = currentIndex;
            while (xml.charAt(currentIndex) != '=' && xml.charAt(currentIndex) != '>' && !Character.isWhitespace(xml.charAt(currentIndex))) {
                currentIndex++;
            }

            String attrName = xml.substring(attrNameStart, currentIndex);
            skipWhitespace();
            int attrValueStart = currentIndex;

            if (xml.charAt(currentIndex) == '=') {
                currentIndex++;
                skipWhitespace();
                if (xml.charAt(currentIndex) == '"') {
                    currentIndex++;
                    int attrValueEnd = xml.indexOf('"', currentIndex);
                    String attrValue = xml.substring(attrValueStart + 1, attrValueEnd);
                    element.setAttribute(attrName, attrValue);
                    currentIndex = attrValueEnd + 1;
                } else {
                    currentIndex++;
                }
            }
        }
    }

    private void parseChildrenAndText(XMLElement element) {
        StringBuilder textContent = new StringBuilder();

        while (currentIndex < xml.length()) {
            skipWhitespace();

            if (xml.charAt(currentIndex) == '<') {
                if (xml.charAt(currentIndex + 1) == '/') {
                    break; // End of current element
                } else {
                    XMLElement child = parseElement();
                    if (child != null) {
                        element.addChild(child);
                    }
                }
            } else {
                int textStart = currentIndex;
                while (currentIndex < xml.length() && xml.charAt(currentIndex) != '<') {
                    currentIndex++;
                }
                String text = xml.substring(textStart, currentIndex).trim();
                textContent.append(text);
            }
        }

        element.setTextContent(textContent.toString().trim());
    }

    private void parseEndTag() {
        currentIndex = xml.indexOf('>', currentIndex);
        if (currentIndex != -1) {
            currentIndex++;
        }
    }

    private void skipWhitespace() {
        while (currentIndex < xml.length() && Character.isWhitespace(xml.charAt(currentIndex))) {
            currentIndex++;
        }
    }
}
