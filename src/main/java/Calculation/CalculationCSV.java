package Calculation;

import DataAccess.DataAccessCSV;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Factory for creating calculation instances for CSV files.
 * This class implements the interface ICalculation.
 */
public class CalculationCSV implements ICalculation {
    DataAccessCSV csv;

    /**
     * Constructor for CSV-calculations.
     * @param csv - Data-access that the client has created and sent to param
     */
    public CalculationCSV(DataAccessCSV csv) {
        this.csv = csv;
    }

    /**
     * Will calculate all of the int values from given column name. The column must consist of datatype int
     * @param columnName - Name of the column the client wants to get the sum value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the sum value of the entire column
     */
    @Override
    public int CalculateColumnSumInt(String columnName, Class type) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        int sum = 0;

        for (Object o : objects) {
            Object value = field.get(o); //TODO: See if you can use getINT
            int intVal = (int) value;
            sum += intVal;
        }

        return sum;
    }

    /**
     * Will calculate all of the double values from given column name. The column must consist of datatype double
     * @param columnName - Name of the column the client wants to get the sum value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the sum value of the entire column
     */
    @Override
    public double CalculateColumnSumDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        double sum = 0;

        for (Object o : objects) {
            Object value = field.get(o);
            double intVal = (double) value;
            sum += intVal;
        }

        return sum;
    }

    /**
     * Will calculate the average int in a column. The column must consist of datatype int
     * @param columnName - Name of the column the client wants to get the average value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the average value of the entire column
     */
    @Override
    public double CalculateColumnAverageInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        int sum = 0;

        for (Object o : objects) {
            Object value = field.get(o);
            int intVal = (int) value;
            sum += intVal;
        }

        return (double)sum / ((objects.size() == 0) ? 1 : objects.size());
    }

    /**
     * Will calculate the average double in a column. The column must consist of datatype double.
     * @param columnName - Name of the column the client wants to get the average value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the average value of the entire column
     */
    @Override
    public double CalculateColumnAverageDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        double sum = 0;

        for (Object o : objects) {
            Object value = field.get(o);
            double doubleVal = (double) value;
            sum += doubleVal;
        }

        return sum / ((objects.size() == 0) ? 1 : objects.size());
    }

    /**
     * Will find the min integer value of a column. The column given must consist of datatype integer.
     * @param columnName - Name of the column the client wants to get the min value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the min value of the entire column
     */
    @Override
    public int CalculateColumnMinInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        int min = Integer.MAX_VALUE;

        for (Object o : objects) {
            Object value = field.get(o);
            int intVal = (int) value;

            if (min > intVal)
                min = intVal;
        }

        return min;
    }

    /**
     * Will find the max integer value of a column. The column given must consist of datatype integer.
     * @param columnName - Name of the column the client wants to get the min value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the max value of the entire column
     */
    @Override
    public int calculateColumnMaxInt(String columnName, Class type) throws NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();
        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);
        int max = Integer.MIN_VALUE;
        for(Object o : objects) {
            int value = field.getInt(o);
            if(max < value)
                max = value;
        }
        return max;
    }

    /**
     * Will find the min double value of a column. The column given must consist of datatype double.
     * @param columnName - Name of the column the client wants to get the min value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the min value of the entire column
     */
    @Override
    public double CalculateColumnMinDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        double min = Double.MAX_VALUE;

        for (Object o : objects) {
            Object value = field.get(o);
            double doubleVal = (double) value;

            if (min > doubleVal)
                min = doubleVal;
        }

        return min;
    }

    /**
     * Will find the max double value of a column. The column given must consist of datatype double.
     * @param columnName - Name of the column the client wants to get the min value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the max value of the entire column
     */
    @Override
    public double calculateColumnMaxDouble(String columnName, Class type) throws NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();
        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);
        double max = Double.MIN_VALUE;
        for(Object o : objects) {
            double value = field.getDouble(o);
            if(max < value)
                max = value;
        }
        return max;
    }

    /**
     * Will find out how many times a specific data occurs in a column, selected by the client.
     * The column must consist of integer values
     * @param columnName - Name of the column the client wants to see for the reference value.
     * @param type - Class type of the objects. Must be created by client
     * @param refValue - The reference value that will be checked in the column selected
     * @return - Returns the amount of times the reference value is counted in the column
     */
    @Override
    public int CountIntValue(String columnName, Class type, int refValue) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        int count = 0;

        for (Object o : objects) {
            Object value = field.get(o);
            int intVal = (int) value;

            if (intVal == refValue)
                count++;
        }

        return count;
    }

    /**
     * Will find out how many times a specific data occurs in a column, selected by the client.
     * The column must consist of string values
     * @param columnName - Name of the column the client wants to see for the reference value.
     * @param type - Class type of the objects. Must be created by client
     * @param refValue - The reference value that will be checked in the column selected
     * @return - Returns the amount of times the reference value is counted in the column
     */
    @Override
    public int CountStringValue(String columnName, Class type, String refValue) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        int count = 0;

        for (Object o : objects) {
            Object value = field.get(o);
            String strVal = (String) value;

            if (strVal.equals(refValue))
                count++;
        }
        return count;
    }

    /**
     * Will find out how many times a specific data occurs in a column, selected by the client.
     * The column must consist of double values
     * @param columnName - Name of the column the client wants to see for the reference value.
     * @param type - Class type of the objects. Must be created by client
     * @param refValue - The reference value that will be checked in the column selected
     * @return - Returns the amount of times the reference value is counted in the column
     */
    @Override
    public int CountdoubleValue(String columnName, Class type, double refValue) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        int count = 0;

        for (Object o : objects) {
            Object value = field.get(o);
            double strVal = (double) value;

            if (strVal == refValue)
                count++;
        }
        return count;
    }
}
