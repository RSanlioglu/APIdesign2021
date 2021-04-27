package Converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class ConverterJSON {

    public static void convertToCSV(String pathName, String newFileName) {
        JsonNode jsonTree = null;
        try {
            jsonTree = new ObjectMapper().readTree(new File(pathName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();

        JsonNode firstObject = jsonTree.elements().next();

        firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);

        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        try {
            csvMapper.writerFor(JsonNode.class)
                    .with(csvSchema)
                    .writeValue(new File(newFileName), jsonTree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertToXML(String pathName, String newFileName, String rootTagName) {
        JsonMapper jsonMapper = new JsonMapper();
        Object x = null;
        try {
            x = jsonMapper.readValue(new File(pathName), Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writerWithDefaultPrettyPrinter().withRootName(rootTagName).writeValue(new File(newFileName), x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
