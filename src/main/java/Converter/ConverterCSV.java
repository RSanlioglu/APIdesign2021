package Converter;

import DataAccess.DataAccessCSV;
import DataAccess.DataAccessJSON;
import DataAccess.DataAccessXML;

import java.util.Collections;
import java.util.List;

//TODO: Sjekk om du kan fikse converters slik at dem ikke er bundet sammen til DataAccess

abstract public class ConverterCSV {

    public static void convertToJSON(DataAccessCSV csv, String newFileName, Class type) {
          List<Object> objects = csv.getAllObjects();
          DataAccessJSON json = new DataAccessJSON(newFileName,type);
          json.writeList(Collections.singletonList(objects));
    }

    public static void convertToXML(DataAccessCSV csv, String newFileName, Class type, String rootName) {
        List<Object> objects = csv.getAllObjects();
        DataAccessXML dataAccessXML = new DataAccessXML(newFileName, type, rootName);
        dataAccessXML.writeList(Collections.singletonList(objects));
    }

}
