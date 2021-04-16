package dataCSV.calculation;

import dataCSV.dataAcces.DataAccess;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class Calculation implements ICalculation {

    @Override
    public int CalculateColumnSumInt(DataAccess csv, String fileName, String columnName, Class type) throws NoSuchFieldException, IllegalAccessException, IOException {
       // DataAccess csv = new DataAccess();
        List<Object> objects = csv.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        int sum = 0;

        for (Object o : objects) {
            Object value = field.get(o);
            int intVal = (int) value;
            sum += intVal;
        }

        return sum;
    }

   @Override
    public double CalculateColumnSumDouble(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        //DataAccess csv = new DataAccess();
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



    @Override
    public double CalculateColumnAverageInt(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        //DataAccess csv = new DataAccess();
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

    @Override
    public double CalculateColumnAverageDouble(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        //DataAccess csv = new DataAccess();
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

    @Override
    public int CalculateColumnMinInt(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        //DataAccess csv = new DataAccess();
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

    @Override
    public double CalculateColumnMinDouble(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        //DataAccess csv = new DataAccess();
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

    @Override
    public int CountIntValue(DataAccess csv, String fileName, String columnName, Class type, int refValue) throws IOException, NoSuchFieldException, IllegalAccessException {
        //DataAccess csv = new DataAccess();
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

    @Override
    public int CountStringValue(DataAccess csv, String fileName, String columnName, Class type, String refValue) throws IOException, NoSuchFieldException, IllegalAccessException {
        //DataAccess csv = new DataAccess();
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
}
