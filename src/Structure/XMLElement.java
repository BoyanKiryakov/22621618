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

    public XMLElement(String name, String textContent) {
        this.name = name;
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
        toXMLStringHelper(sb, 0);
        return sb.toString();
    }

    private void toXMLStringHelper(StringBuilder sb, int depth) {
        sb.append("\t".repeat(depth));
        sb.append("<").append(element.getName());

        // Append attributes
        for (String key : element.getAttributes().keySet()) {
            sb.append(" ").append(key).append("=\"").append(element.getAttribute(key)).append("\"");
        }

        if (element.getChildren().isEmpty() && (element.getTextContent() == null || element.getTextContent().trim().isEmpty())) {
            sb.append("></").append(element.getName()).append(">\n");
        } else {
            sb.append(">\n");

            // Append text content if present
            if (element.getTextContent() != null && !element.getTextContent().trim().isEmpty()) {
                sb.append("\t".repeat(depth + 1)).append(element.getTextContent().trim()).append("\n");
            }

            // Append children recursively
            for (XMLElement child : element.getChildren()) {
                toXMLStringHelper(child, sb, depth + 1);
            }

            sb.append("\t".repeat(depth)).append("</").append(element.getName()).append(">\n");
        }
    }
}
