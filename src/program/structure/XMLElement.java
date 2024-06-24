package program.structure;

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

    public String getTagName() {
        return tagName;
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

    public List<XMLElement> getChildren() {
        return children;
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
    public List<XMLElement> getChildrenWithName(String query) {
        List<XMLElement> matchedChildren = new ArrayList<>();

        // Разделяне на търсенето на две части
        String[] parts = query.split("/", 2);
        String parentTagName = parts[0].trim();
        String childQuery = parts.length > 1 ? parts[1].trim() : null;

        for (XMLElement child : children) {
            if (child.getTagName().equalsIgnoreCase(parentTagName)) {
                if (childQuery == null) {
                    matchedChildren.add(child);
                } else {
                    if (childQuery.equals("*")) {
                        matchedChildren.addAll(child.getChildren());
                    } else if (childQuery.startsWith("@")) {
                        String attrName = childQuery.substring(1);
                        String attrValue = child.getAttribute(attrName);
                        if (attrValue != null) {
                            matchedChildren.add(new XMLElement(attrName, attrValue, null));
                        }
                    } else {
                        List<XMLElement> queriedChildren = child.getChildrenWithName(childQuery);
                        matchedChildren.addAll(queriedChildren);
                    }
                }
            }
        }

        return matchedChildren;
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

    //Клас за превръщане на прочетената структура от файл в стринг, които да се запази в паметта
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
    public List<XMLElement> getChildrenByTagName(String tagName) {
        List<XMLElement> matchingChildren = new ArrayList<>();
        for (XMLElement child : children) {
            if (child.getTagName().equals(tagName)) {
                matchingChildren.add(child);
            }
        }
        return matchingChildren;
    }

}