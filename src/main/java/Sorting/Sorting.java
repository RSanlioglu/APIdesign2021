package Sorting;

import DataAccess.DataAccessXML;
import DataAccess.DataAccessJSON;
import DataAccess.DataAccessCSV;

import java.util.List;

/**
 * Factory for Sorting instance
 */
public class Sorting implements  ISorting{
    /**
     * Type of class that the list of objects consist of
     */
    Class<?> type;
    /**
     * List of objects used for Sorting
     */
    private static List<Object> objects;

    /**
     * Constructor for creating an instance of Sorting class, supporting a DataAccess operating on XML files
     * @param xml - XML DataAccess
     * @param type - Type of objects inside the class
     * @throws NullPointerException - If the DataAccess is null, exception is thrown
     */
    public Sorting(DataAccessXML xml, Class<?> type) throws NullPointerException {
        if(xml == null) {
            throw new NullPointerException();
        } else {
            objects = xml.getAllObjects();
            this.type = type;
        }
    }

    /**
     * Constructor for creating an instance of Sorting class, supporting a DataAccess operating on CSV files
     * @param csv - CSV DataAccess
     * @param type - Type of objects inside the class
     * @throws NullPointerException - If the DataAccess is null, exception is thrown
     */
    public Sorting(DataAccessCSV csv, Class<?> type) {
        if(csv == null) {
            throw new NullPointerException();
        } else {
            objects = csv.getAllObjects();
            this.type = type;
        }
    }

    /**
     * Constructor for creating an instance of Sorting class, supporting a DataAccess operating on JSON files
     * @param json - XML DataAccess
     * @param type - Type of objects inside the class
     * @throws NullPointerException - If the DataAccess is null, exception is thrown
     */
    public Sorting(DataAccessJSON json, Class<?> type) {
        if(json == null) {
            throw new NullPointerException();
        } else {
            objects = json.getAllObjects();
            this.type = type;
        }
    }

    /**
     * The client sends in a string for the field-name. The key values will be sorted ascending
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortIntASC(String fieldName) {
        return SortingOperations.sortIntegersAscending(fieldName, objects, type);
    }

    /**
     * The client sends in a String of field-name. The key values will be sorted where the int value is descending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortIntDESC(String fieldName) {
        return SortingOperations.sortIntegerDescending(fieldName, objects, type);
    }

    /**
     * The client sends in a string for the field-name. The key values will be sorted where the double value is ascending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortDoubleASC(String fieldName) {
        return SortingOperations.sortDoubleAscending(fieldName, objects, type);
    }

    /**
     * The client sends in a string for the field-name. The key values will be sorted where the double value is descending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortDoubleDESC(String fieldName) {
        return SortingOperations.sortDoubleDescending(fieldName, objects, type);
    }

    /**
     * The client sends a string for a field-name. The key values will be sorted where the string value is ascending
     * with a binary search tree algorithm and a list of sorted objects is then returned to the client.
     * Note! Changes are not printed on datafile until client writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortStringAlphabeticalASC(String fieldName) {
        return SortingOperations.sortStringAlphabeticalAscending(fieldName, objects, type);
    }

    /**
     * The client sends a string for a field-name. The key values will be sorted where the string value is descending
     * with a binary search tree algorithm and a list of sorted objects is then returned to the client.
     * Note! Changes are not printed on datafile until client writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortStringAlphabeticalDESC(String fieldName) {
        return SortingOperations.sortStringAlphabeticalDescending(fieldName, objects, type);
    }
}
