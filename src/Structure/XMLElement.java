package Structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLElement {
    private String name;
    private String value;
    private Map<String, String> attributes;
    private List<XMLElement> children;

    public XMLElement(String name, String value) {
        this.name = name;
        this.value = value;
        this.attributes = new HashMap<>();
        this.children = new ArrayList<>();
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

    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public String getAttribute(String key) {
        return attributes.get(key);
    }

    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder xml = new StringBuilder("<" + name);

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            xml.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }

        if (children.isEmpty() && (value == null || value.isEmpty())) {
            xml.append("/>");
        } else {
            xml.append(">\n");

            if (value != null && !value.isEmpty()) {
                xml.append(value).append("\n");
            }

            for (XMLElement child : children) {
                xml.append(child.toString());
            }

            xml.append("</").append(name).append(">");
        }

        return xml.toString();
    }
}
