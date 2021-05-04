package Converter;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
    Abstract class used for converting XML files to CSV or JSON files.
 */
abstract public class ConverterXML {

    /**
     * Function for converting XML file to CSV file.
     * @param pathName - Mame of the XML file that the client wants to convert
     * @param newFile - Name of the new CSV file that the client retrieves after the converting
     * @param type - Type of objects used inside the XML file.
     * @param header - Boolean value if the client wants headers or not
     */
    public static void convertToCSV(String pathName, String newFile, Class<?> type, boolean header) {
        List<Object> objects = readObjectsFromXml(pathName, type);

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if (header) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        try {
            csvMapper.writer(csvSchema).writeValue(new File(newFile), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function for converting XML file to JSON file.
     * @param pathName - Name of the XML file that the client wants to convert
     * @param newFile - Name of the new JSON file that the client retrieves after the converting
     * @param type - Type of objects used inside the XML file.
     */
    public static void convertToJSON(String pathName, String newFile, Class<?> type) {
        List<Object> objects = readObjectsFromXml(pathName, type);

        ObjectMapper objectMapperJSON = new ObjectMapper();
        try {
            objectMapperJSON.writerWithDefaultPrettyPrinter().writeValue(new File(newFile), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private function used for reading objects from a XML file.
     * @param pathName - Path of the xml file.
     * @param type - Type of class hat the objects consist of
     * @return a list of objects
     */
    private static List<Object> readObjectsFromXml(String pathName, Class<?> type) {
        List<Object> objects = new ArrayList<>();
        XmlMapper objectMapper = new XmlMapper();
        MappingIterator<Object> mappingIterator;
        File file = new File(pathName);

        if (file.length() != 0) {
            try {
                mappingIterator = objectMapper.readerFor(type).readValues(file);
                while (mappingIterator.hasNext()) {
                    objects.add(mappingIterator.next());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objects;
    }
}
