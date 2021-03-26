package dataXML.converting;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface IConverterXML {
    void convertToCSV(String pathName, String pathNameXML) throws ParserConfigurationException, IOException, SAXException, TransformerException;
    void convertToJSON(String pathName);
}
