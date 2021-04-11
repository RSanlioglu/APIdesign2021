package dataJSON.sorting;

import java.util.HashMap;
import java.util.List;

public interface ISortingJSON {
    List<? extends Object> sortIntASC(String fieldName);
    List<? extends Object> sortIntDESC(String fieldName);
    List<? extends Object> sortDoubleASC(String fieldName);
    List<? extends Object> sortDoubleDESC(String fieldName);
    List<? extends Object> sortStringAlphabeticalASC(String fieldName);
    List<? extends Object> sortStringAlphabeticalDESC(String fieldName);
}
