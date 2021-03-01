package dataJSON.converting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;

public class JSONConverter implements IJSONConverter {
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

    @Override
    public void convertToXML(String pathName, String newFileName) {

    }
}
