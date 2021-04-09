package dataXML.converting;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConverterXML {

    public static void convertToCSV(String pathName, String newFile, Class type, boolean header) throws IOException {
        List<Object> objects = new ArrayList<>();
        XmlMapper objectMapper = new XmlMapper();
        MappingIterator<Object> mappingIterator;
        File file = new File(pathName);

        if (file.length() != 0) {
            mappingIterator = objectMapper.readerFor(type).readValues(file);

            while (mappingIterator.hasNext()) {
                objects.add(mappingIterator.next());
            }
        }

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if (header) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }

        csvMapper.writer(csvSchema).writeValue(new File(newFile), objects);

    }


    public static void convertToJSON(String pathName, String newFile, Class type) throws IOException {
        List<Object> objects = new ArrayList<>();
        XmlMapper objectMapper = new XmlMapper();
        MappingIterator<Object> mappingIterator;
        File file = new File(pathName);

        if (file.length() != 0) {
            mappingIterator = objectMapper.readerFor(type).readValues(file);

            while (mappingIterator.hasNext()) {
                objects.add(mappingIterator.next());
            }
        }

        ObjectMapper objectMapperJSON = new ObjectMapper();
        objectMapperJSON.writerWithDefaultPrettyPrinter().writeValue(new File(newFile), objects);
    }
}
