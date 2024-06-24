package Structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLElement {
    private String name;
    private String textContent;
    private Map<String, String> attributes;
    private List<XMLElement> children;
    private String tagName;

    public XMLElement(String name, String tagName, String textContent) {
        this.name = name;
        this.tagName = tagName;
        this.textContent = textContent;
        this.attributes = new HashMap<>();
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<XMLElement> getChildren() {
        return children;
    }

    public void setChildren(List<XMLElement> children) {
        this.children = children;
    }

    public void addChild(XMLElement child) {
        this.children.add(child);
    }

    public void removeChild(XMLElement child) {
        this.children.remove(child);
    }

    public void setAttribute(String key, String value) {
        this.attributes.put(key, value);
    }

    public String getAttribute(String key) {
        return this.attributes.get(key);
    }

    public XMLElement findElementById(String id) {
        return findElementByIdHelper(this, id);
    }

    private XMLElement findElementByIdHelper(XMLElement element, String id) {
        if (element.getAttribute("ID") != null && element.getAttribute("ID").equals(id)) {
            return element;
        }
        for (XMLElement child : element.getChildren()) {
            XMLElement found = findElementByIdHelper(child, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public String toXMLString() {
        StringBuilder sb = new StringBuilder();
        toXMLStringHelper(sb, this, 0);
        return sb.toString();
    }

    private void toXMLStringHelper(StringBuilder sb, XMLElement element, int indent) {
        addIndentation(sb, indent);
        sb.append("<").append(element.getTagName());

        for (Map.Entry<String, String> entry : element.getAttributes().entrySet()) {
            sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }

        if (element.getChildren().isEmpty() && (element.getTextContent() == null || element.getTextContent().isEmpty())) {
            sb.append("/>\n");
        } else {
            sb.append(">");

            if (element.getTextContent() != null && !element.getTextContent().isEmpty()) {
                sb.append(element.getTextContent());
            }

            if (!element.getChildren().isEmpty()) {
                sb.append("\n");
                for (XMLElement child : element.getChildren()) {
                    toXMLStringHelper(sb, child, indent + 1);
                }
                addIndentation(sb, indent);
            }

            sb.append("</").append(element.getTagName()).append(">\n");
        }
    }

    private void addIndentation(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append("\t");
        }
    }
}
