package dataCSV.calculation;

import dataCSV.dataAcces.DataAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public interface ICalculation {

    int CalculateColumnSumInt(DataAccess csv, String fileName, String columnName, Class type) throws NoSuchFieldException, IllegalAccessException, IOException;

    double CalculateColumnSumDouble(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    double CalculateColumnAverageInt(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    double CalculateColumnAverageDouble(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    int CalculateColumnMinInt(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    double CalculateColumnMinDouble(DataAccess csv, String fileName, String columnName, Class type) throws IOException, NoSuchFieldException, IllegalAccessException;

    int CountIntValue(DataAccess csv, String fileName, String columnName, Class type, int value) throws IOException, NoSuchFieldException, IllegalAccessException;

    int CountStringValue(DataAccess csv, String fileName, String columnName, Class type, String value) throws IOException, NoSuchFieldException, IllegalAccessException;

}
