import Calculation.Calculation;
import DataAccess.DataAccessXML;
import Model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalculationXMLTest {
    private static final DataAccessXML dataAccess = new DataAccessXML("src/test/java/DataFiles/people.xml", Person.class, "People");
    private static final Calculation calculation = new Calculation(dataAccess);

    /**
     * The test will calculate the sum weight between the people in people.xml file.
     * One assertion is to check if the sum is actually correct (which it is), and the
     * second assertion is to double check that the sum isn't something not right.
     */
    @Test
    public void calculateSumIntWeight() {
        int sum;
        sum = calculation.calculateColumnInt("weight", Person.class, Calculation.Method.SUM);

        assertEquals(sum, 711);
        assertNotEquals(sum, 314);
    }

    /**
     * The test will sum up the total height of the people in person.xml file.
     * The sum is supposed to be correct with the assertion.
     */
    @Test
    public void calculateDoubleHeight() {
        double sum;
        sum = calculation.calculateColumnDouble("height", Person.class, Calculation.Method.SUM);

        assertEquals(sum, 23.3);
        assertNotEquals(sum, 18.0);
    }

    /**
     * The test will find the average age between the people in person.xml file.
     * Note that the assertion is supposed to match.
     */
    @Test
    public void calculateAverageAgeInt() {
        double average;
        average = calculation.calculateColumnAverageInt("age", Person.class);

        assertEquals(Math.round(average), 30); //We round so we don't have to work with too precise numbers
        assertNotEquals(average, 31);
    }

    /**
     * The test will find the average height between the people in person.xml file.
     * Note that the assertion is supposed to match
     */
    @Test
    public void calculateAverageHeightDouble() {
        double avgHeight;
        avgHeight = calculation.calculateColumnAverageDouble("height", Person.class);

        assertEquals(2, Math.round(avgHeight)); //We round so we don't have to work with too precise numbers
        assertNotEquals(0, avgHeight);
    }

    /**
     * The test will return the min integer value from the person.xml file.
     * The min integer is received from the age column.
     */
    @Test
    public void calculateMinAge() {
        int min;
        min = calculation.calculateColumnInt("age", Person.class, Calculation.Method.MIN);

        assertEquals(20, min);
        assertNotEquals(0, min);
    }

    /**
     * The test will return the max integer value from the person.xml file.
     * The max integer is received from the age column
     */
    @Test
    public void calculateMaxAge() {
        int max;
        max = calculation.calculateColumnInt("age", Person.class, Calculation.Method.MAX);

        assertNotEquals(0, max);
        assertEquals(40, max);
    }

    /**
     * The test will return the min double value from the person.xml file.
     * The min double is received from the height column.
     */
    @Test
    public void calculateMinHeight() {
        double min;
        min = calculation.calculateColumnDouble("height", Person.class, Calculation.Method.MIN);

        assertNotEquals(0, min);
        assertEquals(1.5, min);
    }

    /**
     * The test will return the max double value from the person.xml file.
     * The ,ax double is received from the height column.
     */
    @Test
    public void calculateMaxHeight() {
        double max;
        max = calculation.calculateColumnDouble("height", Person.class, Calculation.Method.MAX);

        assertNotEquals(0, max);
        assertEquals(2.5, max);
    }

    /**
     * The test will return how many times an integer occurs in one column in the person.xml file.
     */
    @Test
    public void countAge() {
        int countAge;
        countAge = calculation.countIntInColumn("age", Person.class, 33);

        assertNotEquals(0, countAge);
        assertEquals(3, countAge);
    }

    /**
     * The test will return how many times a string occurs in one column in the person.xml file
     */
    @Test
    public void countName() {
        int countName = 0;
        countName = calculation.countStringInColumn("first_name", Person.class, "Richard");

        assertEquals(1, countName);
        assertNotEquals(0, countName);
    }

    /**
     * The test will return how many times a double value occurs in one column in the person.xml file.
     */
    @Test
    public void countHeight() {
        int countDouble;
        countDouble = calculation.countDoubleInColumn("height", Person.class, 2);

        assertNotEquals(0, countDouble);
        assertEquals(2, countDouble);
    }
}
