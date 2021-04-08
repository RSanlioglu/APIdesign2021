package dataXML.dataAccess;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataAccessXML implements IDataAccessXML {
    public List<Object> o_list = new ArrayList<>();
    private final String fileName;
    private Class type;


    public DataAccessXML(String fileName, Class type) {
        this.fileName = fileName;
        this.type = type;
        createXML();

    }

    private void write(List<Object> obj) {
        XmlMapper objectMapper = new XmlMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(Class type) throws IOException {

        List<Object> objects = new ArrayList<>();
        XmlMapper objectMapper = new XmlMapper();
        MappingIterator<Object> mappingIterator;
        File file = new File(fileName);

        if(file.length() != 0) {
            mappingIterator = objectMapper.readerFor(type).readValues(file);

            while (mappingIterator.hasNext()) {
                objects.add(mappingIterator.next());
            }
            o_list.addAll(objects);
        }
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

    /*Returns a list of objects to client. Then client can easilly
     * cast the objects to appropriate class*/
    public List<Object> getAllObjects() {
        List<Object> returnList = new ArrayList<>();
        try {
            read(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        returnList.addAll(o_list);
        return returnList;
    }

    @Override
    public void writeObject(Object o) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File(fileName), o);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void writeList(List<Object> l_o, String rootName) {
        XmlMapper xmlMapper = new XmlMapper();
        for (Object o : l_o) {
            try {
                xmlMapper.writerWithDefaultPrettyPrinter().withRootName(rootName).writeValue(new File(fileName), o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*Add one object to the datafile. The object must be of the same
     * type as the data inside the json-file.
     * The previous data will not be removed*/
    public void appendObject(Object o) {
        try {
            read(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        o_list.add(o);
        write(o_list);

    }

    /*Add one list of objects to the datafile. The list must contain
     * OBJECTS in order to append to the datafile.
     * The previous data will not be removed*/
    public void appendList(List<Object> l_o) {
        try {
            read(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        o_list.addAll(l_o);
        write(o_list);
    }

    /*Used to check if an object does exist in the collection of data
     * gotten from the data-file. The objects are compared by the .toString()
     * functions from the objects. Therefore
     * */
    public boolean doesExist(Object o) {
        boolean exists = false;
        try {
            read(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Object x : o_list) {
            if(x.toString().equals(o.toString())) {
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
        Iterator i = o_list.iterator();
        try {
            read(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Object x : o_list) {
            if(x.toString().equals(o.toString())) {
               o_list.remove(x);
               break;
            }
        }
        write(o_list);
    }

    /*Finds the object to be updated and deletes it and replaces it
     * with the new object with new data. The new object is placed
     * last in the file*/
    public void updateObject(Object oldObject, Object newObject) {
        //Object objectToBeDeleted = null;
        try {
            read(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Object x : o_list) {
            if(x.toString().equals(oldObject.toString())) {
                o_list.remove(x);
                break;
            }
        }
        write(o_list);
        //deleteObject(objectToBeDeleted);
        appendObject(newObject);
    }
}
