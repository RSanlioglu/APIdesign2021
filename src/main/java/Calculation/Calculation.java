package Calculation;

import DataAccess.DataAccessCSV;
import DataAccess.DataAccessJSON;
import DataAccess.DataAccessXML;

import java.util.List;

/**
 * Factory for creating a Calculation instance. The factory contains
 * three overloads for its constructor, supporting three datafiles (CSV, XML and JSON)
 */
public class Calculation implements ICalculation{
    /**
     * List of objects used for calculation
     */
    private static List<Object> objects;

    /**
     * Constructor for Calculation, supporting XML data-access
     * @param xml - Data-access for the data to be calculated
     * @throws NullPointerException - When data access is throws a nullpointer
     */
    public Calculation(DataAccessXML xml) throws NullPointerException {
        if(xml == null) {
            throw new NullPointerException();
        } else {
            objects = xml.getAllObjects();
        }
    }

    /**
     * Constructor for Calculation, supporting JSON data-access
     * @param json - Data-access for the data to be calculated
     * @throws NullPointerException - When data access throws a nullpointer
     */
    public Calculation(DataAccessJSON json) throws NullPointerException {
        if(json == null) {
            throw new NullPointerException();
        } else {
            objects = json.getAllObjects();
        }
    }

    /**
     * Constructor for Calculation, supporting CSV data-access
     * @param csv - Data-access for the data to be calculated
     * @throws NullPointerException - When data access throws a nullpointer
     */
    public Calculation(DataAccessCSV csv) throws NullPointerException {
        if(csv == null) {
            throw new NullPointerException();
        } else {
            objects = csv.getAllObjects();
        }
    }

    /**
     * Function for calculating a columns integer - MAX value, MIN value and SUM value
     * @param columnName - Name of column you want to execute calculation operation on
     * @param type - Type of class that the objects consist of
     * @param operation - The operation that the client wishes to use (MIN, MAX and SUM)
     * @return integer value of the calculations
     */
    @Override
    public int calculateColumnInt(String columnName, Class<?> type, Method operation) {
        switch (operation) {
            case MAX:
                 return Operations.calculateColumnMaxInt(objects, columnName, type);
            case MIN:
                return Operations.calculateColumnMinInt(objects, columnName, type);
            case SUM:
                return Operations.calculateColumnSumInt(objects, columnName, type);
        }
        return -1;
    }

    /**
     * Function for calculating a columns double - MAX value, MIN value and SUM value
     * @param columnName - Name of column you want to execute calculation operation on
     * @param type - Type of class that the objects consist of
     * @param operation - The operation that the client wishes to use (MIN, MAX and SUM)
     * @return double value of the calculations
     */
    @Override
    public double calculateColumnDouble(String columnName, Class<?> type, Method operation) {
        switch (operation) {
            case MAX:
                return Operations.calculateColumnMaxDouble(objects, columnName, type);
            case MIN:
                return Operations.calculateColumnMinDouble(objects, columnName, type);
            case SUM:
                return Operations.calculateColumnSumDouble(objects, columnName, type);
        }
        return -1;
    }

    /**
     * Function for calculating the average int of a column.
     * @param columnName - Name of the column you want to find the average of
     * @param type - Type of class that the objects consist of
     * @return - average value
     */
    @Override
    public double calculateColumnAverageInt(String columnName, Class<?> type) {
        return Operations.calculateColumnAverageInt(objects, columnName, type);
    }

    /**
     * Function for calculating the average double of a column.
     * @param columnName - Name of the column you want to find the average of
     * @param type - Type of class that the objects consist of
     * @return - average value
     */
    @Override
    public double calculateColumnAverageDouble(String columnName, Class<?> type) {
        return Operations.calculateColumnAverageDouble(objects, columnName, type);
    }

    /**
     * Function for counting how many times an int reference value is found inside the datafile.
     * @param columnName - Name of the column you want to search through
     * @param type - Type of class that the objects consist of
     * @param refValue - Reference-value used for checking
     * @return - How many times a value is found in the data-file
     */
    @Override
    public int countIntInColumn(String columnName, Class<?> type, int refValue) {
        return Operations.countIntValue(objects, columnName, type, refValue);
    }


    /**
     * Function for counting how many times a double reference value is found inside the datafile.
     * @param columnName - Name of the column you want to search through
     * @param type - Type of class that the objects consist of
     * @param refValue - Reference-value used for checking
     * @return - How many times a value is found in the data-file
     */
    @Override
    public int countDoubleInColumn(String columnName, Class<?> type, double refValue) {
        return Operations.countDoubleValue(objects, columnName, type, refValue);
    }


    /**
     * Function for counting how many times a string reference value is found inside the datafile.
     * @param columnName - Name of the column you want to search through
     * @param type - Type of class that the objects consist of
     * @param refValue - Reference-value used for checking
     * @return - How many times a value is found in the data-file
     */
    @Override
    public int countStringInColumn(String columnName, Class<?> type, String refValue) {
        return Operations.countStringValue(objects, columnName, type, refValue);
    }
}
