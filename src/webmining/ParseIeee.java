package webmining;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class ParseIeee {

    public ParseIeee(String xmlFile, String outFileName) {
        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(xmlFile));

           
            doc.getDocumentElement().normalize();
            NodeList listOfBooks = null;
            try {
                listOfBooks = doc.getElementsByTagName("document");
                int totalBooks = listOfBooks.getLength();
                System.out.println("Total no of pubications/articles : " + totalBooks);
            } catch (NullPointerException e) {
            }

            OutputStream out = System.out;
            out = new FileOutputStream(outFileName);
            String line = new String();

            line = "<site name=\"ieeexplore\">\n";
            out.write(line.getBytes("UTF-8"));

            for (int s = 0; s < listOfBooks.getLength(); s++) {
                out.write("<book>\n".getBytes("UTF-8"));
                Node firstNode = listOfBooks.item(s);
                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element firstElement = (Element) firstNode;

                    //-------
                    try {
                        NodeList firstNameList = firstElement.getElementsByTagName("title");
                        Element firstNameElement = (Element) firstNameList.item(0);
                        NodeList textFNList = firstNameElement.getChildNodes();
                        line = "<title>" + replaceSpecialCharacters(((Node) textFNList.item(0)).getNodeValue().trim()) + "</title>\n";
                        out.write(line.getBytes("UTF-8"));
                    } catch (NullPointerException e) {
                    }

                    try {
                        NodeList author = firstElement.getElementsByTagName("authors");
                        Element lastElement = (Element) author.item(0);
                        NodeList textLNList = lastElement.getChildNodes();
                        String au = ((Node) textLNList.item(0)).getNodeValue().trim();
                        String[] authors = au.split(";");
                        out.write("<authors>\n".getBytes("UTF-8"));
                        for (int j = 0; j < authors.length; j++) {
                            line = "<author>" + replaceSpecialCharacters(authors[j]) + "</author>\n";
                            out.write(line.getBytes("UTF-8"));
                        }
                        out.write("</authors>\n".getBytes("UTF-8"));
                    } catch (NullPointerException e) {
                    }

                    try {
                        NodeList pyList = firstElement.getElementsByTagName("py");
                        Element pyElement = (Element) pyList.item(0);

                        NodeList url = pyElement.getChildNodes();
                        line = "<pubyear>" + replaceSpecialCharacters(((Node) url.item(0)).getNodeValue().trim()) + "</pubyear>\n";
                        out.write(line.getBytes("UTF-8"));
                    } catch (NullPointerException e) {
                    }

                    try {
                        NodeList urlList = firstElement.getElementsByTagName("mdurl");
                        Element urlElement = (Element) urlList.item(0);

                        NodeList url = urlElement.getChildNodes();
                        line = "<url>" + replaceSpecialCharacters(((Node) url.item(0)).getNodeValue().trim()) + "</url>\n";
                        out.write(line.getBytes("UTF-8"));
                    } catch (NullPointerException e) {
                    }

                    try {
                        NodeList affiliationsList = firstElement.getElementsByTagName("affiliations");
                        Element affiliationsElement = (Element) affiliationsList.item(0);

                        NodeList affiliations = affiliationsElement.getChildNodes();
                        line = "<affiliations>" + replaceSpecialCharacters(((Node) affiliations.item(0)).getNodeValue().trim()) + "</affiliations>\n";
                        out.write(line.getBytes("UTF-8"));
                    } catch (NullPointerException e) {
                    }

                    try {
                        NodeList isbnList = firstElement.getElementsByTagName("isbn");
                        Element isbnElement = (Element) isbnList.item(0);

                        NodeList textIsbnList = isbnElement.getChildNodes();
                        line = "<isbn>" + replaceSpecialCharacters(((Node) textIsbnList.item(0)).getNodeValue().trim()) + "</isbn>\n";
                        out.write(line.getBytes("UTF-8"));
                    } catch (NullPointerException e) {
                    }

                    try {
                        NodeList doiList = firstElement.getElementsByTagName("doi");
                        Element doiElement = (Element) doiList.item(0);

                        NodeList textdoiList = doiElement.getChildNodes();
                        line = "<doi>" + replaceSpecialCharacters(((Node) textdoiList.item(0)).getNodeValue().trim()) + "</doi>\n";
                        out.write(line.getBytes("UTF-8"));
                    } catch (NullPointerException e) {
                    }

                }//end of if clause

                out.write("</book>\n".getBytes("UTF-8"));
            }
            out.write("</site>".getBytes("UTF-8"));
            if (out != System.out) {
                out.close();
            }

        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();

        } catch (Throwable t) {
            t.printStackTrace();
        }

    }//end of ParseIeee

    private String replaceSpecialCharacters(String str) {        
        if (str.contains("&")) {
            str = str.replace("&", "&amp;");
        }
        if (str.contains("<")) {
            str = str.replace("<", "&lt;");
        }
        if (str.contains(">")) {
            str = str.replace(">", "&gt;");
        }
        if (str.contains("\"")) {
            str = str.replace("\"", "&quot;");
        }
        if (str.contains("'")) {
            str = str.replace("'", "&apos;");
        }
        return str;
    }
}

