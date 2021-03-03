package dataXML.dataAccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataAccessXML implements IDataAccessXML{
    @Override
    public void createXML(File file, Object o) {

        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(file, o);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void assertNotNull(String xml) {
    }

    @Override
    public void readDataFile() {

    }

    @Override
    public void writeToDataFile(Object o) throws JsonProcessingException{
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(o);
        assertNotNull(xml);
    }

    @Override
    public void writeCollectionToFile(){
       
          
        

    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public ArrayList<Object> getAllData(int key) {
        return null;
    }

    @Override
    public ArrayList<Object> getAllData(double key) {
        return null;
    }

    @Override
    public ArrayList<Object> getAllData(float key) {
        return null;
    }

    @Override
    public void updateField(Object o) {

    }

    @Override
    public void deleteField(Object o) {

    }
}
