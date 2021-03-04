package dataJSON.dataAccess;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessJSON implements IDataAccessJSON {

    //Create a JSON file
    @Override
    public void createJSON() {

    }

    //Read a JSON file
    @Override
    public List<Object> readDataFile(String fileName, Class type) throws IOException {
        List<Object> objects = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        MappingIterator<Object> mappingIterator = objectMapper.readerFor(type).readValues(new File(fileName));

        while(mappingIterator.hasNext()) {
            objects.add(mappingIterator.next());
        }
        return objects;
    }

    //Write one object into the JSON file !!NOT CORRECT!!
    @Override
    public void writeToDataFile(Object o, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Write a collection of data into the JSON file
    @Override
    public void writeCollectionToFile(List<Object> l_o, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        for(Object o : l_o) {
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Get an object from the JSON file
    @Override
    public Object getData() {
        return null;
    }

    //Kanskje slette disse?
    @Override
    public ArrayList<Object> getAllData(int key) {
        return null;
    }
    @Override
    public ArrayList<Object> getAllData(double key) {
        return null;
    }
    @Override
    public ArrayList<Object> getAllData(float key) {
        return null;
    }

    //Get one field and update it
    @Override
    public void updateField(Object o) {

    }

    //Delete a field in the JSON file
    @Override
    public void deleteField(Object o) {

    }
}
