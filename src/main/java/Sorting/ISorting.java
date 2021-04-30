package Sorting;

import java.util.List;

public interface ISorting {
    <T> List<T> sortIntASC(String fieldName);
    <T> List<T> sortIntDESC(String fieldName);
    <T> List<T> sortDoubleASC(String fieldName);
    <T> List<T> sortDoubleDESC(String fieldName);
    <T> List<T> sortStringAlphabeticalASC(String fieldName);
    <T> List<T> sortStringAlphabeticalDESC(String fieldName);
}
