package dataJSON.dataAccess;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataAccessJSON implements IDataAccessJSON {
    public List<Object> o_list = new ArrayList<>();
    private final String fileName;

    /*Constructor*/
    public DataAccessJSON(String fileName, Class type) {
        this.fileName = fileName;
        try {
            read(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Private function that is called by client-functions. This is a
    * reader that reads objects and treats them by the class type that
    * the client specifies with*/
    private void read(Class type) throws IOException {
        List<Object> objects = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        MappingIterator<Object> mappingIterator = objectMapper.readerFor(type).readValues(new File(fileName));
        while(mappingIterator.hasNext()) {
            objects.add(mappingIterator.next());
        }
        o_list.addAll(objects);
    }

    /*Private function that is called by client-functions. This is a
    * writer that writes a list of object(s) to the file specified by the user*/
    private void write(List<Object> obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Create a new file if it does not exist*/
    public void createJson(){
        File file = new File(fileName);
        try {
            if(file.createNewFile()) {
                System.out.println("Success");
            } else {
                System.out.println("This file already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Returns a list of objects to client. Then client can easilly
    * cast the objects to appropriate class*/
    public List<Object> getAllObjects() {
        List<Object> returnList = new ArrayList<>();
        returnList.addAll(o_list);
        return returnList;
    }

    /*Gets the datafile specified from the constructor and
    * writes over the file with the object given from the
    * parameter*/
    public void writeToFile(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Gets the datafile specified from the constructor and
    * writes over the file with data given from the list
    * in the function parameter*/
    public void writeToFile(List<Object> l_o) {
        ObjectMapper objectMapper = new ObjectMapper();
        for(Object o : l_o) {
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*Add one object to the datafile. The object must be of the same
    * type as the data inside the json-file.
    * The previous data will not be removed*/
    public void append(Object o) {
        o_list.add(o);
        write(o_list);
    }

    /*Add one list of objects to the datafile. The list must contain
    * OBJECTS in order to append to the datafile.
    * The previous data will not be removed*/
    public void append(List<Object> l_o) {
        o_list.addAll(l_o);
        write(o_list);
    }

    /*Used to check if an object does exist in the collection of data
    * gotten from the data-file. The objects are compared by the .toString()
    * functions from the objects. Therefore
    * */
    public boolean doesExist(Object o) {
        boolean exists = false;
        for(Object x : o_list) {
            if(x.toString().equals(o.toString())) {
                exists = true;
            }
        }
        return exists;
    }

    /*Deletes an object from the file. The object must first
    * be created by the client and the framework will find it and
    * remove it*/
    public void deleteObject(Object o) {
        Iterator i = o_list.iterator();
        while(i.hasNext()) {
            Object x = i.next();
            if(x.toString().equals(o.toString())) {
                i.remove();
            }
        }
        write(o_list);
    }

    /*Finds the object to be updated and deletes it and replaces it
    * with the new object with new data. The new object is placed
    * last in the file*/
    public void updateObject(Object oldObject, Object newObject) {
        Object objectToBeDeleted = null;

        for(Object x : o_list) {
            if(x.toString().equals(oldObject.toString())) {
                objectToBeDeleted = x;
            }
        }
        deleteObject(objectToBeDeleted);
        append(newObject);
    }
}
