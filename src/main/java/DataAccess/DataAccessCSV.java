package DataAccess;

import Exception.AlreadyExistsException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory for data access creation for CSV files
 */
public class DataAccessCSV implements IDataAccess {
    private final String fileName;
    private final boolean withHeaders;
    private final Class<?> type;

    /**
     * Constructor for instantiating the DataAccess for csv files.
     * @param fileName - name of file which the access will operate on
     * @param type - Type of object class the access wil operate on.
     * @param withHeaders - Declare if the CSV file will contain headers.
     */
    public DataAccessCSV(String fileName, Class<?> type, boolean withHeaders) {
        this.fileName = fileName;
        this.withHeaders = withHeaders;
        this.type = type;
    }

    /**
     * Returns a list of object that is red from the
     * .csv file.
     * @param <T> - Type of class used to cast the objects from the data-file
     * @return - A list of objects returned from the data-file
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAllObjects() {
        List<T> objects = new ArrayList<>();

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if(withHeaders) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        MappingIterator<Object> mappingIterator = null;

        try {
            mappingIterator = csvMapper.readerFor(type).with(csvSchema).readValues(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mappingIterator != null) {
            while(mappingIterator.hasNext()) {
                objects.add((T) type.cast(mappingIterator.next()));
            }
        }
        return objects;
    }

    /**
     * The client can get one object by ID fields. Unique ID fields should be used since it will guarantee
     * the correct object to be returned by matching it's ID, which is a sort of primary key
     *
     * @param fieldName - The name of the field to be used for searching
     * @param value - The value that the user wants to retrieve the object by
     * @param <T> - Type of object used in operation
     * @return - Object with correct id will be returned
     */
    @Override
    public <T> T getObjectById(String fieldName, double value) {
        List<T> objects = getAllObjects();
        return GetObjectByIdOperations.getObjectById(objects, fieldName, value, type);
    }

    /**
     * The client can get one object by ID fields. Unique ID fields should be used since it will guarantee
     * the correct object to be returned by matching it's ID, which is a sort of primary key
     *
     * @param fieldName - The name of the field to be used for searching
     * @param value - The value that the user wants to retrieve the object by
     * @return - Object with correct id will be returned
     */
    @Override
    public <T> T getObjectById(String fieldName, int value) {
        List<T> objects = getAllObjects();
        return GetObjectByIdOperations.getObjectById(objects, fieldName, value, type);
    }

    /**
     * The client can get one object by ID fields. Unique ID fields should be used since it will guarantee
     * the correct object to be returned by matching it's ID, which is a sort of primary key
     *
     * @param fieldName - The name of the field to be used for searching
     * @param value - The value that the user wants to retrieve the object by
     * @return - Object with correct id will be returned
     */
    @Override
    public <T> T getObjectById(String fieldName, String value) {
        List<T> objects = getAllObjects();
        return GetObjectByIdOperations.getObjectById(objects, fieldName, value, type);
    }

    /**
     * Writes over the file with this one object that is given by the client.
     * Previous data will be deleted after calling this function
     * @param o - Object to be written on to the file
     */
    @Override
    public void writeObject(Object o) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if(withHeaders) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        try {
            csvMapper.writer(csvSchema).writeValue(new File(fileName), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overrides the file with a list of objects that is given by the client.
     * Previous data will be deleted after calling this function
     * @param l_o - List to written on to the file
     */
    @Override
    public void writeList(List<Object> l_o) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if(withHeaders) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        for(Object o : l_o) {
            try {
                csvMapper.writer(csvSchema).writeValue(new File(fileName), o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Appends the set of data in the datafile with one object given by the client
     * @param o - Object that is supposed to be appended to the file
     */
    @Override
    public void appendObject(Object o) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        csvSchema = csvMapper.schemaFor(type).withoutHeader();

        ObjectWriter writer = csvMapper.writer(csvSchema);
        try {
            OutputStream outputStream = new FileOutputStream(fileName, true);
            writer.writeValue(outputStream, o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends the set of data in the datafile with one list of objects given by the client
     * @param l_o - List of objects that is supposed to be appended to the file
     */
    @Override
    public void appendList(List<Object> l_o) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        csvSchema = csvMapper.schemaFor(type).withoutHeader();

        ObjectWriter writer = csvMapper.writer(csvSchema);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName, true);
            for(Object o : l_o) {
                writer.writeValue(outputStream, o);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A simple test to see if the object does exist in the datafile.
     * If it does, return true, else false.
     * @param o - Object that is supposed to be checked for
     * @return - true or false value
     */
    @Override
    public boolean doesExist(Object o) {
        boolean exists = false;
        List<Object> objects;
        objects = getAllObjects();

        for(Object obj : objects) {
            if(obj.toString().equals(o.toString())) {
                exists = true;
            }
        }
        return exists;
    }

    /**
     * Lets the client delete one object from the datafile.
     * The datafile is read and the objects are stored. The object to be deleted
     * is then searched for and deleted if it exists in the list. Then the new list
     * without the data to be deleted is then written on to the file.
     * @param o - Object to be deleted
     */
    @Override
    public void deleteObject(Object o) {
        List<Object> objects =  getAllObjects();
        objects.removeIf(obj -> obj.toString().equals(o.toString()));

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if(withHeaders) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        ObjectWriter writer = csvMapper.writer(csvSchema);
        try {
            writer.writeValue(new File(fileName), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds the old object in the datafile, and replaces it with the new object.
     * The old object is deleted and the new object is created and appended to the datafile.
     * Ergo updatedObjects will be placed on the bottom of the file
     * @param oldObject - Object to be updated
     * @param newObject - Object containing new information
     */
    @Override
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

    /**
     * Creates a new file with the filename that the client declared in the constructor.
     * @throws AlreadyExistsException - If the file already exists a AlreadyExistsException is thrown.
     */
    public void createCSV() throws AlreadyExistsException {
        File file = new File(fileName);
        try {
            if(!file.createNewFile()) {
                throw new AlreadyExistsException("This file already exists: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}