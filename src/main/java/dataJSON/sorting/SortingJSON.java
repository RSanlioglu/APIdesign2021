package dataJSON.sorting;

import DataAccess.DataAccessJSON;

import java.util.*;
import java.lang.reflect.Field;

public class SortingJSON implements ISortingJSON{
    DataAccessJSON json;
    Class type;

    public SortingJSON(DataAccessJSON json, Class type) {
        this.json = json;
        this.type = type;
    }

    /**
     * The client sends in a string for the fieldname. The key values will be sorted ascending
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortIntASC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<Integer, List<Object>> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            int[] intToBeSorted = new int[objects.size()];
            for(int i = 0; i < objects.size(); i++) {
                List<Object> list = new ArrayList<>();
                intToBeSorted[i] = (int) field.get(objects.get(i));
                for (Object object : objects) {
                    if (field.getInt(objects.get(i)) == field.getInt(object)) {
                        list.add(object);
                    }
                }
                objectsMap.put(field.getInt(objects.get(i)), list);
            }
            SortingAlgorithm.mergeSortINT(intToBeSorted, 0, intToBeSorted.length - 1);
            LinkedHashSet<Integer> set = RemoveDuplicates.removeDuplicatesInt(intToBeSorted);
            for(int i : set) {
                sortedList.addAll(objectsMap.get(i));
            }


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    /**
     * The client sends in a String of fieldname. The key values will be sorted where the int value is descending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortIntDESC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<Integer, List<Object>> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            int[] intToBeSorted = new int[objects.size()];
            for(int i = 0; i < objects.size(); i++) {
                List<Object> list = new ArrayList<>();
                intToBeSorted[i] = (int) field.get(objects.get(i));
                for(Object object : objects) {
                    if(field.getInt(objects.get(i)) == field.getInt(object)) {
                        list.add(object);
                    }
                }
                objectsMap.put(field.getInt(objects.get(i)), list);
            }
            SortingAlgorithm.mergeSortINT(intToBeSorted, 0, intToBeSorted.length - 1);
            LinkedHashSet<Integer> set = RemoveDuplicates.removeDuplicatesInt(intToBeSorted);
            for(int i : set) {
                sortedList.addAll(objectsMap.get(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Collections.reverse(sortedList);

        return sortedList;
    }

    /**
     * The client sends in a string for the field name. The key values will be sorted where the double value is ascending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortDoubleASC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<Double, List<Object>> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            double[] doubleToBeSorted = new double[objects.size()];
            for(int i = 0; i < objects.size(); i++) {
                List<Object> list = new ArrayList<>();
                doubleToBeSorted[i] = (double) field.get(objects.get(i));
                for(Object object : objects) {
                    if(field.getDouble(objects.get(i)) == field.getDouble(object)) {
                        list.add(object);
                    }
                }
                objectsMap.put(field.getDouble(objects.get(i)), list);
            }
            SortingAlgorithm.mergeSortDouble(doubleToBeSorted, 0, doubleToBeSorted.length-1);
            LinkedHashSet<Double> set = RemoveDuplicates.removeDuplicatesDouble(doubleToBeSorted);
            for(double i : set) {
                sortedList.addAll(objectsMap.get(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    /**
     * The client sends in a string for the fieldname. The key values will be sorted where the double value is descending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortDoubleDESC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<Double, List<Object>> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field;
        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            double[] doubleToBeSorted = new double[objects.size()];
            for(int i = 0; i < objects.size(); i++) {
                List<Object> list = new ArrayList<>();
                doubleToBeSorted[i] = (double) field.get(objects.get(i));
                for(Object object : objects) {
                    if(field.getDouble(objects.get(i)) == field.getDouble(object)) {
                        list.add(object);
                    }
                }
                objectsMap.put(field.getDouble(objects.get(i)), list);
            }
            SortingAlgorithm.mergeSortDouble(doubleToBeSorted, 0, doubleToBeSorted.length-1);
            LinkedHashSet<Double> set = RemoveDuplicates.removeDuplicatesDouble(doubleToBeSorted);
            for(double i : set) {
                sortedList.addAll(objectsMap.get(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Collections.reverse(sortedList);

        return sortedList;
    }

    /**
     * The client sends a string for a fieldname. The key values will be sorted where the string value is ascending
     * with a binary search tree algorithm and a list of sorted objects is then returned to the client.
     * Note! Changes are not printed on datafile until client writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortStringAlphabeticalASC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<String, List<Object>> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field;

        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            List<String> stringToBeSorted = new ArrayList<>();
            for(int i = 0; i < objects.size(); i++) {
                List<Object> list = new ArrayList<>();
                stringToBeSorted.add(i, (String) field.get(objects.get(i)));
                for(Object object : objects) {
                    if(field.get(objects.get(i)).toString().equals(field.get(object).toString())) {
                        list.add(object);
                    }
                }
                objectsMap.put((String) field.get(objects.get(i)), list);
            }
            SortingAlgorithm.bstSortString(stringToBeSorted);
            LinkedHashSet<String> set = RemoveDuplicates.removeDuplicatesString(stringToBeSorted);
            for(String s : set) {
                sortedList.addAll(objectsMap.get(s));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    /**
     * The client sends a string for a fieldName. The key values will be sorted where the string value is descending
     * with a binary search tree algorithm and a list of sorted objects is then returned to the client.
     * Note! Changes are not printed on datafile until client writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    public List<? extends Object> sortStringAlphabeticalDESC(String fieldName) {
        List<Object> sortedList = new ArrayList<>();

        HashMap<String, List<Object>> objectsMap = new HashMap<>();
        List<Object> objects = json.getAllObjects();

        Field field;

        try {
            field = type.getDeclaredField(fieldName);
            field.setAccessible(true);

            List<String> stringToBeSorted = new ArrayList<>();
            for(int i = 0; i < objects.size(); i++) {
                List<Object> list = new ArrayList<>();
                stringToBeSorted.add(i, (String) field.get(objects.get(i)));
                for(Object object : objects) {
                    if(field.get(objects.get(i)).toString().equals(field.get(object).toString())) {
                        list.add(object);
                    }
                }
                objectsMap.put((String) field.get(objects.get(i)), list);
            }
            SortingAlgorithm.bstSortString(stringToBeSorted);
            LinkedHashSet<String> set = RemoveDuplicates.removeDuplicatesString(stringToBeSorted);
            for(String s : set) {
                sortedList.addAll(objectsMap.get(s));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Collections.reverse(sortedList);

        return sortedList;
    }
}
