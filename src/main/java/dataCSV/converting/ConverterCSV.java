package dataCSV.converting;

import DataAccess.DataAccessCSV;
import DataAccess.DataAccessJSON;
import DataAccess.DataAccessXML;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ConverterCSV {


      public static void convertToJSON(DataAccessCSV csv, String newFileName, Class type) throws IOException {

          List<Object> objects = csv.getAllObjects();
          DataAccessJSON json = new DataAccessJSON(newFileName,type);
          json.writeList(Collections.singletonList(objects));
      }


    public static void convertToXML(DataAccessCSV csv, String newFileName, Class type, String rootName) throws IOException {

        List<Object> objects = csv.getAllObjects();
        DataAccessXML dataAccessXML = new DataAccessXML(newFileName, type, rootName);
        dataAccessXML.writeList(Collections.singletonList(objects));
    }

}
