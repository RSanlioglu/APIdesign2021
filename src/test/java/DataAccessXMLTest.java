import Exceptions.FileAlreadyExistsException;
import dataCSV.CoreCSV;
import dataXML.dataAccess.DataAccessXML;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataAccessXMLTest {
    private static final DataAccessXML dataAccessXML = new DataAccessXML("test.xml", Car.class, "Car");

    /**
     * The tests that are created here uses a specific type of class that we have created
     * just for the sake of the tests. The class is a simple Car class, letting us create
     * objects of cars for the different tests
     */

    /**
     * Creates a new test.xml file for each test.
     */
    @BeforeEach
    public void setUp() {
        try {
            dataAccessXML.createXML();
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    /**
     * After each test the file is deleted
     */
    @AfterEach
    public void tearDown() {
        new File("test.xml").delete();
    }

    /**
     * Create a new empty xml file
     */
    @Test
    @SuppressWarnings("unchecked")
    public void createNewXMLFile() {
        DataAccessXML dataAccessXML = new DataAccessXML("newFile.xml", Car.class, "Car");

        try {
            dataAccessXML.createXML(); //Create the file
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        }

        assertTrue(new File("newFile.xml").exists()); //See if the file exists

        List<CoreCSV.Car> cars = (List<CoreCSV.Car>)(List<?>) dataAccessXML.getAllObjects();
        assertEquals(0, cars.size()); //Check if the file is empty

        new File("newFile.xml").delete(); //Delete the file at last
    }

    /**
     * Try to create a new Xml-file, but it already exists.
     * This test will throw an exception
     */
    @Test
    public void createExistingXMLFile() {
        assertThrows(FileAlreadyExistsException.class, dataAccessXML::createXML); //This will throw an exception since the file is created before the test, and we create it again here
    }

    /**
     * A test where we add two car-objecst in the datafile,
     * and return them all and check them.
     * Headers are used in this test
     */
    @Test
    @SuppressWarnings("unchecked")
   public void getAllObjectsFromTheXMLFile() {
        //The list of cars are created. NOTE! Tesla is not added and will not be returned from the file
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.0);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car tesla = new Car(33212, "Tesla", "Model S", 2020, 0);
        cars.add(mercedes);
        cars.add(mustang);
        dataAccessXML.writeList(Collections.singletonList(cars));

        //Return the cars from the datafile
        List<Car> returneCars = (List<Car>)(List<?>) dataAccessXML.getAllObjects();

        assertEquals(2, returneCars.size());
        assertEquals(returneCars.get(0).toString(), mercedes.toString());
        assertEquals(returneCars.get(1).toString(), mustang.toString());
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
        dataAccessXML.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        assertEquals(passat.toString(), dataAccessXML.getObjectById("registrationID", 99122).toString());
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
        dataAccessXML.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        assertNotEquals(mercedes.toString(), dataAccessXML.getObjectById("registrationID", 99122).toString());
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's producer (Here all of them are unique).
     * The cars are then compared. The test will pass with correct comparison. Will test string values
     */
    @Test
    public void getCorrectObjectByProducerReference() {
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.1);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 1.9);
        cars.add(mercedes);
        cars.add(passat);
        cars.add(mustang);
        dataAccessXML.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        assertEquals(passat.toString(), dataAccessXML.getObjectById("producer", "Volkswagen").toString());
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's producer (Here all of them are unique).
     * The cars are then compared. The test will pass with an incorrect comparison. Will test string values
     */
    @Test
    public void getCorrectObjectByProducerReference_NotEquals() {
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.0);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 5.1);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 1.6);
        cars.add(mercedes);
        cars.add(passat);
        cars.add(mustang);
        dataAccessXML.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        assertNotEquals(passat.toString(), dataAccessXML.getObjectById("producer", "Mercedes").toString());
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's cylinder volume.
     * The cars are then compared. The test will pass with correct comparison. Will test double values
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
        dataAccessXML.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        assertEquals(passat.toString(), dataAccessXML.getObjectById("cylinderVolume", 1.6).toString());
    }

    /**
     * This test will write 3 car objects to the file and a car is retrieved by it's cylinder volume.
     * The cars are then compared. The test will pass with an incorrect comparison. Will test double values
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
        dataAccessXML.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        assertNotEquals(mercedes.toString(), dataAccessXML.getObjectById("cylinderVolume", 4.7).toString());
    }

    /**
     * Creates a new Car-object and writes it to the xml-file.
     * The test checks the size of the objects returned from the file
     * and the value that is expected
     */
    @Test
    @SuppressWarnings("unchecked")
    public void writeOneObjectToFile() {
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018,3.2);
        dataAccessXML.writeObject(gClass); //Write the object to the file

        List<Car> cars = (List<Car>)(List<?>) dataAccessXML.getAllObjects(); //Get all objects from the file (should only be one now)

        assertEquals(1, cars.size()); //Check if the list only contains one car since we only added one
        assertEquals(gClass.toString(), cars.get(0).toString()); //Check the car returned from the file
    }

    /**
     * Writes a list of car objects to the file. The cars are then returned from the file and
     * used to check with the values of the initial cars.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void writeListToFile() {
        List<Car> cars = new ArrayList<>();
        Car mercedes = new Car(1211, "Mercedes", "C-class", 2009, 2.1);
        Car mustang = new Car(55311, "Ford", "Mustang Cobra", 1969, 4.7);
        Car passat = new Car(99122, "Volkswagen", "Passat", 2006, 1.9);

        cars.add(mercedes);
        cars.add(mustang);
        cars.add(passat);

        dataAccessXML.writeList(Collections.singletonList(cars)); //Writes the list of cars to the file

        List<Car> returnedCars = (List<Car>)(List<?>) dataAccessXML.getAllObjects();

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
    public void appendObject() {
        Car gClass = new Car(121222, "Mercedes", "G-Class", 2018, 2.7);
        Car golf = new Car(5531121, "Volkswagen", "Golf", 2008, 1.6);
        dataAccessXML.writeObject(gClass);
        dataAccessXML.appendObject(golf);
        //Get the cars back from the file

        List<Object> cars = dataAccessXML.getAllObjects();
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
    @SuppressWarnings("unchecked")
    public void appendList() {
        dataAccessXML.writeObject(new Car(55323, "Opel", "Astra", 2010, 1.6)); //The dataFile contains one car now

        List<Object> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);

        dataAccessXML.appendList(cars); //Append the list of cars to the file

        List<Car> returnedCars = (List<Car>)(List<?>) dataAccessXML.getAllObjects();

        assertEquals(3, returnedCars.size());
        assertEquals(returnedCars.get(1).toString(), tesla.toString());
        assertEquals(returnedCars.get(2).toString(), etron.toString());
    }

    /**
     * Simple test to see if the framework can find an object with the same values in the file.
     * The test is supposed to find the object and return a true value.
     */
    @Test
    public void doesExist_Correct() {
        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);
        dataAccessXML.writeList(Collections.singletonList(cars));

        assertTrue(dataAccessXML.doesExist(tesla));
    }

    /**
     * Simple test to see if the framework can find an object with the same values in the file.
     * The test is supposed to NOT find the object and return a false value.
     */
    @Test
    public void doesExist_Fail() {
        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        Car mustang = new Car(55311, "Ford", "Mustang", 1969, 4.7); //Mustang is not added to the list and will not exist in the file
        cars.add(tesla);
        cars.add(etron);
        dataAccessXML.writeList(Collections.singletonList(cars));

        assertFalse(dataAccessXML.doesExist(mustang));
    }

    /**
     * A test that will delete an object in the file. When the objects are returned the deleted object
     * will not be found
     */
    @Test
    public void deleteObjectFromFile() {
        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);
        dataAccessXML.appendList(Collections.singletonList(cars));

        assertTrue(dataAccessXML.doesExist(tesla)); //Check if the tesla is inside the file before deleting it
        dataAccessXML.deleteObject(tesla);
        assertFalse(dataAccessXML.doesExist(tesla)); //Check if the tesla is inside the file after deleting it
    }

    /**
     * A test where we compare a Car-object before updating it and after updating it to see
     * the changes made
     */
    @Test
    @SuppressWarnings("unchecked")
    public void updateObjectFromFile() {
        List<Car> cars = new ArrayList<>();
        Car tesla = new Car(2211, "Tesla", "Model s", 2020, 0);
        Car etron = new Car(8853, "Audi", "E Tron", 2020, 0);
        cars.add(tesla);
        cars.add(etron);
        dataAccessXML.writeList(Collections.singletonList(cars)); //Write the list of cars in to the file

        List<Car> returnedCars = (List<Car>)(List<?>) dataAccessXML.getAllObjects();
        assertEquals(tesla.toString(), returnedCars.get(0).toString()); //Compare the old tesla with the tesla from the file

        Car teslaUpdated = new Car(2211, "Tesla", "Model x", 2021, 0); //The new Tesla

        dataAccessXML.updateObject(tesla, teslaUpdated); //Update the old tesla with the new tesla

        returnedCars = (List<Car>)(List<?>) dataAccessXML.getAllObjects(); //Read the file again
        assertEquals(returnedCars.get(1).toString(), teslaUpdated.toString()); //Compare the new tesla with the tesla from the file. The updated objects are at the bottom
    }
}