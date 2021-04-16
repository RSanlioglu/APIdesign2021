package dataCSV.converting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dataCSV.dataAcces.DataAccess;
//import dataJSON.dataAccess.DataAccessJSON;
//import dataXML.dataAccess.DataAccessXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Converting implements IConverting {

    @Override
    public void convertToJSON(DataAccess csv, String pathName, String newFileName, Class type) throws IOException {
        //DataAccess csv = new DataAccess();
        List<Object> objects = csv.getAllObjects();
        //DataAccessJSON json = new DataAccessJSON();
        //json.writeCollectionToFile(objects, newFileName);
    }

    @Override
    public void convertToXML(DataAccess csv, String pathName, String newFileName, String rootTagName, Class type) throws IOException {
        //DataAccess csv = new DataAccess();
        List<Object> x = csv.getAllObjects();

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.writerWithDefaultPrettyPrinter().withRootName(rootTagName).writeValue(new File(newFileName), x);
    }
}
