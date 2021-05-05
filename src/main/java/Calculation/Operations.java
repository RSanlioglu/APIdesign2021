package Calculation;

import java.lang.reflect.Field;
import java.util.List;

abstract class Operations {

    /**
     * Private function that will retrieve fields given by the client.
     * @param columnName - Name of the column the client wants to focus on the datafile.
     * @param type - Type of object the client is working with
     * @return - Will return a Field.
     */
    private static Field getField(String columnName, Class<?> type) {
        Field field = null;
        try {
            field = type.getDeclaredField(columnName);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return field;
    }

    /**
     * Private function that will retrieve the sum of a list of doubles
     * @param objects - List of objects to retrieve the sum of
     * @param columnName - Name of the column to calculate on
     * @param type - Type of class the objects consist of
     * @return - Sum of doubles in the list of objects
     */
    private static double getSumDouble(List<?> objects, String columnName, Class<?> type) {
        double sum = 0;

        for (Object o : objects) {
            double value = 0;
            try {
                value = getField(columnName, type).getDouble(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sum += value;
        }
        return sum;
    }

    /**
     * Will calculate all of the int values from given column name. The column must consist of datatype int
     * @param columnName - Name of the column the client wants to get the sum value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the sum value of the entire column
     */
    public static int calculateColumnSumInt(List<?> objects, String columnName, Class<?> type){
        int sum = 0;

        for (Object o : objects) {
            int value = 0;
            try {
                value = getField(columnName, type).getInt(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sum += value;
        }

        return sum;
    }

    /**
     * Will calculate all of the double values from given column name. The column must consist of datatype double
     * @param columnName - Name of the column the client wants to get the sum value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the sum value of the entire column
     */
    public static double calculateColumnSumDouble(List<?> objects, String columnName, Class<?> type) {
        return getSumDouble(objects, columnName, type);
    }

    /**
     * Will calculate the average int in a column. The column must consist of datatype int
     * @param columnName - Name of the column the client wants to get the average value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the average value of the entire column
     */
    public static double calculateColumnAverageInt(List<?> objects, String columnName, Class<?> type) {
        double sum = 0;

        for (Object o : objects) {
            int value = 0;
            try {
                value = getField(columnName, type).getInt(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sum += value;
        }

        return sum / ((objects.size() == 0) ? 1 : objects.size());
    }

    /**
     * Will calculate the average double in a column. The column must consist of datatype double.
     * @param columnName - Name of the column the client wants to get the average value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the average value of the entire column
     */
    public static double calculateColumnAverageDouble(List<?> objects, String columnName, Class<?> type) {
        double sum = getSumDouble(objects, columnName, type);

        return sum / ((objects.size() == 0) ? 1 : objects.size());
    }

    /**
     * Will find the min integer value of a column. The column given must consist of datatype integer.
     * @param columnName - Name of the column the client wants to get the min value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the min value of the entire column
     */
    public static int calculateColumnMinInt(List<?> objects, String columnName, Class<?> type) {
        int min = Integer.MAX_VALUE;

        for (Object o : objects) {
            int value = 0;
            try {
                value = getField(columnName, type).getInt(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (min > value)
                min = value;
        }

        return min;
    }

    /**
     * Will find the max integer value of a column. The column given must consist of datatype integer.
     * @param columnName - Name of the column the client wants to get the min value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the max value of the entire column
     */
    public static int calculateColumnMaxInt(List<?> objects, String columnName, Class<?> type) {
        int max = Integer.MIN_VALUE;

        for(Object o : objects) {
            int value = 0;
            try {
                value = getField(columnName, type).getInt(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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
    public static double calculateColumnMinDouble(List<?> objects, String columnName, Class<?> type) {
        double min = Double.MAX_VALUE;

        for (Object o : objects) {
            double value = 0;
            try {
                value = getField(columnName, type).getDouble(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (min > value)
                min = value;
        }

        return min;
    }

    /**
     * Will find the max double value of a column. The column given must consist of datatype double.
     * @param columnName - Name of the column the client wants to get the min value of
     * @param type - Class type of the objects. Must be created by client
     * @return - returns the max value of the entire column
     */
    public static double calculateColumnMaxDouble(List<?> objects, String columnName, Class<?> type) {
        double max = Double.MIN_VALUE;

        for(Object o : objects) {
            double value = 0;
            try {
                value = getField(columnName, type).getDouble(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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
    public static int countIntValue(List<?> objects, String columnName, Class<?> type, int refValue) {
        int count = 0;

        for (Object o : objects) {
            int value = 0;
            try {
                value = getField(columnName, type).getInt(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == refValue)
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
    public static int countStringValue(List<?> objects, String columnName, Class<?> type, String refValue) {
        int count = 0;

        for (Object o : objects) {
            String value = null;
            try {
                value = (String) getField(columnName, type).get(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value.equals(refValue))
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
    public static int countDoubleValue(List<?> objects, String columnName, Class<?> type, double refValue) {
        int count = 0;

        for (Object o : objects) {
            double value = 0;
            try {
                value = getField(columnName, type).getDouble(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == refValue)
                count++;
        }
        return count;
    }
}
