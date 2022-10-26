import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
public class Main {
    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new URL("https://www.XATAKA.com/sitemap_index.xml").openStream());

            doc.getDocumentElement().normalize();






                System.out.println("Elemento raiz :" + doc.getDocumentElement().getNodeName());
            System.out.println("-----------------");

            NodeList list = doc.getElementsByTagName("loc");

            for (int i = 0; i < list.getLength(); i++) {
                System.out.println("---------------------------------");

                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Web: " + element.getTextContent());
                    Document subdoc = db.parse(new URL(element.getTextContent()).openStream());
                    NodeList sublist = doc.getElementsByTagName("loc");
                    for (int j = 0; j < list.getLength(); j++) {
                        Node SubNode = list.item(j);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element subelement = (Element) node;
                            System.out.println("    SubWeb: " + subelement.getTextContent());
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

        }
    }
}