package Sorting;

import DataAccess.DataAccessCSV;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Factory for SortingCSV.
 */
public class SortingCSV implements ISorting{
    DataAccessCSV csv;
    Class<?> type;

    /**
     * Constructor used to create an instance of SortingCSV class
     * @param csv - DataAccess used to acces a CSV file
     * @param type - Type of objects inside the CSV file
     */
    public SortingCSV(DataAccessCSV csv, Class<?> type) {
        this.csv = csv;
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
    @Override
    public <T> List<T> sortIntASC(String fieldName) {
        return SortingOperations.sortIntegersAscending(fieldName, csv.getAllObjects(), type);
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
    @Override
    public <T> List<T> sortIntDESC(String fieldName) {
        return SortingOperations.sortIntegerDescending(fieldName, csv.getAllObjects(), type);
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
    @Override
    public <T> List<T> sortDoubleASC(String fieldName) {
        return SortingOperations.sortDoubleAscending(fieldName, csv.getAllObjects(), type);
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
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> sortDoubleDESC(String fieldName) {
        return SortingOperations.sortDoubleDescending(fieldName, csv.getAllObjects(), type);
    }

    /**
     * The client sends a string for a fieldname. The key values will be sorted where the string value is ascending
     * with a binary search tree algorithm and a list of sorted objects is then returned to the client.
     * Note! Changes are not printed on datafile until client writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortStringAlphabeticalASC(String fieldName) {
        return SortingOperations.sortStringAlphabeticalAscending(fieldName, csv.getAllObjects(), type);
    }

    /**
     * The client sends a string for a fieldName. The key values will be sorted where the string value is descending
     * with a binary search tree algorithm and a list of sorted objects is then returned to the client.
     * Note! Changes are not printed on datafile until client writes it on there using the DataAccess.
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortStringAlphabeticalDESC(String fieldName) {
        return SortingOperations.sortStringAlphabeticalDescending(fieldName, csv.getAllObjects(), type);
    }
}
