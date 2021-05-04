package Calculation;

public interface ICalculation {
    /**
     * Enum containing three operations such as SUM, MIN and MAX.
     * Making it easier for client to calculate a column
     */
    enum Method {
        SUM,
        MIN,
        MAX
    }

    int calculateColumnInt(String columnName, Class<?> type, Method operation);
    double calculateColumnDouble(String columnName, Class<?> type, Method operation);
    double calculateColumnAverageInt(String columnName, Class<?> type);
    double calculateColumnAverageDouble(String columnName, Class<?> type);
    int countIntInColumn(String columnName, Class<?> type, int refValue);
    int countDoubleInColumn(String columnName, Class<?> type, double refValue);
    int countStringInColumn(String columnName, Class<?> type, String refValue);
}
