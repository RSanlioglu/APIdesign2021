package dataJSON.dataAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IDataAccessJSON {
    void createJSON();
    List<Object> readDataFile(String fileName, final Class type) throws IOException;
    void writeToDataFile(Object o, String fileName);
    void writeCollectionToFile(List<Object> l_o, String fileName);

    /*CRUD operasjoner i rammeverket*/
    Object getData();
    ArrayList<Object> getAllData(int key);
    ArrayList<Object> getAllData(double key);
    ArrayList<Object> getAllData(float key);
    void updateField(Object o);
    void deleteField(Object o);
}
