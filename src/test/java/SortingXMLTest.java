import DataAccess.DataAccessXML;
import Exceptions.FileAlreadyExistsException;
import Model.Car;
import Sorting.SortingXML;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortingXMLTest {
    private static final DataAccessXML dataAccess = new DataAccessXML("src/test/java/DataFiles/cars.xml", Car.class, "People");
    private static final SortingXML sorting = new SortingXML(dataAccess, Car.class);

    List<Car> cars = new ArrayList<>();
    Car ferrariF40 = new Car(1122029, "Ferrari", "F40", 1992, 2.9);
    Car porche911 = new Car(5531110, "Porche", "911", 2018, 3.8);
    Car fordGT = new Car(9934442, "Ford", "GT", 2005, 5.4);
    Car mcLaren = new Car(6641124, "Mercedes", "SLR McLaren", 2006, 5.4);
    Car gWagon = new Car(9922212, "Mercedes", "Gelandewagen", 2006, 3.8);
    Car bentley = new Car(7884829, "Bentley", "Continental GT V8", 2020, 6);

    @BeforeEach
    public void setUp() {
        try {
            dataAccess.createXML();
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        cars.add(ferrariF40);
        cars.add(porche911);
        cars.add(fordGT);
        cars.add(mcLaren);
        cars.add(gWagon);
        cars.add(bentley);
        dataAccess.writeList(Collections.singletonList(cars));
    }

    @AfterEach
    public void tearDown() {
        new File("src/test/java/DataFiles/cars.xml").delete();
    }

    @Test
    public void sortIntAscending() {
        List<Car> carsSortedByYear = sorting.sortIntASC("year");
        assertEquals(ferrariF40.toString(), carsSortedByYear.get(0).toString());
        assertEquals(fordGT.toString(), carsSortedByYear.get(1).toString());
        assertEquals(mcLaren.toString(), carsSortedByYear.get(2).toString());
        assertEquals(gWagon.toString(), carsSortedByYear.get(3).toString());
        assertEquals(porche911.toString(), carsSortedByYear.get(4).toString());
        assertEquals(bentley.toString(), carsSortedByYear.get(5).toString());
    }

    @Test
    public void sortIntDescending() {
        List<Car> carsSortedByYear = sorting.sortIntDESC("year");
        assertEquals(ferrariF40.toString(), carsSortedByYear.get(5).toString());
        assertEquals(fordGT.toString(), carsSortedByYear.get(4).toString());
        assertEquals(mcLaren.toString(), carsSortedByYear.get(3).toString());
        assertEquals(gWagon.toString(), carsSortedByYear.get(2).toString());
        assertEquals(porche911.toString(), carsSortedByYear.get(1).toString());
        assertEquals(bentley.toString(), carsSortedByYear.get(0).toString());
    }

    @Test
    public void sortDoubleAscending() {
        List<Car> carsSortedByCylinderVolume = sorting.sortDoubleASC("cylinderVolume");
        assertEquals(ferrariF40.toString(), carsSortedByCylinderVolume.get(0).toString());
        assertEquals(porche911.toString(), carsSortedByCylinderVolume.get(1).toString());
        assertEquals(gWagon.toString(), carsSortedByCylinderVolume.get(2).toString());
        assertEquals(fordGT.toString(), carsSortedByCylinderVolume.get(3).toString());
        assertEquals(mcLaren.toString(), carsSortedByCylinderVolume.get(4).toString());
        assertEquals(bentley.toString(), carsSortedByCylinderVolume.get(5).toString());
    }

    @Test
    public void sortDoubleDescending() {
        List<Car> carsSortedByCylinderVolume = sorting.sortDoubleDESC("cylinderVolume");
        assertEquals(ferrariF40.toString(), carsSortedByCylinderVolume.get(5).toString());
        assertEquals(porche911.toString(), carsSortedByCylinderVolume.get(4).toString());
        assertEquals(gWagon.toString(), carsSortedByCylinderVolume.get(3).toString());
        assertEquals(fordGT.toString(), carsSortedByCylinderVolume.get(2).toString());
        assertEquals(mcLaren.toString(), carsSortedByCylinderVolume.get(1).toString());
        assertEquals(bentley.toString(), carsSortedByCylinderVolume.get(0).toString());
    }

    @Test
    public void sortStringAlphabeticalAscending() {
        List<Car> carsSortedByCylinderVolume = sorting.sortStringAlphabeticalASC("producer");
        assertEquals(bentley.toString(), carsSortedByCylinderVolume.get(0).toString());
        assertEquals(ferrariF40.toString(), carsSortedByCylinderVolume.get(1).toString());
        assertEquals(fordGT.toString(), carsSortedByCylinderVolume.get(2).toString());
        assertEquals(mcLaren.toString(), carsSortedByCylinderVolume.get(3).toString());
        assertEquals(gWagon.toString(), carsSortedByCylinderVolume.get(4).toString());
        assertEquals(porche911.toString(), carsSortedByCylinderVolume.get(5).toString());
    }

    @Test
    public void sortStringAlphabeticalDescending() {
        List<Car> carsSortedByCylinderVolume = sorting.sortStringAlphabeticalDESC("producer");
        assertEquals(bentley.toString(), carsSortedByCylinderVolume.get(5).toString());
        assertEquals(ferrariF40.toString(), carsSortedByCylinderVolume.get(4).toString());
        assertEquals(fordGT.toString(), carsSortedByCylinderVolume.get(3).toString());
        assertEquals(mcLaren.toString(), carsSortedByCylinderVolume.get(2).toString());
        assertEquals(gWagon.toString(), carsSortedByCylinderVolume.get(1).toString());
        assertEquals(porche911.toString(), carsSortedByCylinderVolume.get(0).toString());
    }

}
