package Sorting;

import java.util.List;

public interface ISorting {
    List<?> sortIntASC(String fieldName);
    List<?> sortIntDESC(String fieldName);
    List<?> sortDoubleASC(String fieldName);
    List<?> sortDoubleDESC(String fieldName);
    List<?> sortStringAlphabeticalASC(String fieldName);
    List<?> sortStringAlphabeticalDESC(String fieldName);
}
