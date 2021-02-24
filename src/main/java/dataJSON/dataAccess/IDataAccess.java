package dataJSON.dataAccess;

import java.util.ArrayList;

public interface IDataAccess {
    void createJSON();
    void readDataFile();
    void writeToDataFile(Object o);
    void writeCollectionToFile();

    /*CRUD operasjoner i rammeverket*/
    Object getData();
    ArrayList<Object> getAllData(int key);
    ArrayList<Object> getAllData(double key);
    ArrayList<Object> getAllData(float key);
    void updateField(Object o);
    void deleteField(Object o);
}
