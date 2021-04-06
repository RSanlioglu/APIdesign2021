package dataJSON.sorting;

import java.util.HashMap;
import java.util.List;

public interface ISortingJSON {
    List<? extends Object> sortIntASC(HashMap<Integer, ?> mapOfObjects);
    List<? extends Object> sortIntDESC(HashMap<Integer, ?> mapOfObjects);
    List<? extends Object> sortDoubleASC(HashMap<Double, ?> mapOfObjects);
    List<? extends Object> sortDoubleDESC(HashMap<Double, ?> mapOfObjects);
    List<? extends Object> sortStringAlphabeticalASC(HashMap<String, ?> mapOfObjects);
    List<? extends Object> sortStringAlphabeticalDESC(HashMap<String, ?> mapOfObjects);
}
