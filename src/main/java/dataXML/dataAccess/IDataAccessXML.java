package dataXML.dataAccess;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.util.ArrayList;

public interface IDataAccessXML {

    void createXML(File file, Object o) throws JsonProcessingException;
    void readDataFile();
    void writeToDataFile(Object o) throws JsonProcessingException;
    void writeCollectionToFile() throws JsonProcessingException;

    /*CRUD operasjoner i rammeverket*/
    Object getData();
    ArrayList<Object> getAllData(int key);
    ArrayList<Object> getAllData(double key);
    ArrayList<Object> getAllData(float key);
    void updateField(Object o);
    void deleteField(Object o);
}
