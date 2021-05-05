package DataAccess;

import java.util.List;

public interface IDataAccess {
    /**
     * Will return all objects from the datafile
     * @param <T> - Type of object
     * @return - list of objects received from the datafile
     */
    <T> List<T> getAllObjects();

    /**
     * Get one object by it's id-reference
     * @param <T> - Type of object
     * @param fieldName - Name of the ID field
     * @param value - Value of the ID
     * @return - Returns the object if found, else null
     */
    <T> T getObjectById(String fieldName, int value);

    /**
     * Get one object by it's id-reference
     * @param <T> - Type of object
     * @param fieldName - Name of the ID field
     * @param value - Value of the ID
     * @return - Returns the object if found, else null
     */
    <T> T getObjectById(String fieldName, double value);

    /**
     * Get one object by it's id-reference
     * @param <T> - Type of object
     * @param fieldName - Name of the ID field
     * @param value - Value of the ID
     * @return - Returns the object if found, else null
     */
    <T> T getObjectById(String fieldName, String value);

    /**
     * Overwrites the data-file with one single object, received from the client
     * @param o - Object that will overwrite the datafile
     */
    void writeObject(Object o);

    /**
     * Overwrites the data-file with a list of objects, received from the client
     * @param l_o - List of objects that will overwrite the datafile
     */
    void writeList(List<Object> l_o);

    /**
     * Appends the datafile with a single object
     * @param o - Object that the client wants to append the data-file with
     */
    void appendObject(Object o);

    /**
     * Appends the datafile with a list of objects
     * @param l_o - List of objects that the client wants to append the data-file with
     */
    void appendList(List<Object> l_o);

    /**
     * Simple check to see if an object exists in the data-file
     * @param o - Object that the client wants to search for
     * @return - boolean value for if the object does exist
     */
    boolean doesExist(Object o);

    /**
     * Deletes the object from the datafile
     * @param o - The object that the client wishes to delete
     */
    void deleteObject(Object o);

    /**
     * Updates an object by deleting the old object, and writing the new
     * one at he bottom of the datafile
     * @param oldObject - The old object that is supposed to be updated
     * @param newObject - The new object containing new information
     */
    void updateObject(Object oldObject, Object newObject);
}