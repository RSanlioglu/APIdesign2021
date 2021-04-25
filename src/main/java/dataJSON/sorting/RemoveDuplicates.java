package dataJSON.sorting;

import java.util.LinkedHashSet;
import java.util.List;

class RemoveDuplicates {
    static LinkedHashSet<Integer> removeDuplicatesInt(int[] a) {
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        for (int j : a) set.add(j);
        return set;
    }

    static LinkedHashSet<Double> removeDuplicatesDouble(double[] a) {
        LinkedHashSet<Double> set = new LinkedHashSet<Double>();
        for (double j : a) set.add(j);
        return set;
    }

    static LinkedHashSet<String> removeDuplicatesString(List<String> a) {
        return new LinkedHashSet<String>(a);
    }

}
