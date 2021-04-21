package dataJSON.dataAccess;

import Exceptions.FileAlreadyExistsException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DataAccessJSON implements IDataAccessJSON {
    private final String fileName;
    private Class type;

    /*Constructor*/
    public DataAccessJSON(String fileName, Class type) {
        this.fileName = fileName;
        this.type = type;
    }

    /**
     * Creates a new file with the filename that the client declared in the constructor.
     * If the file already exists a FileAlreadyExistsException is thrown.
     */
    public void createJson() throws FileAlreadyExistsException {
        File file = new File(fileName);
        try {
            if(!file.createNewFile()) {
                throw new FileAlreadyExistsException("This file already exists: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of objects to client. Then client can easilly
     * cast the objects to appropriate class
     * @return list of objects read from file
     */
    public List<Object> getAllObjects() {
        List<Object> objects = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        MappingIterator<Object> mappingIterator = null;
        try {
            mappingIterator = objectMapper.readerFor(type).readValues(new File(fileName));
            while(mappingIterator.hasNext()) {
                objects.add(mappingIterator.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    /**
     * The client can get one object by ID fields. ID fields should be used since it will guarantee
     * the correct object to be returned by matching it's ID, which is a sort of primary key
     *
     * @param fieldName - The name of the field to be used for searching
     * @param value - The value that the user wants to retrieve the object by
     * @return - Object with correct id will be returned
     */
    public Object getObjectById(String fieldName, double value) {
        List<Object> objects = getAllObjects();
        Field field;
        Object obj = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            for(Object o : objects) {
                if(field.getDouble(o) == value) {
                    obj = o;
                    break;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * The client can get one object by ID fields. ID fields should be used since it will guarantee
     * the correct object to be returned by matching it's ID, which is a sort of primary key
     *
     * @param fieldName - The name of the field to be used for searching
     * @param value - The value that the user wants to retrieve the object by
     * @return - Object with correct id will be returned
     */
    public Object getObjectById(String fieldName, int value) {
        List<Object> objects = getAllObjects();
        Field field;
        Object obj = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            for(Object o : objects) {
                if(field.getInt(o) == value) {
                    obj = o;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * The client can get one object by ID fields. ID fields should be used since it will guarantee
     * the correct object to be returned by matching it's ID, which is a sort of primary key
     *
     * @param fieldName - The name of the field to be used for searching
     * @param value - The value that the user wants to retrieve the object by
     * @return - Object with correct id will be returned
     */
    public Object getObjectById(String fieldName, String value) {
        List<Object> objects = getAllObjects();
        Field field;
        Object obj = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            for(Object o : objects) {
                if(((String) field.get(o)).equals(value)) {
                    obj = o;
                    break;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Gets the datafile specified from the constructor and
     * writes over the file with the object given from the
     * parameter
     * @param o - Object to be written onto the file
     */
    public void writeObject(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the datafile specified from the constructor and
     * writes over the file with data given from the list
     * in the function parameter
     * @param l_o - List of objects to be written onto the file
     */
    public void writeList(List<Object> l_o) {
        ObjectMapper objectMapper = new ObjectMapper();
        for(Object o : l_o) {
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Add one object to the datafile. The object must be of the same
     * type as the data inside the json-file.
     * The previous data will not be removed
     * @param o - Object to be appended onto the file
     */
    public void appendObject(Object o) {
        List<Object> objects = getAllObjects();
        objects.add(o);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add one list of objects to the datafile. The list must contain
     * OBJECTS in order to append to the datafile.
     * The previous data will not be removed
     * @param l_o - List of objects to be appended onto the file
     */
    public void appendList(List<Object> l_o) {
        List<Object> objects = getAllObjects();
        objects.addAll(l_o);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to check if an object does exist in the collection of data
     * gotten from the data-file. The objects are compared by the .toString()
     * functions from the objects.
     * @param o - Object to be searched for
     * @return True or false value
     */
    public boolean doesExist(Object o) {
        boolean exists = false;
        List<Object> objects = getAllObjects();
        for(Object obj : objects) {
            if(obj.toString().equals(o.toString())) {
                exists = true;
            }
        }
        return exists;
    }

    /**
     * Deletes an object from the file. The object must first
     * be created by the client and the framework will find it and
     * remove it
     * @param o - Object to be deleted
     */
    public void deleteObject(Object o) {
        List<Object> objects = getAllObjects();
        objects.removeIf(obj -> obj.toString().equals(o.toString()));

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        try {
            objectWriter.writeValue(new File(fileName), objects);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds the object to be updated and deletes it and replaces it
     * with the new object with new data. The new object is placed
     * last in the file
     * @param oldObject - Object to be updated
     * @param newObject - Object with the new values
     */
    public void updateObject(Object oldObject, Object newObject) {
        Object objectToBeDeleted = null;
        List<Object> objects = getAllObjects();

        for(Object x : objects) {
            if(x.toString().equals(oldObject.toString())) {
                objectToBeDeleted = x;
            }
        }

        deleteObject(objectToBeDeleted);
        appendObject(newObject);
    }
}
