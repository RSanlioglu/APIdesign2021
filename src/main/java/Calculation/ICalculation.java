package Calculation;

import DataAccess.DataAccessCSV;

import java.io.IOException;

public interface ICalculation {
    int calculateColumnSumInt(String columnName, Class type) throws NoSuchFieldException, IllegalAccessException, IOException;
    double calculateColumnSumDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    double calculateColumnAverageInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    double calculateColumnAverageDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    int calculateColumnMinInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    int calculateColumnMaxInt(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    double calculateColumnMinDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    double calculateColumnMaxDouble(String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;
    int countIntValue(String columnName, Class type, int value) throws IOException, NoSuchFieldException, IllegalAccessException;
    int countStringValue(String columnName, Class type, String value) throws IOException, NoSuchFieldException, IllegalAccessException;
    int countDoubleValue(String columnName, Class type, double refValue) throws IOException, NoSuchFieldException, IllegalAccessException;
}
