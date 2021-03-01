package dataJSON.converting;

import java.io.IOException;

public interface IJSONConverter {
    void convertToCSV(String pathName, String newFileName) throws IOException;
    void convertToXML(String pathName, String newFileName);
}
