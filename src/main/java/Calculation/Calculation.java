package Calculation;

import DataAccess.DataAccessCSV;
import DataAccess.DataAccessJSON;
import DataAccess.DataAccessXML;

import java.util.List;

/**
 * Factory for creating calculation instances for CSV files.
 * This class implements the interface ICalculation.
 */
public class Calculation {
    public enum Method {
        SUM,
        MIN,
        MAX
    }

    private static List<Object> objects;

    public Calculation(DataAccessXML xml) throws NullPointerException {
        if(xml == null) {
            throw new NullPointerException();
        } else {
            objects = xml.getAllObjects();
        }

    }

    public Calculation(DataAccessJSON json) throws NullPointerException {
        if(json == null) {
            throw new NullPointerException();
        } else {
            objects = json.getAllObjects();
        }
    }


    public Calculation(DataAccessCSV csv) throws NullPointerException {
        if(csv == null) {
            throw new NullPointerException();
        } else {
            objects = csv.getAllObjects();
        }
    }

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

    public double calculateColumnAverageInt(String columnName, Class<?> type) {
        return Operations.calculateColumnAverageInt(objects, columnName, type);
    }

    public double calculateColumnAverageDouble(String columnName, Class<?> type) {
        return Operations.calculateColumnAverageDouble(objects, columnName, type);
    }

    public int countIntInColumn(String columnName, Class<?> type, int refValue) {
        return Operations.countIntValue(objects, columnName, type, refValue);
    }

    public int countDoubleInColumn(String columnName, Class<?> type, double refValue) {
        return Operations.countDoubleValue(objects, columnName, type, refValue);
    }

    public int countStringInColumn(String columnName, Class<?> type, String refValue) {
        return Operations.countStringValue(objects, columnName, type, refValue);
    }
}
