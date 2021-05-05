package Sorting;

import DataAccess.DataAccessXML;

import java.util.List;

/**
 * Factory for SortingXML.
 */
public class SortingXML implements ISorting {
    DataAccessXML xml;
    Class<?> type;

    /**
     * Constructor used to create an instance of SortingXML class.
     * @param xml - DataAccess used to access a XML file
     * @param type - Type of objects in XML file
     */
    public SortingXML(DataAccessXML xml, Class<?> type) {
        this.xml = xml;
        this.type = type;
    }

    /**
     * The client sends in a string for the field-name. The key values will be sorted ascending
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortIntASC(String fieldName) {
        return SortingOperations.sortIntegersAscending(fieldName, xml.getAllObjects(), type);
    }

    /**
     * The client sends in a String of field-name. The key values will be sorted where the int value is descending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortIntDESC(String fieldName) {
        return SortingOperations.sortIntegerDescending(fieldName, xml.getAllObjects(), type);
    }

    /**
     * The client sends in a string for the field-name. The key values will be sorted where the double value is ascending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortDoubleASC(String fieldName) {
        return SortingOperations.sortDoubleAscending(fieldName, xml.getAllObjects(), type);
    }

    /**
     * The client sends in a string for the field-name. The key values will be sorted where the double value is descending,
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param fieldName - sort by the selected field that the client inputs
     * @return - A list of objects that are sorted by the key values
     */
    @Override
    public <T> List<T> sortDoubleDESC(String fieldName) {
        return SortingOperations.sortDoubleDescending(fieldName, xml.getAllObjects(), type);
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
        return SortingOperations.sortStringAlphabeticalAscending(fieldName, xml.getAllObjects(), type);
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
        return SortingOperations.sortStringAlphabeticalDescending(fieldName, xml.getAllObjects(), type);
    }
}
