package dataJSON.converting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class ConverterJSON implements IConverterJSON {
    @Override
    public void convertToCSV(String pathName, String newFileName) throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(new File(pathName));
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();

        JsonNode firstObject = jsonTree.elements().next();

        firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);

        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File(newFileName), jsonTree);
    }

    //Får bare Items for XML filer
    @Override
    public void convertToXML(String pathName, String newFileName, String rootTagName, Class type) throws IOException {
        JsonMapper jsonMapper = new JsonMapper();
        Object x = jsonMapper.readValue(new File(pathName), Object.class);

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.writerWithDefaultPrettyPrinter().withRootName(rootTagName).writeValue(new File(newFileName), x);
    }
}
