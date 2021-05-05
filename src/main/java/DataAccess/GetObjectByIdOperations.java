package DataAccess;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Private utility class used to retrieve objects by their ID.
 * This class was constructed with an effort to minimize duplicated code.
 */
abstract class GetObjectByIdOperations {
    /**
     * Gets one object by the id reference (double).
     * @param objects - List of objects to search through
     * @param fieldName - name of field that is relevant for the operation
     * @param value - value of id to search
     * @param type - type of class that the objects consist of
     * @return - the object received by the search
     */
    public static <T> T getObjectById(List<T> objects, String fieldName, double value, Class<?> type) {
        Field field;
        T obj = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            for(T o : objects) {
                if(field.getDouble(o) == value) {
                    obj = o;
                    break;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Gets one object by the id reference (string).
     * @param objects - List of objects to search through
     * @param fieldName - name of field that is relevant for the operation
     * @param value - value of id to search
     * @param type - type of class that the objects consist of
     * @return - the object received by the search
     */
    public static <T> T getObjectById(List<T> objects, String fieldName, String value, Class<?> type) {
        Field field;
        T obj = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            for(T o : objects) {
                if(((String) field.get(o)).equals(value)) {
                    obj = o;
                    break;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Gets one object by the id reference (int).
     * @param objects - List of objects to search through
     * @param fieldName - name of field that is relevant for the operation
     * @param value - value of id to search
     * @param type - type of class that the objects consist of
     * @return - the object received by the search
     */
    public static <T> T getObjectById(List<T> objects, String fieldName, int value, Class<?> type) {
        Field field;
        T obj = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            for(T o : objects) {
                if(field.getInt(o) == value) {
                    obj = o;
                    break;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
