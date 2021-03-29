import dataCSV.dataAcces.DataAccess;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataAccessTest {

    @BeforeEach
    public void setUp() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, true);
        dataAccess.createCSV();
    }

    @AfterEach
    public void tearDown() {
        FileOutputStream writer = null;
        try {
            writer = new FileOutputStream("test.csv");
            writer.write(("").getBytes());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void writeOneObjectToFile() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, true);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018);
        dataAccess.writeObject(gClass); //Writes the car object to the file

        //Read back the car to check if equal
        List<Car> cars = new ArrayList<>();
        cars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        //CHECK IF THERE IS ONLY 1 CAR IN THE FILE
        assertEquals(1, cars.size());

        Car c = cars.get(0); //Get the only car
        assertEquals(gClass.toString(), c.toString()); //Check the values of the cars
    }

    @Test
    @SuppressWarnings("unchecked")
    public void appendTest() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, true);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018);
        Car golf = new Car(5531121, "Volkswagen", "Golf", 2008);
        dataAccess.writeObject(gClass);
        dataAccess.appendObject(golf);

        List<Car> cars = new ArrayList<>();
        cars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        assertEquals(2, cars.size());
        assertEquals(gClass.toString(), cars.get(0).toString());
        assertEquals(golf.toString(), cars.get(1).toString());
    }
}
