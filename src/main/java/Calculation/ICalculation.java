package Calculation;

import DataAccess.DataAccessCSV;

import java.io.IOException;

public interface ICalculation {
    int CalculateColumnSumInt(String columnName, Class type) throws NoSuchFieldException, IllegalAccessException, IOException;
    double CalculateColumnSumDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    double CalculateColumnAverageInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    double CalculateColumnAverageDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    int CalculateColumnMinInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    double CalculateColumnMinDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    int CountIntValue(String columnName, Class type, int value) throws IOException, NoSuchFieldException, IllegalAccessException;
    int CountStringValue(String columnName, Class type, String value) throws IOException, NoSuchFieldException, IllegalAccessException;

}
