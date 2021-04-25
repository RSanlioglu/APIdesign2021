package Calculation;

import DataAccess.DataAccessCSV;

import java.io.IOException;

public interface ICalculation {
    int calculateColumnSumInt(String columnName, Class type);
    double calculateColumnSumDouble(String columnName, Class type);
    double calculateColumnAverageInt(String columnName, Class type);
    double calculateColumnAverageDouble(String columnName, Class type);
    int calculateColumnMinInt(String columnName, Class type);
    int calculateColumnMaxInt(String columnName, Class type);
    double calculateColumnMinDouble(String columnName, Class type);
    double calculateColumnMaxDouble(String columnName, Class type);
    int countIntValue(String columnName, Class type, int value);
    int countStringValue(String columnName, Class type, String value);
    int countDoubleValue(String columnName, Class type, double refValue);
}
