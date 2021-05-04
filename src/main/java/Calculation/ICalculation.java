package Calculation;

public interface ICalculation {
    int calculateColumnInt(String columnName, Class<?> type, Calculation.Calculation.Method operation);
    double calculateColumnDouble(String columnName, Class<?> type, Calculation.Calculation.Method operation);
    double calculateColumnAverageInt(String columnName, Class<?> type);
    double calculateColumnAverageDouble(String columnName, Class<?> type);
    int countIntInColumn(String columnName, Class<?> type, int refValue);
    int countDoubleInColumn(String columnName, Class<?> type, double refValue);
    int countStringInColumn(String columnName, Class<?> type, String refValue);
}
