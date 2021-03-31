package dataJSON.sorting;

import java.util.*;

public class SortingJSON implements ISortingJSON {

    public SortingJSON() {

    }

    /**
     * The client sends in a hashmap of data. The key values will be sorted
     * with a merge-sort algorithm and a list of sorted objects is then returned
     * to the client. Note! Changes are not printed on datafile until client
     * writes it on there using the DataAccess.
     *
     * @param mapOfObjects - A hashmap containing objects and the keys to the objects
     * @return - A list of objects that are sorted by the key integer values (Ascending)
     */

    public List<? extends Object> sortIntASC(HashMap<Integer, ?> mapOfObjects) {
        List<Object> sortedList = new ArrayList<>();

        List<Integer> intToBeSorted = new ArrayList<>(mapOfObjects.keySet());

        for(Integer i : sort(intToBeSorted)) {
            sortedList.add(mapOfObjects.get(i));
        }

        return sortedList;
    }

    private List<Integer> sort(List<Integer> integerList) {
        int n = integerList.size();
        int min, tmp;

        for(int i = 0; i < n-1; i++) {
            min = i;
            for(int j = i+1; j < n; j++) {
                if(integerList.get(j) < integerList.get(min)) {
                    min = j;
                }
            }
            tmp = integerList.get(min);
            integerList.set(min, integerList.get(i));
            integerList.set(i, tmp);
        }

        return integerList;
    }
}
