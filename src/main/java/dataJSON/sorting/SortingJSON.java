package dataJSON.sorting;

import dataJSON.dataAccess.DataAccessJSON;

import java.util.*;
import java.lang.reflect.Field;


public class SortingJSON {
    //TODO: FIX THE INTERFACE
    DataAccessJSON json;
    Class type;

    public SortingJSON(DataAccessJSON json, Class type) {
        this.json = json;
        this.type = type;
    }

    /**
     * The client sends in a hashmap of data. The key values will be sorted ascending
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortIntASC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<Integer, Object> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            int[] intToBeSorted = new int[objects.size()];
            int j = 0;
            for(Object o : objects) {
                intToBeSorted[j++] = (int) field.get(o);
                objectsMap.put((int) field.get(o), o);
            }

            SortingAlgorithm.mergeSortINT(intToBeSorted, 0, intToBeSorted.length - 1);

            for(int i : intToBeSorted) {
                sortedList.add(objectsMap.get(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    /**
     * The client sends in a hashmap of data. The key values will be sorted where the int value is descending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortIntDESC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<Integer, Object> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            int[] intToBeSorted = new int[objects.size()];
            int j = 0;
            for(Object o : objects) {
                intToBeSorted[j++] = (int) field.get(o);
                objectsMap.put((int) field.get(o), o);
            }

            SortingAlgorithm.mergeSortINT(intToBeSorted, 0, intToBeSorted.length - 1);

            for(int i : intToBeSorted) {
                sortedList.add(objectsMap.get(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Collections.reverse(sortedList);

        return sortedList;
    }

    /**
     * The client sends in a hashmap of data. The key values will be sorted where the double value is ascending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortDoubleASC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<Double, Object> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            double[] doubleToBeSorted = new double[objects.size()];
            int j = 0;
            for(Object o : objects) {
                doubleToBeSorted[j++] = (double) field.get(o);
                objectsMap.put((double) field.get(o), o);
            }

            SortingAlgorithm.mergeSortDouble(doubleToBeSorted, 0, doubleToBeSorted.length - 1);

            for(double d : doubleToBeSorted) {
                sortedList.add(objectsMap.get(d));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    /**
     * The client sends in a hashmap of data. The key values will be sorted where the double value is descending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortDoubleDESC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<Double, Object> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field = null;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            double[] doubleToBeSorted = new double[objects.size()];
            int j = 0;
            for(Object o : objects) {
                doubleToBeSorted[j++] = (double) field.get(o);
                objectsMap.put((double) field.get(o), o);
            }

            SortingAlgorithm.mergeSortDouble(doubleToBeSorted, 0, doubleToBeSorted.length - 1);

            for(double d : doubleToBeSorted) {
                sortedList.add(objectsMap.get(d));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Collections.reverse(sortedList);

        return sortedList;
    }

    /**
     * The client sends a hashmap of data. The key values will be sorted where the string value is ascending
     * with a binary search tree algorithm and a list of sorted objects is then returned to the client.
     * Note! Changes are not printed on datafile until client writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortStringAlphabeticalASC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<String, Object> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field = null;

        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            for(Object o : objects) {
                objectsMap.put((String) field.get(o), o);
            }
            List<String> stringToBeSorted = new ArrayList<>(objectsMap.keySet());

            SortingAlgorithm.bstSortString(stringToBeSorted);

            for(String s : stringToBeSorted) {
                sortedList.add(objectsMap.get(s));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    /**
     * The client sends a hashmap of data. The key values will be sorted where the string value is descending
     * with a binary search tree algorithm and a list of sorted objects is then returned to the client.
     * Note! Changes are not printed on datafile until client writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortStringAlphabeticalDESC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<String, Object> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field = null;

        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            for(Object o : objects) {
                objectsMap.put((String) field.get(o), o);
            }
            List<String> stringToBeSorted = new ArrayList<>(objectsMap.keySet());

            SortingAlgorithm.bstSortString(stringToBeSorted);

            for(String s : stringToBeSorted) {
                sortedList.add(objectsMap.get(s));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Collections.reverse(sortedList);

        return sortedList;
    }
}



