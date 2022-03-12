import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlToJson {


    public static List<Employee> parseXML(String nameXML) {
        List<Employee> result = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("data.xml"));

            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element employee = (Element) node;
                    result.add(new Employee(

                            Long.parseLong(employee.getElementsByTagName("id").item(0).getTextContent()),
                            employee.getElementsByTagName("firstName").item(0).getTextContent(),
                            employee.getElementsByTagName("lastName").item(0).getTextContent(),
                            employee.getElementsByTagName("country").item(0).getTextContent(),
                            Integer.parseInt(employee.getElementsByTagName("age").item(0).getTextContent())


                    ));

                }


            }
            return result;


        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();

        }
        return null;
    }
}