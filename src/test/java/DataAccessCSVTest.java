import DataAccess.DataAccessCSV;
import Exceptions.AlreadyExistsException;
import org.junit.jupiter.api.*;
import Model.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataAccessCSVTest {
    private static final DataAccessCSV dataAccess = new DataAccessCSV("test.csv", Car.class, true);

    /**
     * The tests that are created here uses a specific type of class that we have created
     * just for the sake of the tests. The class is a simple Model.Car class, letting us create
     * objects of cars for the different tests
     */

    /**
     * Creates a new test.csv file for each test, with headers
     */
    @BeforeEach
    public void setUp() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        try {
            dataAccessCSV.createCSV();
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    /**
     * After each test the file is deleted
     */
    @AfterEach
    public void tearDown() {
        new File("test.csv").delete(); //Delete the file after the tests
    }

    /**
     * Create a new empty csv.file
     */
    @Test
    public void createNewCSVFile() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("newFile.csv", Car.class, true);
        try {
            dataAccessCSV.createCSV(); //Create the file
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }
        assertTrue(new File("newFile.csv").exists()); //See if the file exists

        List<Car> cars = dataAccessCSV.getAllObjects();
        assertEquals(0, cars.size()); //Check if the returned objects are 0, since the file is empty

        new File("newFile.csv").delete();  //Delete the file at last
    }

    /**
     * Try to create a new csv-file, but it already exists.
     * This test will throw an exception
     */
    @Test
    public void createExistingCSVFile() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        assertThrows(AlreadyExistsException.class, dataAccessCSV::createCSV);
    }


    /**
     * A test where we add two car-objecst in the datafile,
     * and return them all and check them.
     * Headers are used in this test
     */
    @Test
    public void getAllObjectsFromTheCSVFile_withHeader() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);

        //The list of cars are created. NOTE! tesla is not added and will not be expected to be returned from the file
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.0);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car tesla = new Car(33212, "Tesla", "Model S", 2020, 0);
        cars.add(mercedes);
        cars.add(mustang);
        dataAccessCSV.writeList(Collections.singletonList(cars));

        //Returns the cars from the datafile
        List<Car> returnedCars;
        returnedCars = dataAccessCSV.getAllObjects();

        assertEquals(2, returnedCars.size()); //The size of the returned cars are 2
        Assertions.assertEquals(returnedCars.get(0).toString(), mercedes.toString());  //Mercedes is in the csv file
        Assertions.assertEquals(returnedCars.get(1).toString(), mustang.toString());   //Mustang is in the csv file
        assertFalse(dataAccessCSV.doesExist(tesla));                           //Tesla is NOT in the file. doesExist function is tested further down
    }

    /**
     * A test where we add two car-objecst in the datafile,
     * and return them all and check them.
     * Headers are not used in this test
     */
    @Test
    public void getAllObjectsFromTheCSVFile_withoutHeader() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, false);

        //The list of cars are created. NOTE! tesla is not added and will not be expected to be returned from the file
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.0);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car tesla = new Car(33212, "Tesla", "Model S", 2020, 0);
        cars.add(mercedes);
        cars.add(mustang);
        dataAccessCSV.writeList(Collections.singletonList(cars));

        //Returns the cars from the datafile
        List<Car> returnedCars = new ArrayList<>();
        returnedCars = dataAccessCSV.getAllObjects();

        assertEquals(2, returnedCars.size()); //The size of the returned cars are 2
        Assertions.assertEquals(returnedCars.get(0).toString(), mercedes.toString());  //Mercedes is in the csv file
        Assertions.assertEquals(returnedCars.get(1).toString(), mustang.toString());   //Mustang is in the csv file
        assertFalse(dataAccessCSV.doesExist(tesla));                           //Tesla is NOT in the file. doesExist function is tested further down
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's registrationID.
     * The cars are then compared. The test will pass with correct comparison. Will test int values
     */
    @Test
    public void getCorrectObjectByIDReference() {
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.0);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 1.6);
        cars.add(mercedes);
        cars.add(passat);
        cars.add(mustang);
        dataAccess.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        Car retrievedCar = dataAccess.getObjectById("registrationID", 99122);

        Assertions.assertEquals(passat.toString(), retrievedCar.toString());
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's registrationID.
     * The cars are then compared. The test will pass with an incorrect comparison. Will test int values
     */
    @Test
    public void getCorrectObjectByIDReference_NotEquals() {
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.1);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 2.0);
        cars.add(mercedes);
        cars.add(passat);
        cars.add(mustang);
        dataAccess.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        Car retrievedCar = dataAccess.getObjectById("registrationID", 99122);

        Assertions.assertNotEquals(mercedes.toString(),  retrievedCar.toString());
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's Producer (In this case they are unique).
     * The cars are then compared. The test will pass with correct comparison. Will test String values
     */
    @Test
    public void getCorrectObjectByProducerReference() {
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.0);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 1.6);
        cars.add(mercedes);
        cars.add(passat);
        cars.add(mustang);
        dataAccess.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        Car retrievedCar = dataAccess.getObjectById("producer", "Volkswagen");

        Assertions.assertEquals(passat.toString(), retrievedCar.toString());
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's Producer (In this case they are unique).
     * The cars are then compared. The test will pass with an incorrect comparison. Will test String values
     */
    @Test
    public void getCorrectObjectByProducerReference_NotEquals() {
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.1);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 2.0);
        cars.add(mercedes);
        cars.add(passat);
        cars.add(mustang);
        dataAccess.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        Car retrievedCar = dataAccess.getObjectById("producer", "Ford");

        Assertions.assertNotEquals(mercedes.toString(), retrievedCar.toString());
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's Cylinder (In this case they are unique).
     * The cars are then compared. The test will pass with correct comparison. Will test Double values
     */
    @Test
    public void getCorrectObjectByCylinderReference() {
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.0);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 1.6);
        cars.add(mercedes);
        cars.add(passat);
        cars.add(mustang);
        dataAccess.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        Car retrievedCar = dataAccess.getObjectById("cylinderVolume", 1.6);

        Assertions.assertEquals(passat.toString(), retrievedCar.toString());
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's Cylinder (In this case they are unique).
     * The cars are then compared. The test will pass with an incorrect comparison. Will Double int values
     */
    @Test
    public void getCorrectObjectByCylinderReference_NotEquals() {
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.1);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 2.0);
        cars.add(mercedes);
        cars.add(passat);
        cars.add(mustang);
        dataAccess.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        Car retrievedCar = dataAccess.getObjectById("cylinderVolume", 4.7);

        Assertions.assertNotEquals(mercedes.toString(), retrievedCar.toString());
    }

    /**
     * Creates a new Model.Car-object and writes it to the csv-file.
     * The test checks the size of the objects returned from the file
     * and the value that is expected
     */
    @Test
    public void writeOneObjectToFile_withHeader() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018, 2.1);
        dataAccessCSV.writeObject(gClass); //Writes the car object to the file

        //Get all objects from the file
        List<Car> cars = new ArrayList<>();
        cars = dataAccessCSV.getAllObjects();

        //Check if there is only one object in the file, since we only added one file
        assertEquals(1, cars.size());

        Car c = cars.get(0); //Get the only car
        Assertions.assertEquals(gClass.toString(), c.toString()); //Check the values of the cars
    }

    /**
     * Creates a new Model.Car-object and writes it to the csv-file.
     * The test checks the size of the objects returned from the file
     * and the value that is expected
     */
    @Test
    public void writeOneObjectToFile_withOutHeader() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, false);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018, 2.1);
        dataAccessCSV.writeObject(gClass); //Writes the car object to the file

        //Get all objects from the file
        List<Car> cars = new ArrayList<>();
        cars = dataAccessCSV.getAllObjects();

        //Check if there is only one object in the file, since we only added one file
        assertEquals(1, cars.size());

        Car c = cars.get(0); //Get the only car
        Assertions.assertEquals(gClass.toString(), c.toString()); //Check the values of the cars
    }

    /**
     * Writes a list of car objects to the file. The cars are then returned from the file and
     * used to check with the values of the initial cars.
     */
    @Test
    public void writeListToFile_WithHeader() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.1);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 1.9);

        cars.add(mercedes);
        cars.add(mustang);
        cars.add(passat);

        dataAccessCSV.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        //Returns the cars from the datafile
        List<Car> returnedCars = new ArrayList<>();
        returnedCars = dataAccessCSV.getAllObjects();

        assertEquals(returnedCars.size(), 3);
        Assertions.assertEquals(returnedCars.get(0).toString(), mercedes.toString());
        Assertions.assertEquals(returnedCars.get(1).toString(), mustang.toString());
        Assertions.assertEquals(returnedCars.get(2).toString(), passat.toString());
    }

    /**
     * Writes a list of car objects to the file. The cars are then returned from the file and
     * used to check with the values of the initial cars.
     */
    @Test
    public void writeListToFile_WithOutHeader() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, false);
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.1);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 1.9);

        cars.add(mercedes);
        cars.add(mustang);
        cars.add(passat);

        dataAccessCSV.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        //Returns the cars from the datafile
        List<Car> returnedCars = new ArrayList<>();
        returnedCars = dataAccessCSV.getAllObjects();

        assertEquals(returnedCars.size(), 3);
        Assertions.assertEquals(returnedCars.get(0).toString(), mercedes.toString());
        Assertions.assertEquals(returnedCars.get(1).toString(), mustang.toString());
        Assertions.assertEquals(returnedCars.get(2).toString(), passat.toString());

    }

    /**
     * Creates a file with a car-object, and appends that file with a new car-object.
     * The test checks the size of the objects returned from the file
     * and the values expected within the test
     */
    @Test
    public void appendObject_WithHeader() {
        //Add the cars. One is added the other is appended
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018, 2.1);
        Car golf = new Car(5531121, "Volkswagen", "Golf", 2008, 1.6);
        dataAccessCSV.writeObject(gClass);
        dataAccessCSV.appendObject(golf);

        //Get the cars back from the file
        List<Car> cars = new ArrayList<>();
        cars = dataAccessCSV.getAllObjects();

        //Check the size of the return list and the values
        assertEquals(2, cars.size());
        Assertions.assertEquals(gClass.toString(), cars.get(0).toString());
        Assertions.assertEquals(golf.toString(), cars.get(1).toString());
    }

    /**
     * Creates a file with a car-object, and appends that file with a new car-object.
     * The test checks the size of the objects returned from the file
     * and the values expected within the test
     */
    @Test
    public void appendObject_WithOutHeader() {
        //Add the cars. One is added the other is appended
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, false);
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018, 2.1);
        Car golf = new Car(5531121, "Volkswagen", "Golf", 2008, 1.6);
        dataAccessCSV.writeObject(gClass);
        dataAccessCSV.appendObject(golf);

        //Get the cars back from the file
        List<Car> cars = new ArrayList<>();
        cars = dataAccessCSV.getAllObjects();

        //Check the size of the return list and the values
        assertEquals(2, cars.size());
        Assertions.assertEquals(gClass.toString(), cars.get(0).toString());
        Assertions.assertEquals(golf.toString(), cars.get(1).toString());
    }

    /**
     * Appends a list of car objects and appends them to the file.
     * all of the objects are then returned and used to check with the initial
     * car-objects
     */
    @Test
    public void appendList_withHeader() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        dataAccessCSV.writeObject(new Car(55323, "Opel", "Astra", 2010, 1.6)); //The dataAccess contains one car now

        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);

        dataAccessCSV.appendList(Collections.singletonList(cars)); //Append the cars to the file

        //Read them back
        List<Car> returnedCars = dataAccessCSV.getAllObjects();

        assertEquals(3, returnedCars.size());                       //Check the length of the list of cars returned
        Assertions.assertEquals(returnedCars.get(1).toString(), tesla.toString());     //The second car in the file should be the tesla
        Assertions.assertEquals(returnedCars.get(2).toString(), etron.toString());     //The third car in the file should be the e tron
    }

    /**
     * Appends a list of car objects and appends them to the file.
     * all of the objects are then returned and used to check with the initial
     * car-objects
     */
    @Test
    public void appendList_withOutHeader() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, false);
        dataAccessCSV.writeObject(new Car(55323, "Opel", "Astra", 2010, 1.6)); //The dataAccess contains one car now

        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);

        dataAccessCSV.appendList(Collections.singletonList(cars)); //Append the cars to the file

        //Read them back
        List<Car> returnedCars = dataAccessCSV.getAllObjects();

        assertEquals(3, returnedCars.size());                       //Check the length of the list of cars returned
        Assertions.assertEquals(returnedCars.get(1).toString(), tesla.toString());     //The second car in the file should be the tesla
        Assertions.assertEquals(returnedCars.get(2).toString(), etron.toString());     //The third car in the file should be the e tron
    }

    /**
     * Simple test to see if the framework can find an object with the same values in the file.
     * The test is supposed to find the object and return a true value.
     */
    @Test
    public void doesExist_Correct() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);
        dataAccessCSV.writeList(Collections.singletonList(cars));

        assertTrue(dataAccessCSV.doesExist(tesla));
    }

    /**
     * Simple test to see if the framework can find an object with the same values in the file.
     * The test is supposed to NOT find the object and return a false value.
     */
    @Test
    public void doesExist_Fail() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        Car mustang = new Car(55311, "Ford", "Mustang", 1969, 4.7); //Mustang is not added to the list and will not exist in the file
        cars.add(tesla);
        cars.add(etron);
        dataAccessCSV.writeList(Collections.singletonList(cars));

        assertFalse(dataAccessCSV.doesExist(mustang));
    }

    /**
     * A test that will delete an object in the file. When the objects are returned the deleted object
     * will not be found
     */
    @Test
    public void deleteObjectFromFile() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);
        dataAccessCSV.writeList(Collections.singletonList(cars));

        assertTrue(dataAccessCSV.doesExist(tesla)); //Check if the tesla is inside the file before deleting it
        dataAccessCSV.deleteObject(tesla);
        assertFalse(dataAccessCSV.doesExist(tesla)); //Check if the tesla is inside the file after deleting it
    }

    /**
     * A test that will delete an object in the file. When the objects are returned the deleted object
     * will not be found
     */
    @Test
    public void deleteObjectFromFile_WithoutHeaders() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, false);
        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);
        dataAccessCSV.writeList(Collections.singletonList(cars));

        assertTrue(dataAccessCSV.doesExist(tesla)); //Check if the tesla is inside the file before deleting it
        dataAccessCSV.deleteObject(tesla);
        assertFalse(dataAccessCSV.doesExist(tesla)); //Check if the tesla is inside the file after deleting it
    }

    /**
     * A test where we compare a Model.Car-object before updating it and after updating it to see
     * the changes made
     */
    @Test
    public void updateObjectFromFile() {
        DataAccessCSV dataAccessCSV = new DataAccessCSV("test.csv", Car.class, true);
        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);
        dataAccessCSV.writeList(Collections.singletonList(cars)); //Write the list of cars in to the file

        List<Car> returnedCars = dataAccessCSV.getAllObjects();
        Assertions.assertEquals(tesla.toString(), returnedCars.get(0).toString()); //Compare the old tesla with the tesla from the file

        Car teslaUpdated = new Car(2211, "Tesla", "Model x", 2021, 0); //The new Tesla
        dataAccessCSV.updateObject(tesla, teslaUpdated); //Update the old tesla with the new tesla

        returnedCars = dataAccessCSV.getAllObjects(); //Read the file again
        Assertions.assertEquals(returnedCars.get(1).toString(), teslaUpdated.toString()); //Compare the new tesla with the tesla from the file. The updated objects are at the bottom
    }
}
