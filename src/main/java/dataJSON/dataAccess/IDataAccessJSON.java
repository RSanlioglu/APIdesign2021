package dataJSON.dataAccess;

import Exceptions.FileAlreadyExistsException;

import java.util.List;

public interface IDataAccessJSON {
    void createJson() throws FileAlreadyExistsException;                                        //Create a JSON-file
    List<Object> getAllObjects();                             //Get all objects from file
    Object getObjectById(String fieldName, double value);
    Object getObjectById(String fieldName, int value);
    Object getObjectById(String fieldName, String value);
    void writeObject(Object o);                               //Override one object to file
    void writeList(List<Object> l_o);                       //Override list to file
    void appendObject(Object o);                                    //Add one object to file
    void appendList(List<Object> l_o);                            //Add list to file
    boolean doesExist(Object o);                              //Check if file exists
    void deleteObject(Object o);                              //Delete one object from file
    void updateObject(Object oldObject, Object newObject);    //Update an existing object

}
