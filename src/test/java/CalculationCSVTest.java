import Calculation.CalculationCSV;
import DataAccess.DataAccessCSV;
import Model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculationCSVTest {
    private static final DataAccessCSV dataAccess = new DataAccessCSV("src/test/java/DataFiles/people.csv", Person.class, true);
    private static final CalculationCSV calculation = new CalculationCSV(dataAccess);

    /**
     * The test will calculate the sum weight between the people in people.csv file.
     * One assertion is to check if the sum is actually correct (which it is), and the
     * second assertion is to double check that the sum isn't something not right.
     */
    @Test
    public void calculateSumIntWeight() {
        int sum;
        sum = calculation.calculateColumnSumInt("weight", Person.class);

        assertEquals(sum, 860);
        assertNotEquals(sum, 314); //The actual sum is 860
    }

    /**
     * The test will sum up the total height of the people in person.csv file.
     * The sum is supposed to be correct with the assertion.
     */
    @Test
    public void calculateDoubleHeight() {
        double sum;
        sum = calculation.calculateColumnSumDouble("height", Person.class);

        assertEquals(sum, 18.5);
        assertNotEquals(sum, 18.0); //The sum is actually 18.5
    }

    /**
     * The test will find the average age between the people in person.csv file.
     * Note that the assertion is supposed to match.
     */
    @Test
    public void calculateAverageAgeInt() {
        double average;
        average = calculation.calculateColumnAverageInt("age", Person.class);

        assertEquals(average, 31.3);
        assertNotEquals(average, 31); //Average is actually 31.3
    }

    /**
     * The test will find the average height between the people in person.csv file.
     * Note that the assertion is supposed to match
     */
    @Test
    public void calculateAverageHeightDouble() {
        double avgHeight;
        avgHeight = calculation.calculateColumnAverageDouble("height", Person.class);

        assertEquals(1.85, avgHeight);
        assertNotEquals(0, avgHeight);
    }

    /**
     * The test will return the min integer value from the person.csv file.
     * The min integer is received from the age column.
     */
    @Test
    public void calculateMinAge() {
        int min;
        min = calculation.calculateColumnMinInt("age", Person.class);

        assertEquals(21, min);
        assertNotEquals(0, min);
    }

    /**
     * The test will return the max integer value from the person.csv file.
     * The max integer is received from the age column
     */
    @Test
    public void calculateMaxAge() {
        int max;
        max = calculation.calculateColumnMaxInt("age", Person.class);

        assertNotEquals(0, max);
        assertEquals(38, max);
    }

    /**
     * The test will return the min double value from the person.csv file.
     * The min double is received from the height column.
     */
    @Test
    public void calculateMinHeight() {
        double min;
        min = calculation.calculateColumnMinDouble("height", Person.class);

        assertNotEquals(0, min);
        assertEquals(1.4, min);
    }

    /**
     * The test will return the max double value from the person.csv file.
     * The ,ax double is received from the height column.
     */
    @Test
    public void calculateMaxHeight() {
        double max;
        max = calculation.calculateColumnMaxDouble("height", Person.class);

        assertNotEquals(0, max);
        assertEquals(2.5, max);
    }

    /**
     * The test will return how many times an integer occurs in one column in the person.csv file.
     */
    @Test
    public void countAge() {
        int countAge;
        countAge = calculation.countIntValue("age", Person.class, 36);

        assertNotEquals(0, countAge);
        assertEquals(4, countAge);
    }

    /**
     * The test will return how many times a string occurs in one column in the person.csv file
     */
    @Test
    public void countName() {
        int countName;
        countName = calculation.countStringValue("first_name", Person.class, "Kyle");

        assertEquals(2, countName);
        assertNotEquals(0, countName);
    }

    /**
     * The test will return how many times a double value occurs in one column in the person.csv file.
     */
    @Test
    public void countHeight() {
        int countDouble;
        countDouble = calculation.countDoubleValue("height", Person.class, 2.3);

        assertNotEquals(0, countDouble);
        assertEquals(2, countDouble);
    }
}
