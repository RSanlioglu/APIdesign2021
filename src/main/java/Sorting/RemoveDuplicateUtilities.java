package Sorting;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Utility class providing simple removal of duplications in a list og hash-keys used in sorting-operations
 */
final class RemoveDuplicateUtilities {
    /**
     * Will remove duplicates of int values inside an array
     * @param a Array of ints
     * @return LinkedHashSet containing key integer values without duplicates
     */
    static LinkedHashSet<Integer> removeDuplicatesInt(int[] a) {
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        for (int j : a) set.add(j);
        return set;
    }

    /**
     * Will remove duplicates of double values inside an array
     * @param a Array of double values
     * @return LinkedHashSet containing key double values without duplicates
     */
    static LinkedHashSet<Double> removeDuplicatesDouble(double[] a) {
        LinkedHashSet<Double> set = new LinkedHashSet<Double>();
        for (double j : a) set.add(j);
        return set;
    }

    /**
     * Will remove duplicates of string values inside a list
     * @param a - List of string values
     * @return - LinkedHashSet containing key string values without duplicates
     */
    static LinkedHashSet<String> removeDuplicatesString(List<String> a) {
        return new LinkedHashSet<String>(a);
    }

}
