package dataXML.dataAccess;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessXML implements IDataAccessXML {

    public List<Object> o_list = new ArrayList<>();
    private final String fileName;
    private Class type;
    private String rootName;



    public DataAccessXML(String fileName, Class type, String rootName) {
        this.fileName = fileName;
        this.type = type;
        this.rootName = rootName;
        createXML();

    }



    @Override
    public List<Object> getAllObjects() {

        List<Object> objects = new ArrayList<>();
        XmlMapper objectMapper = new XmlMapper();
        MappingIterator<Object> mappingIterator;
        File file = new File(fileName);

        if (file.length() != 0) {
            try {
                mappingIterator = objectMapper.readerFor(type).readValues(file);


                while (mappingIterator.hasNext()) {
                    objects.add(mappingIterator.next());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return objects;
    }


    @Override
    public void createXML() {
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("Success");
            } else {
                System.out.println("This file already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeObject(Object o) {
        List<Object> objects = new ArrayList<>();
        objects.add(o);
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File(fileName), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeList(List<Object> l_o) {

        XmlMapper xmlMapper = new XmlMapper();


        try {
            xmlMapper.writerWithDefaultPrettyPrinter().withRootName(rootName).writeValue(new File(fileName), l_o);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*Add one object to the datafile. The object must be of the same
     * type as the data inside the json-file.
     * The previous data will not be removed*/
    public void appendObject(Object o) {
        List<Object> objects = getAllObjects();
        objects.add(o);

        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Add one list of objects to the datafile. The list must contain
     * OBJECTS in order to append to the datafile.
     * The previous data will not be removed*/
    public void appendList(List<Object> l_o) {
        List<Object> objects = getAllObjects();
        objects.addAll(l_o);
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*Used to check if an object does exist in the collection of data
     * gotten from the data-file. The objects are compared by the .toString()
     * functions from the objects. Therefore
     * */
    public boolean doesExist(Object o) {
        boolean exists = false;
        List<Object> objects = getAllObjects();


        for (Object x : objects) {
            if (x.toString().equals(o.toString())) {
                exists = true;
                System.out.println("this exists");
            }
        }
        return exists;
    }

    /*Deletes an object from the file. The object must first
     * be created by the client and the framework will find it and
     * remove it*/
    public void deleteObject(Object o) {
        List<Object> objects = getAllObjects();

        for (Object x : objects) {
            if (x.toString().equals(o.toString())) {
                objects.remove(x);
                break;
            }
        }
        writeList(objects);
    }

    /*Finds the object to be updated and deletes it and replaces it
     * with the new object with new data. The new object is placed
     * last in the file*/
    public void updateObject(Object oldObject, Object newObject) {
        List<Object> objects = getAllObjects();
        Object objectToBeDeleted = null;

        for (Object x : objects) {
            if (x.toString().equals(oldObject.toString())) {

                objectToBeDeleted = x;

            }
        }

        deleteObject(objectToBeDeleted);
        appendObject(newObject);
    }
}
