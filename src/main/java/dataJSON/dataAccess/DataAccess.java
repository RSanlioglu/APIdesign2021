package dataJSON.dataAccess;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataAccess implements IDataAccess{

    //Create a JSON file
    @Override
    public void createJSON() {

    }

    //Read a JSON file
    @Override
    public void readDataFile() {

    }

    //Write one object into the JSON file
    @Override
    public void writeToDataFile(Object o) {

    }

    //Write a collection of data into the JSON file
    @Override
    public void writeCollectionToFile() {

    }

    //Get an object from the JSON file
    @Override
    public Object getData() {
        return null;
    }

    //Get all data from the JSON file with different keys
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
