package Sorting;

import java.lang.reflect.Field;
import java.util.*;

abstract class SortingOperations {

    /**
     * Sorts the list of integers ascending
     * @param fieldName - Name of field consisting the int values
     * @param allObjects - List of objects
     * @param type - Type of class that the object consist of
     * @return - List of integers sorted ascending
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> sortIntegersAscending(String fieldName, List<Object> allObjects, Class<?> type) {
        List<T> sortedList = new ArrayList<>();

        HashMap<Integer, List<Object>> objectsMap = new HashMap<>();

        Field field;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            int[] intToBeSorted = new int[allObjects.size()];
            for(int i = 0; i < allObjects.size(); i++) {
                List<Object> list = new ArrayList<>();
                intToBeSorted[i] = (int) field.get(allObjects.get(i));
                for (Object object : allObjects) {
                    if (field.getInt(allObjects.get(i)) == field.getInt(object)) {
                        list.add(object);
                    }
                }
                objectsMap.put(field.getInt(allObjects.get(i)), list);
            }
            SortingAlgorithm.mergeSortINT(intToBeSorted, 0, intToBeSorted.length - 1);
            LinkedHashSet<Integer> set = RemoveDuplicateUtilities.removeDuplicatesInt(intToBeSorted);
            for(int i : set) {
                sortedList.addAll((Collection<? extends T>) objectsMap.get(i));
            }


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    /**
     * Sorts the list of integers descending
     * @param fieldName - Name of field consisting the int values
     * @param allObjects - List of objects
     * @param type - Type of class that the object consist of
     * @return - List of integers sorted descending
     */
    public static <T> List<T> sortIntegerDescending(String fieldName, List<Object> allObjects, Class<?> type) {
        List<T> sortedList = sortIntegersAscending(fieldName, allObjects, type);
        Collections.reverse(sortedList);

        return sortedList;
    }

    /**
     * Sorts the list of doubles ascending
     * @param fieldName - Name of field consisting the double values
     * @param allObjects - List of objects
     * @param type - Type of class that the object consist of
     * @return - List of doubles sorted ascending
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> sortDoubleAscending(String fieldName, List<Object> allObjects, Class<?> type) {
        List<T> sortedList = new ArrayList<>();

        HashMap<Double, List<Object>> objectsMap = new HashMap<>();

        Field field;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            double[] doubleToBeSorted = new double[allObjects.size()];
            for(int i = 0; i < allObjects.size(); i++) {
                List<Object> list = new ArrayList<>();
                doubleToBeSorted[i] = (double) field.get(allObjects.get(i));
                for(Object object : allObjects) {
                    if(field.getDouble(allObjects.get(i)) == field.getDouble(object)) {
                        list.add(object);
                    }
                }
                objectsMap.put(field.getDouble(allObjects.get(i)), list);
            }
            SortingAlgorithm.mergeSortDouble(doubleToBeSorted, 0, doubleToBeSorted.length-1);
            LinkedHashSet<Double> set = RemoveDuplicateUtilities.removeDuplicatesDouble(doubleToBeSorted);
            for(double i : set) {
                sortedList.addAll((Collection<? extends T>) objectsMap.get(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    /**
     * Sorts the list of doubles descending
     * @param fieldName - Name of field consisting the double values
     * @param allObjects - List of objects
     * @param type - Type of class that the object consist of
     * @return - List of doubles sorted descending
     */
    public static <T> List<T> sortDoubleDescending(String fieldName, List<Object> allObjects, Class<?> type) {
        List<T> sortedList = sortDoubleAscending(fieldName, allObjects, type);
        Collections.reverse(sortedList);

        return sortedList;
    }

    /**
     * Sorts the list of string ascending
     * @param fieldName - Name of field consisting the string values
     * @param allObjects - List of objects
     * @param type - Type of class that the object consist of
     * @return - List of strings sorted ascending
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> sortStringAlphabeticalAscending(String fieldName, List<Object> allObjects, Class<?> type) {
        List<T> sortedList = new ArrayList<>();

        HashMap<String, List<Object>> objectsMap = new HashMap<>();

        Field field;

        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            List<String> stringToBeSorted = new ArrayList<>();
            for(int i = 0; i < allObjects.size(); i++) {
                List<Object> list = new ArrayList<>();
                stringToBeSorted.add(i, (String) field.get(allObjects.get(i)));
                for(Object object : allObjects) {
                    if(field.get(allObjects.get(i)).toString().equals(field.get(object).toString())) {
                        list.add(object);
                    }
                }
                objectsMap.put((String) field.get(allObjects.get(i)), list);
            }
            SortingAlgorithm.bstSortString(stringToBeSorted);
            LinkedHashSet<String> set = RemoveDuplicateUtilities.removeDuplicatesString(stringToBeSorted);
            for(String s : set) {
                sortedList.addAll((Collection<? extends T>) objectsMap.get(s));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    /**
     * Sorts the list of string descending
     * @param fieldName - Name of field consisting the string values
     * @param allObjects - List of objects
     * @param type - Type of class that the object consist of
     * @return - List of strings sorted descending
     */
    public static <T> List<T> sortStringAlphabeticalDescending(String fieldName, List<Object> allObjects, Class<?> type) {
        List<T> sortedList = sortStringAlphabeticalAscending(fieldName, allObjects, type);
        Collections.reverse(sortedList);

        return sortedList;
    }
}
