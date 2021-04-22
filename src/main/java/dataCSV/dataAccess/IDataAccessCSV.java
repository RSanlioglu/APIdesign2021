package dataCSV.dataAccess;

import Exceptions.FileAlreadyExistsException;
import java.util.List;

public interface IDataAccessCSV {
    void createCSV() throws FileAlreadyExistsException;     //Create an empty csv file
    List<Object> getAllObjects();                           //Return all of the objects from csv file
    Object getObjectById(String fieldName, int value);      //Get one object by a field
    Object getObjectById(String fieldName, double value);   //Get one object by a field
    Object getObjectById(String fieldName, String value);   //Get one object by a field
    void writeObject(Object o);                             //Override one object to the file
    void writeList(List<Object> l_o);                       //Override one list to the file
    void appendObject(Object o);                            //Add one object to the file
    void appendList(List<Object> l_o);                      //Add one list of objects to the file
    boolean doesExist(Object o);                            //Check if object exists in file
    void deleteObject(Object o);                            //Delete an object from csv file
    void updateObject(Object oldObject, Object newObject);  //Update an object (Delete old object, add one new object)
}