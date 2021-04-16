package dataCSV.calculation;

import dataCSV.dataAcces.DataAccessCSV;

import java.io.IOException;

public interface ICalculation {

    int CalculateColumnSumInt(DataAccessCSV csv, String fileName, String columnName, Class type) throws NoSuchFieldException, IllegalAccessException, IOException;

    double CalculateColumnSumDouble(DataAccessCSV csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    double CalculateColumnAverageInt(DataAccessCSV csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    double CalculateColumnAverageDouble(DataAccessCSV csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    int CalculateColumnMinInt(DataAccessCSV csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    double CalculateColumnMinDouble(DataAccessCSV csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    int CountIntValue(DataAccessCSV csv, String fileName, String columnName, Class type, int value) throws IOException, NoSuchFieldException, IllegalAccessException;

    int CountStringValue(DataAccessCSV csv, String fileName, String columnName, Class type, String value) throws IOException, NoSuchFieldException, IllegalAccessException;

}
