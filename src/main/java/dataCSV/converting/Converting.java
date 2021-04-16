package dataCSV.converting;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dataCSV.dataAcces.DataAccess;
import dataJSON.dataAccess.DataAccessJSON;
import dataXML.dataAccess.DataAccessXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Converting {


    /*  public static void convertToJSON(DataAccess csv, String newFileName, Class type) throws IOException {

          List<Object> objects = csv.getAllObjects();
          DataAccessJSON json = new DataAccessJSON(newFileName,type);
          json.writeList(objects);
      }*/
    public static void convertToJSON(DataAccess csv, String pathName, String newFileName, Class type, boolean header) throws IOException {
        List<Object> objects = new ArrayList<>();
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if (header) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        MappingIterator<Object> mappingIterator;
        File file = new File(pathName);
        if (file.length() != 0) {
            mappingIterator = csvMapper.readerFor(type).with(csvSchema).readValues(file);

            while (mappingIterator.hasNext()) {
                objects.add(mappingIterator.next());
            }
        }


   /* public static void convertToXML(DataAccess csv, String newFileName, String rootTagName, Class type) throws IOException {

        List<Object> x = csv.getAllObjects();

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.writerWithDefaultPrettyPrinter().withRootName(rootTagName).writeValue(new File(newFileName), x);
    }*/
    }
}
