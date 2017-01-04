package fatima;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class WorldCatXML {

    public WorldCatXML(String xmlFile) {
        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse("WorldCatSearchOutput.xml");

            FileWriter fstream = new FileWriter(xmlFile);
            BufferedWriter out = new BufferedWriter(fstream);
            // normalize text representation
            doc.getDocumentElement().normalize();
            System.out.println("L'élément racine du document est "
                    + doc.getDocumentElement().getNodeName());

            NodeList listOfBooks = doc.getElementsByTagName("entre");
            int totalPersons = listOfBooks.getLength();
            System.out.println("Nombre total de publications / articles : " + totalPersons);
            out.write("<site name=\"WorldCat\">\n");
            for (int s = 0; s < listOfBooks.getLength(); s++) {

                out.write("<book>\n");
                Node title = listOfBooks.item(s);
                if (title.getNodeType() == Node.ELEMENT_NODE) {


                    Element elem = (Element) title;

                    //-------
                    NodeList titleList = elem.getElementsByTagName("title");
                    Element titluElement = (Element) titleList.item(0);

                    NodeList ttlList = titluElement.getChildNodes();
//                    out.write("<title>" + ((Node) ttlList.item(0)).getNodeValue().trim().replace("&", "and") + "</title>\n");
                    out.append("<title>" + ((Node)ttlList.item(0)).getNodeValue().trim().replace("&", "&amp;").replace("\"","&quot;").replace("\'","&apos;").replace("<","&lt;").replace(">","&gt;") +"</title>\n");
                    try {
                        NodeList author = elem.getElementsByTagName("name");
                        Element nameElement = (Element) author.item(0);

                        NodeList nmList = nameElement.getChildNodes();
                        out.write("<author>" + ((Node) nmList.item(0)).getNodeValue().trim() + "</author>\n");
                    } catch (NullPointerException err) {
                    }

                    NodeList link = elem.getElementsByTagName("id");
                    Element linkElement = (Element) link.item(0);

                    NodeList lnkList = linkElement.getChildNodes();
                    out.write("<url>" + ((Node) lnkList.item(0)).getNodeValue().trim() + "</url>\n");


                    try {
                        NodeList dcid = elem.getElementsByTagName("dc:identifier");
                        Element dcident1Element = (Element) dcid.item(0);

                        NodeList dcid1List = dcident1Element.getChildNodes();
                        out.write("<identifier>" + ((Node) dcid1List.item(0)).getNodeValue().trim() + "</identifier>\n");

                    } catch (NullPointerException err) {
                    }

                    NodeList dci = elem.getElementsByTagName("oclcterms:recordIdentifier");
                    Element dcidentElement = (Element) dci.item(0);

                    NodeList dcidList = dcidentElement.getChildNodes();
                    out.write("<recordIdentifier>" + ((Node) dcidList.item(0)).getNodeValue().trim() + "</recordIdentifier>\n");



                    out.write("</book>\n");
                }


            }
            out.write("</site>\n");
            out.close();

        } catch (SAXParseException err) {
            System.out.println("** Erreur d'analyse" + ", ligne "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();

        } catch (Throwable t) {
            t.printStackTrace();
        }

    }
}
