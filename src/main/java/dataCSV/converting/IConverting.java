package dataCSV.converting;

import dataCSV.dataAcces.DataAccess;

import java.io.IOException;

public interface IConverting {
    void convertToJSON(DataAccess csv, String pathName, String newFileName, Class type) throws IOException;
    void convertToXML(DataAccess csv, String pathName, String newFileName, String rootTagName, Class type) throws IOException;

}
