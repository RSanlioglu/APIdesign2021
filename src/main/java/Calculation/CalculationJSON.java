package Calculation;

import DataAccess.DataAccessJSON;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class CalculationJSON implements ICalculation{
    DataAccessJSON json;

    public CalculationJSON(DataAccessJSON json) {
        this.json = json;
    }


    @Override
    public int CalculateColumnSumInt(String columnName, Class type) throws NoSuchFieldException, IllegalAccessException, IOException {
        List<Object> objects = json.getAllObjects();

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

    @Override
    public double CalculateColumnSumDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();

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
    public double CalculateColumnAverageInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();

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
    public double CalculateColumnAverageDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();

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
    public int CalculateColumnMinInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();

        Field field = type.getDeclaredField(columnName);
        field.setAccessible(true);

        int min = Integer.MAX_VALUE;

        for (Object o : objects) {
            Object value = field.get(o);
            int intVal = (int) value;

            if (min > intVal)
                min = intVal;
        }

        return min;    }

    @Override
    public int calculateColumnMaxInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();
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

    @Override
    public double CalculateColumnMinDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();

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
    public double calculateColumnMaxDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();
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

    @Override
    public int CountIntValue(String columnName, Class type, int refValue) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();

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
    public int CountStringValue(String columnName, Class type, String refValue) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();

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

    @Override
    public int CountdoubleValue(String columnName, Class type, double refValue) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Object> objects = json.getAllObjects();

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
