package dataJSON.converting;

import java.io.IOException;

public interface IConverterJSON {
    void convertToCSV(String pathName, String newFileName) throws IOException;
    void convertToXML(String pathName, String newFileName, String rootTagName, Class type) throws IOException;
}
