import Converter.ConverterCSV;
import Converter.ConverterJSON;
import Converter.ConverterXML;
import DataAccess.DataAccessCSV;
import DataAccess.DataAccessJSON;
import DataAccess.DataAccessXML;
import Model.Car;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConverterTest {
    List<Car> cars = new ArrayList<>();
    Car ferrariF40 = new Car(1122029, "Ferrari", "F40", 1992, 2.9);
    Car porche911 = new Car(5531110, "Porche", "911", 2018, 3.8);
    Car fordGT = new Car(9934442, "Ford", "GT", 2005, 5.4);
    Car mcLaren = new Car(6641124, "Mercedes", "SLR McLaren", 2006, 5.4);
    Car gWagon = new Car(9922212, "Mercedes", "Gelandewagen", 2006, 3.8);
    Car bentley = new Car(7884829, "Bentley", "Continental GT V8", 2020, 6);

    DataAccessCSV csv;
    DataAccessJSON json;
    DataAccessXML xml;

    @BeforeEach
    public void setUp() {
        cars.add(ferrariF40);
        cars.add(porche911);
        cars.add(fordGT);
        cars.add(mcLaren);
        cars.add(gWagon);
        cars.add(bentley);

        csv = new DataAccessCSV("src/test/java/DataFiles/cars.csv", Car.class, true);
        json = new DataAccessJSON("src/test/java/DataFiles/cars.json", Car.class);
        xml = new DataAccessXML("src/test/java/DataFiles/cars.xml", Car.class, "Cars");

        csv.writeList(Collections.singletonList(cars));
        json.writeList(Collections.singletonList(cars));
        xml.writeList(Collections.singletonList(cars));
    }

    @AfterEach
    public void tearDown() {
        new File("src/test/java/DataFiles/cars.json").delete();
        new File("src/test/java/DataFiles/cars.csv").delete();
        new File("src/test/java/DataFiles/cars.xml").delete();

        new File("src/test/java/DataFiles/carsConverted.json").delete();
        new File("src/test/java/DataFiles/carsConverted.csv").delete();
        new File("src/test/java/DataFiles/carsConverted.xml").delete();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertJsonToCSV() {
        ConverterJSON.convertToCSV("src/test/java/DataFiles/cars.json", "src/test/java/DataFiles/carsConverted.csv");
        DataAccessCSV dataAccessCSV = new DataAccessCSV("src/test/java/DataFiles/carsConverted.csv", Car.class, true);
        List<Car> retrievedCars = (List<Car>)(List<?>) dataAccessCSV.getAllObjects();
        for(int i = 0; i < retrievedCars.size(); i++) {
            assertEquals(cars.get(i).toString(), retrievedCars.get(i).toString());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertJsonToXML() {
        ConverterJSON.convertToXML("src/test/java/DataFiles/cars.json", "src/test/java/DataFiles/carsConverted.xml", "Cars");
        DataAccessXML dataAccessXML = new DataAccessXML("src/test/java/DataFiles/carsConverted.xml", Car.class, "Cars");
        List<Car> retrievedCars = (List<Car>)(List<?>) dataAccessXML.getAllObjects();
        for(int i = 0; i < retrievedCars.size(); i++) {
            assertEquals(cars.get(i).toString(), retrievedCars.get(i).toString());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertCsvToJson() {
        ConverterCSV.convertToJSON(csv, "src/test/java/DataFiles/carsConverted.json", Car.class);
        DataAccessJSON dataAccessJSON = new DataAccessJSON("src/test/java/DataFiles/carsConverted.json", Car.class);
        List<Car> retrievedCars = (List<Car>)(List<?>) dataAccessJSON.getAllObjects();
        for(int i = 0; i < retrievedCars.size(); i++) {
            assertEquals(cars.get(i).toString(), retrievedCars.get(i).toString());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertCsvToXml() {
        ConverterCSV.convertToXML(csv, "src/test/java/DataFiles/carsConverted.xml", Car.class, "Cars");
        DataAccessXML dataAccessXML = new DataAccessXML("src/test/java/DataFiles/carsConverted.xml", Car.class, "Cars");
        List<Car> retrievedCars = (List<Car>)(List<?>) dataAccessXML.getAllObjects();
        for(int i = 0; i < retrievedCars.size(); i++) {
            assertEquals(cars.get(i).toString(), retrievedCars.get(i).toString());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertXmlToJson() {
        ConverterXML.convertToJSON("src/test/java/DataFiles/cars.xml", "src/test/java/DataFiles/carsConverted.json", Car.class);
        DataAccessJSON dataAccessJSON = new DataAccessJSON("src/test/java/DataFiles/carsConverted.json", Car.class);
        List<Car> retrievedCars = (List<Car>)(List<?>) dataAccessJSON.getAllObjects();
        for(int i = 0; i < retrievedCars.size(); i++) {
            assertEquals(cars.get(i).toString(), retrievedCars.get(i).toString());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertXmlToCsv() {
        ConverterXML.convertToCSV("src/test/java/DataFiles/cars.xml", "src/test/java/DataFiles/carsConverted.csv", Car.class, true);
        DataAccessCSV dataAccessCSV = new DataAccessCSV("src/test/java/DataFiles/carsConverted.csv", Car.class, true);
        List<Car> retrievedCars = (List<Car>)(List<?>) dataAccessCSV.getAllObjects();
        for(int i = 0; i < retrievedCars.size(); i++) {
            assertEquals(cars.get(i).toString(), retrievedCars.get(i).toString());
        }
    }
}
