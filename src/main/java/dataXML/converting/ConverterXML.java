package dataXML.converting;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class ConverterXML{

    public static void convertToCSV(String pathNameCSV, String pathNameXML) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        File stylesheet = new File("style.xsl");
        File xmlSource = new File(pathNameCSV);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlSource);

        StreamSource stylesource = new StreamSource(stylesheet);
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer(stylesource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File("/tmp/x.csv"));
        transformer.transform(source, outputTarget);

    }


    public void convertToJSON(String pathName) {

    }
}
