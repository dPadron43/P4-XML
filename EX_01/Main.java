import org.w3c.dom.Document;
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
                    System.out.println("Web: " + list.item(i).getTextContent());
                }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

        }
    }
}