package Sorting;

import java.util.List;

public interface ISorting {
    /**
     * Sorting the objects received from the data-file by their int value, ascending.
     * @param fieldName - Name of field that contains the int values
     * @return - List of objects sorted
     */
    <T> List<T> sortIntASC(String fieldName);

    /**
     * Sorting the objects received from the data-file by their int value, descending.
     * @param fieldName - Name of field that contains the int values
     * @return - List of objects sorted
     */
    <T> List<T> sortIntDESC(String fieldName);

    /**
     * Sorting the objects received from the data-file by their double value, ascending.
     * @param fieldName - Name of field that contains the double values
     * @return - List of objects sorted
     */
    <T> List<T> sortDoubleASC(String fieldName);

    /**
     * Sorting the objects received from the data-file by their double value, descending.
     * @param fieldName - Name of field that contains the double values
     * @return - List of objects sorted
     */
    <T> List<T> sortDoubleDESC(String fieldName);

    /**
     * Sorting the objects received from the data-file by their string value, ascending.
     * @param fieldName - Name of field that contains the string values
     * @return - List of objects sorted
     */
    <T> List<T> sortStringAlphabeticalASC(String fieldName);

    /**
     * Sorting the objects received from the data-file by their string value, descending.
     * @param fieldName - Name of field that contains the string values
     * @return - List of objects sorted
     */
    <T> List<T> sortStringAlphabeticalDESC(String fieldName);
}
