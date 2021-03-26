package dataXML.dataAccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class DataAccessXML implements IDataAccessXML{
    public List<Object> o_list = new ArrayList<>();
    private final String fileName;

    public DataAccessXML(String fileName, Class type) {
        this.fileName = fileName;
      createXML();
       try {
            createXML();
            read(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createXML(File file, Object o) {

        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(file, o);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
    private void read(Class type) throws IOException {

        List<Object> objects = new ArrayList<>();
        XmlMapper objectMapper = new XmlMapper();
        MappingIterator<Object> mappingIterator = objectMapper.readerFor(type).readValues(fileName);;

        while(mappingIterator.hasNext()) {
            objects.add(mappingIterator.next());
        }
        o_list.addAll(objects);
    }

    private void assertNotNull(String xml) {
    }




    public void writeToDataFile(Object o) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(o);
        assertNotNull(xml);
    }


    @Override
    public void createXML() {
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

    @Override
    public List<Object> getAllObjects() {
        return null;
    }

    @Override
    public void writeObject(Object o) {

    }

    @Override
    public void writeList(List<Object> l_o) {

    }

    @Override
    public void appendObject(Object o) {

    }

    @Override
    public void appendList(List<Object> l_o) {

    }

    @Override
    public boolean doesExist(Object o) {
        return false;
    }

    @Override
    public void deleteObject(Object o) {

    }

    @Override
    public void updateObject(Object oldObject, Object newObject) {

    }
}
