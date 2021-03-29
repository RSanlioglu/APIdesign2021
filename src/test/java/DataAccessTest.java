import dataCSV.dataAcces.DataAccess;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataAccessTest {
    /**
     * Creates a new test.csv file for each test, with headers
     */
    @BeforeEach
    public void setUp() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, true);
        dataAccess.createCSV();
    }

    /**
     * After each test the file is emptied
     */
    @AfterEach
    public void tearDown() {
        new File("test.csv").delete(); //Delete the file after the tests
    }

    /**
     * Create a new empty csv.file
     */
    @Test
    @SuppressWarnings("unchecked")
    public void createNewCSVFile() {
        DataAccess dataAccess = new DataAccess("newFile.csv", Car.class, true);
        dataAccess.createCSV(); //Create the file
        assertTrue(new File("newFile.csv").exists()); //See if the file exists

        List<Car> cars = (List<Car>)(List<?>) dataAccess.getAllObjects();
        assertEquals(0, cars.size()); //Check if the returned objects are 0, since the file is empty

        new File("newFile.csv").delete();  //Delete the file at last
    }

    /**
     * Try to create a new csv-file, but it already exists
     */
    @Test
    public void createExistingCSVFile() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, true);
        dataAccess.createCSV(); //The test.csv is created before each test so this file already exists
        //TODO: Implement exception and assert throws
    }


    /**
     * A test where we add two car-objecst in the datafile,
     * and return them all and check them.
     * Headers are used in this test
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getAllObjectsFromTheCSVFile_withHeader() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, true);

        //The list of cars are created. NOTE! tesla is not added and will not be expected to be returned from the file
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969);
        Car tesla = new Car(33212, "Tesla", "Model S", 2020);
        cars.add(mercedes);
        cars.add(mustang);
        dataAccess.writeList(Collections.singletonList(cars));

        //Returns the cars from the datafile
        List<Car> returnedCars = new ArrayList<>();
        returnedCars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        assertEquals(2, returnedCars.size()); //The size of the returned cars are 2
        assertEquals(returnedCars.get(0).toString(), mercedes.toString());  //Mercedes is in the csv file
        assertEquals(returnedCars.get(1).toString(), mustang.toString());   //Mustang is in the csv file
        assertFalse(dataAccess.doesExist(tesla));                           //Tesla is NOT in the file. doesExist function is tested further down
    }

    /**
     * A test where we add two car-objecst in the datafile,
     * and return them all and check them.
     * Headers are not used in this test
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getAllObjectsFromTheCSVFile_withoutHeader() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, false);

        //The list of cars are created. NOTE! tesla is not added and will not be expected to be returned from the file
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969);
        Car tesla = new Car(33212, "Tesla", "Model S", 2020);
        cars.add(mercedes);
        cars.add(mustang);
        dataAccess.writeList(Collections.singletonList(cars));

        //Returns the cars from the datafile
        List<Car> returnedCars = new ArrayList<>();
        returnedCars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        assertEquals(2, returnedCars.size()); //The size of the returned cars are 2
        assertEquals(returnedCars.get(0).toString(), mercedes.toString());  //Mercedes is in the csv file
        assertEquals(returnedCars.get(1).toString(), mustang.toString());   //Mustang is in the csv file
        assertFalse(dataAccess.doesExist(tesla));                           //Tesla is NOT in the file. doesExist function is tested further down
    }


    /**
     * Creates a new Car-object and writes it to the csv-file.
     * The test checks the size of the objects returned from the file
     * and the value that is expected
     */
    @Test
    @SuppressWarnings("unchecked")
    public void writeOneObjectToFile_withHeader() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, true);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018);
        dataAccess.writeObject(gClass); //Writes the car object to the file

        //Get all objects from the file
        List<Car> cars = new ArrayList<>();
        cars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        //Check if there is only one object in the file, since we only added one file
        assertEquals(1, cars.size());

        Car c = cars.get(0); //Get the only car
        assertEquals(gClass.toString(), c.toString()); //Check the values of the cars
    }

    /**
     * Creates a new Car-object and writes it to the csv-file.
     * The test checks the size of the objects returned from the file
     * and the value that is expected
     */
    @Test
    @SuppressWarnings("unchecked")
    public void writeOneObjectToFile_withOutHeader() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, false);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018);
        dataAccess.writeObject(gClass); //Writes the car object to the file

        //Get all objects from the file
        List<Car> cars = new ArrayList<>();
        cars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        //Check if there is only one object in the file, since we only added one file
        assertEquals(1, cars.size());

        Car c = cars.get(0); //Get the only car
        assertEquals(gClass.toString(), c.toString()); //Check the values of the cars
    }

    /**
     * Writes a list of car objects to the file. The cars are then returned from the file and
     * used to check with the values of the initial cars.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void writeListToFile_WithHeader() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, true);
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006);

        cars.add(mercedes);
        cars.add(mustang);
        cars.add(passat);

        dataAccess.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        //Returns the cars from the datafile
        List<Car> returnedCars = new ArrayList<>();
        returnedCars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        assertEquals(returnedCars.size(), 3);
        assertEquals(returnedCars.get(0).toString(), mercedes.toString());
        assertEquals(returnedCars.get(1).toString(), mustang.toString());
        assertEquals(returnedCars.get(2).toString(), passat.toString());
    }

    /**
     * Writes a list of car objects to the file. The cars are then returned from the file and
     * used to check with the values of the initial cars.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void writeListToFile_WithOutHeader() {
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, false);
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006);

        cars.add(mercedes);
        cars.add(mustang);
        cars.add(passat);

        dataAccess.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        //Returns the cars from the datafile
        List<Car> returnedCars = new ArrayList<>();
        returnedCars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        assertEquals(returnedCars.size(), 3);
        assertEquals(returnedCars.get(0).toString(), mercedes.toString());
        assertEquals(returnedCars.get(1).toString(), mustang.toString());
        assertEquals(returnedCars.get(2).toString(), passat.toString());

    }

    /**
     * Creates a file with a car-object, and appends that file with a new car-object.
     * The test checks the size of the objects returned from the file
     * and the values expected within the test
     */
    @Test
    @SuppressWarnings("unchecked")
    public void appendObject_WithHeader() {
        //Add the cars. One is added the other is appended
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, true);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018);
        Car golf = new Car(5531121, "Volkswagen", "Golf", 2008);
        dataAccess.writeObject(gClass);
        dataAccess.appendObject(golf);

        //Get the cars back from the file
        List<Car> cars = new ArrayList<>();
        cars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        //Check the size of the return list and the values
        assertEquals(2, cars.size());
        assertEquals(gClass.toString(), cars.get(0).toString());
        assertEquals(golf.toString(), cars.get(1).toString());
    }

    /**
     * Creates a file with a car-object, and appends that file with a new car-object.
     * The test checks the size of the objects returned from the file
     * and the values expected within the test
     */
    @Test
    @SuppressWarnings("unchecked")
    public void appendObject_WithOutHeader() {
        //Add the cars. One is added the other is appended
        DataAccess dataAccess = new DataAccess("test.csv", Car.class, false);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018);
        Car golf = new Car(5531121, "Volkswagen", "Golf", 2008);
        dataAccess.writeObject(gClass);
        dataAccess.appendObject(golf);

        //Get the cars back from the file
        List<Car> cars = new ArrayList<>();
        cars = (List<Car>)(List<?>) dataAccess.getAllObjects();

        //Check the size of the return list and the values
        assertEquals(2, cars.size());
        assertEquals(gClass.toString(), cars.get(0).toString());
        assertEquals(golf.toString(), cars.get(1).toString());
    }

    /**
     * Appends a list of car objects and appends them to the file.
     * all of the objects are then returned and used to check with the initial
     * car-objects
     */
    @Test
    public void appendList_withHeader() {

    }

    /**
     * Appends a list of car objects and appends them to the file.
     * all of the objects are then returned and used to check with the initial
     * car-objects
     */
    @Test
    public void appendList_withOutHeader() {

    }
}
