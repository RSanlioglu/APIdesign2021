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

    /**
     * Function for calculating a columns integer - MAX value, MIN value and SUM value
     * @param columnName - Name of column you want to execute calculation operation on
     * @param type - Type of class that the objects consist of
     * @param operation - The operation that the client wishes to use (MIN, MAX and SUM)
     * @return integer value of the calculations
     */
    int calculateColumnInt(String columnName, Class<?> type, Method operation);

    /**
     * Function for calculating a columns double - MAX value, MIN value and SUM value
     * @param columnName - Name of column you want to execute calculation operation on
     * @param type - Type of class that the objects consist of
     * @param operation - The operation that the client wishes to use (MIN, MAX and SUM)
     * @return double value of the calculations
     */
    double calculateColumnDouble(String columnName, Class<?> type, Method operation);

    /**
     * Function for calculating the average int of a column.
     * @param columnName - Name of the column you want to find the average of
     * @param type - Type of class that the objects consist of
     * @return - average value
     */
    double calculateColumnAverageInt(String columnName, Class<?> type);

    /**
     * Function for calculating the average double of a column.
     * @param columnName - Name of the column you want to find the average of
     * @param type - Type of class that the objects consist of
     * @return - average value
     */
    double calculateColumnAverageDouble(String columnName, Class<?> type);

    /**
     * Function for counting how many times an int reference value is found inside the datafile.
     * @param columnName - Name of the column you want to search through
     * @param type - Type of class that the objects consist of
     * @param refValue - Reference-value used for checking
     * @return - How many times a value is found in the data-file
     */
    int countIntInColumn(String columnName, Class<?> type, int refValue);

    /**
     * Function for counting how many times a double reference value is found inside the datafile.
     * @param columnName - Name of the column you want to search through
     * @param type - Type of class that the objects consist of
     * @param refValue - Reference-value used for checking
     * @return - How many times a value is found in the data-file
     */
    int countDoubleInColumn(String columnName, Class<?> type, double refValue);

    /**
     * Function for counting how many times a string reference value is found inside the datafile.
     * @param columnName - Name of the column you want to search through
     * @param type - Type of class that the objects consist of
     * @param refValue - Reference-value used for checking
     * @return - How many times a value is found in the data-file
     */
    int countStringInColumn(String columnName, Class<?> type, String refValue);
}
