package IO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLElement {
    private String name;
    private String value;
    private List<XMLElement> children;
    private Map<String, String> attributes;

    public XMLElement(String name, String value) {
        this.name = name;
        this.value = value;
        this.children = new ArrayList<>();
        this.attributes = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addChild(XMLElement child) {
        children.add(child);
    }

    public List<XMLElement> getChildren() {
        return children;
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    @Override
    public String toString() {
        return toString(0);
    }

    private String toString(int indentLevel) {
        StringBuilder xml = new StringBuilder();
        String indent = "    ".repeat(indentLevel);

        xml.append(indent).append("<").append(name);

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            xml.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }

        if (value == null || value.isEmpty() && children.isEmpty()) {
            xml.append("/>\n");
        } else {
            xml.append(">");
            if (value != null && !value.isEmpty()) {
                xml.append(value);
            }
            if (!children.isEmpty()) {
                xml.append("\n");
                for (XMLElement child : children) {
                    xml.append(child.toString(indentLevel + 1));
                }
                xml.append(indent);
            }
            xml.append("</").append(name).append(">\n");
        }

        return xml.toString();
    }
}

