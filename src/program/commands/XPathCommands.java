package program.commands;

import program.structure.CommandWithArgs;
import program.structure.XMLElement;

import java.util.ArrayList;
import java.util.List;

public class XPathCommands implements CommandWithArgs {
    private String xpath;

    public XPathCommands(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public void execute(String args) {
        args = args.trim();

        if (args.startsWith("xpath")) {
            args = args.substring("xpath".length()).trim();
        }

        if (args.startsWith("<") && args.endsWith(">")) {
            args = args.substring(1, args.length() - 1).trim();
        } else {
            System.out.println("Invalid XPath format. Please use 'xpath <parentTag/childQuery>'.");
            return;
        }

        if (args.contains("/") && !args.contains("[") && !args.contains("]")&& !args.contains("=")) {
            handleXPathQuery(args);
        } else if (args.contains("@")) {
            handleAttributeQuery(args);
        } else if (args.contains("/") && args.contains("[") && args.contains("]")) {
            handleIndexedQuery(args);
        } else if (args.contains("=")) {
            handleXPathEqual(args);
        } else {
            System.out.println("Invalid XPath format. Please use 'xpath <parentTag/childQuery>'.");
        }
    }

    private void handleXPathQuery(String args) {
        String[] parts = args.split("/");
        String parentTag = parts[0].trim();
        String childQuery = parts[1].trim();

        XMLElement rootElement = program.menu.Menu.getRootElement();
        if (rootElement != null) {
            List<XMLElement> matchedElements = rootElement.getChildrenWithName(parentTag + "/" + childQuery);
            if (!matchedElements.isEmpty()) {
                System.out.println("Matched elements found:");
                for (XMLElement element : matchedElements) {
                    System.out.println(element.toXMLString());
                }
            } else {
                System.out.println("No matching elements found.");
            }
        } else {
            System.out.println("No XML file loaded.");
        }
    }

    private void handleAttributeQuery(String args) {
        String[] parts = args.split("@");
        String parentTag = parts[0].trim();
        String attrName = parts[1].trim();

        XMLElement rootElement = program.menu.Menu.getRootElement();
        if (rootElement != null) {
            List<XMLElement> matchedElements = rootElement.getChildrenWithName(parentTag);
            if (!matchedElements.isEmpty()) {
                System.out.println("Attributes found:");
                for (XMLElement element : matchedElements) {
                    String attrValue = element.getAttributes().toString();
                    if (attrValue != null) {
                        System.out.println(attrName + ": " + attrValue);
                    } else {
                        System.out.println("Attribute '" + attrName + "' not found in element:\n" + element.toXMLString());
                    }
                }
            } else {
                System.out.println("No elements matched for parent tag: " + parentTag);
            }
        } else {
            System.out.println("No XML file loaded.");
        }
    }

    private void handleIndexedQuery(String args) {

        args = args.replace("xpath", "").trim();

      if (!args.contains("[") || !args.contains("]")) {
           System.out.println("Invalid XPath format: " + args);
          return;
      }

        int startIndex = args.indexOf('[');
        String parentTag = args.substring(0, startIndex).trim();

        int endIndex = args.indexOf(']');
        String indexStr = args.substring(startIndex + 1, endIndex).trim();
        int index = Integer.parseInt(indexStr);

        String attribute = args.substring(endIndex + 1).trim();

        XMLElement rootElement = program.menu.Menu.getRootElement();
        if (rootElement != null) {
            List<XMLElement> matchedElements = rootElement.getChildrenWithName(parentTag);
            if (!matchedElements.isEmpty()) {
                if (index < matchedElements.size()) {
                    XMLElement element = matchedElements.get(index);
                    String value = element.getAttribute(attribute);
                    if (value != null) {
                        System.out.println("Value found at index " + index + ": " + value);
                    } else {
                        System.out.println(element.toXMLString());
                    }
                } else {
                    System.out.println("Index " + index + " is out of bounds for matched elements.");
                }
            } else {
                System.out.println("No elements matched for parent tag: " + parentTag);
            }
        } else {
            System.out.println("No XML file loaded.");
        }
    }

    private void handleXPathEqual(String args) {
        args = args.trim();
//        if (!args.startsWith("<") || !args.endsWith(">")) {
//            System.out.println("Invalid XPath format. Please use 'xpath <parentTag(condition)/childQuery>'.");
//            return;
//        }
        args = args.substring(1, args.length() - 1).trim();
        int slashIndex = args.indexOf("/");
        if (slashIndex == -1) {
            System.out.println("Invalid XPath format. Please use 'xpath <parentTag/childQuery>'.");
            return;
        }
        String parentCondition = args.substring(0, slashIndex).trim();
        String childQuery = args.substring(slashIndex + 1).trim();

        String parentTag;
        String condition = null;

        int openParenIndex = parentCondition.indexOf("(");
        int closeParenIndex = parentCondition.lastIndexOf(")");
        if (openParenIndex != -1 && closeParenIndex != -1 && openParenIndex < closeParenIndex) {
            parentTag = parentCondition.substring(0, openParenIndex).trim();
            condition = parentCondition.substring(openParenIndex + 1, closeParenIndex).trim();
        } else {
            parentTag = parentCondition;
        }

        String attribute = null;
        String value = null;

        if (condition != null) {
            String[] conditionParts = condition.split("=");
            if (conditionParts.length != 2) {
                System.out.println("Invalid condition format: " + condition);
                return;
            }

            attribute = conditionParts[0].trim();
            value = conditionParts[1].trim().replaceAll("[\"']", "");
        }

        XMLElement rootElement = program.menu.Menu.getRootElement();
        if (rootElement != null) {
            List<XMLElement> matchedElements = rootElement.getChildrenWithName(parentTag);
            if (!matchedElements.isEmpty()) {
                List<String> names = new ArrayList<>();
                for (XMLElement element : matchedElements) {
                    if (attribute == null || value == null) {
                        List<XMLElement> childElements = element.getChildrenWithName(childQuery);
                        for (XMLElement childElement : childElements) {
                            String childContent = childElement.getTextContent();
                            if (childContent != null && !childContent.isEmpty()) {
                                names.add(childContent);
                            }
                        }
                    } else {
                        String attrValue = element.getAttribute(attribute);
                        if (attrValue != null && attrValue.equals(value)) {
                            List<XMLElement> childElements = element.getChildrenWithName(childQuery);
                            for (XMLElement childElement : childElements) {
                                String childContent = childElement.getTextContent();
                                if (childContent != null && !childContent.isEmpty()) {
                                    names.add(childContent);
                                }
                            }
                        }
                    }
                }

                if (!names.isEmpty()) {
                    System.out.println("Elements with attribute '" + attribute + "' equal to '" + value + "' under parent tag '" + parentTag + "':");
                    for (String name : names) {
                        System.out.println(name);
                    }
                } else {
                    System.out.println("No elements found with attribute '" + attribute + "' equal to '" + value + "' under parent tag '" + parentTag + "'");
                }
            } else {
                System.out.println("No elements matched for parent tag: " + parentTag);
            }
        } else {
            System.out.println("No XML file loaded.");
        }
    }
    @Override
    public void execute() {
    }
}
