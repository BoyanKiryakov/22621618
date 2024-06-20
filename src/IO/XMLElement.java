package IO;

import java.util.ArrayList;
import java.util.List;

public class XMLElement {
    private String name;
    private String value;
    private List<XMLElement> children;

    public XMLElement(String name, String value) {
        this.name = name;
        this.value = value;
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

    @Override
    public String toString() {
        StringBuilder xml = new StringBuilder("<" + name + ">\n");
        for (XMLElement child : children) {
            xml.append(child.toString());
        }
        xml.append("</" + name + ">\n");
        return xml.toString();
    }
}