package dataCSV.dataAcces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessCSV implements IDataAccessCSV {
    //Constructor
    public void createCsv() {

    }

    //Write an object to a CSV file
    public void writeToDataFile(Object o, String fileName, Class type, Boolean withHeaders) throws JsonProcessingException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if(withHeaders) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        try {
            csvMapper.writer(csvSchema).writeValue(new File(fileName), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Write a list to CSV
    public void writeCollectionToDataFile(List<Object> l_o, String fileName, Class type, Boolean withHeaders) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if(withHeaders) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        for(Object o : l_o) {
            try {
                csvMapper.writer(csvSchema).writeValue(new File(fileName), o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Read a csv file
    public List<Object> readDataFile(String fileName, Class type, Boolean withHeaders) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if (withHeaders) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        MappingIterator<Object> mappingIterator = csvMapper.readerFor(type).with(csvSchema).readValues(new File(fileName));
        List<Object> objects = new ArrayList<>();
        while(mappingIterator.hasNext()) {
            objects.add(mappingIterator.next());
        }
        return objects;
    }
}
