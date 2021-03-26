package dataXML.dataAccess;

import java.util.List;
public interface IDataAccessXML {
/*
    void createXML(File file, Object o) throws JsonProcessingException;
    void readDataFile();
    void writeToDataFile(Object o) throws JsonProcessingException;
    void writeCollectionToFile() throws JsonProcessingException;


    Object getData();
    ArrayList<Object> getAllData(int key);
    ArrayList<Object> getAllData(double key);
    ArrayList<Object> getAllData(float key);
    void updateField(Object o);
    void deleteField(Object o);*/


        void createXML();                                        //Create a JSON-file
        List<Object> getAllObjects();                             //Get all objects from file
        void writeObject(Object o);                               //Override one object to file
        void writeList(List<Object> l_o, String rootName);                       //Override list to file
        void appendObject(Object o);                                    //Add one object to file
        void appendList(List<Object> l_o);                            //Add list to file
        boolean doesExist(Object o);                              //Check if file exists
        void deleteObject(Object o);                              //Delete one object from file
        void updateObject(Object oldObject, Object newObject);    //Update an existing object


}
