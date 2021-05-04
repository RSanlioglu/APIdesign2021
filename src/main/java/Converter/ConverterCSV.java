package Converter;

import DataAccess.DataAccessCSV;
import DataAccess.DataAccessJSON;
import DataAccess.DataAccessXML;

import java.util.Collections;
import java.util.List;

/**
 * Abstract class used for converting CSV files to JSON or XML files.
 */
abstract public class ConverterCSV {

    /**
     * Function for converting a CSV file to a JSON file.
     * @param csv - DataAccess that is needed for converting. This must be created by client BEFORE converting
     * @param newFileName - The name of the newly created JSON file.
     * @param type - Type of object used inside the csv file.
     */
    public static void convertToJSON(DataAccessCSV csv, String newFileName, Class<?> type) {
          List<Object> objects = csv.getAllObjects();
          DataAccessJSON json = new DataAccessJSON(newFileName,type);
          json.writeList(Collections.singletonList(objects));
    }

    /**
     * Function for converting a CSV file to a XML file.
     * @param csv - DataAccess that is needed for converting. This must be created by client BEFORE converting
     * @param newFileName - The name of the newly created XML file.
     * @param type - Type of object used inside the csv file
     * @param rootName - The name of the root tag in the xml file.
     */
    public static void convertToXML(DataAccessCSV csv, String newFileName, Class<?> type, String rootName) {
        List<Object> objects = csv.getAllObjects();
        DataAccessXML dataAccessXML = new DataAccessXML(newFileName, type, rootName);
        dataAccessXML.writeList(Collections.singletonList(objects));
    }

}
