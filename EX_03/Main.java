import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
public class Main {
    public static void main(String[] args) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("https://www.XATAKA.com/sitemap_index.xml").openStream());
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("loc");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Web: " + element.getTextContent());
                    String subURL = list.item(i).getTextContent();

                    String cutURL = subURL.replace("https://www.xataka.com/", "");
                    cutURL = cutURL.replace("/", "_");

                    try {
                        File archivo = new File("XML/" + cutURL);

                        if (!archivo.exists()) {
                            archivo.createNewFile();
                            System.out.println("Archivo creado: " + archivo.getName());
                        }
                        FileWriter fw = new FileWriter(archivo);
                        BufferedWriter bw = new BufferedWriter(fw);

                        Document subDoc = db.parse(new URL(subURL).openStream());
                        NodeList sublist = subDoc.getElementsByTagName("loc");

                        for (int j = 0; j < sublist.getLength(); j++) {
                            Node SubNode = sublist.item(j);
                            if (SubNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element subelement = (Element) SubNode;

                                bw.write(subelement.getTextContent());
                                bw.newLine();

                            }
                        }

                        bw.close();

                    } catch (IOException e) {
                        System.out.println("Error al crear el archivo");
                        e.printStackTrace();
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}