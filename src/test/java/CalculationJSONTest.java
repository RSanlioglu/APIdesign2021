import Calculation.CalculationJSON;
import DataAccess.DataAccessJSON;
import Model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalculationJSONTest {
    private static final DataAccessJSON dataAccess = new DataAccessJSON("src/test/java/DataFiles/people.json", Person.class);
    private static final CalculationJSON calculation = new CalculationJSON(dataAccess);

    /**
     * The test will calculate the sum weight between the people in people.json file.
     * One assertion is to check if the sum is actually correct (which it is), and the
     * second assertion is to double check that the sum isn't something not right.
     */
    @Test
    public void calculateSumIntWeight() {
        int sum;
        sum = calculation.calculateColumnSumInt("weight", Person.class);

        assertEquals(sum, 1876);
        assertNotEquals(sum, 314);
    }

    /**
     * The test will sum up the total height of the people in person.json file.
     * The sum is supposed to be correct with the assertion.
     */
    @Test
    public void calculateDoubleHeight() {
        double sum;
        sum = Math.round(calculation.calculateColumnSumDouble("height", Person.class));

        assertEquals(sum, 39); //We round the number in order to not work with very precise numbers
        assertNotEquals(sum, 18.0);
    }

    /**
     * The test will find the average age between the people in person.json file.
     * Note that the assertion is supposed to match.
     */
    @Test
    public void calculateAverageAgeInt() {
        double average;
        average = calculation.calculateColumnAverageInt("age", Person.class);

        assertEquals(Math.round(average), 32); //We round the number so we don't work with very precise numbers
        assertNotEquals(average, 31);
    }

    /**
     * The test will find the average height between the people in person.json file.
     * Note that the assertion is supposed to match
     */
    @Test
    public void calculateAverageHeightDouble() {
        double avgHeight;
        avgHeight = calculation.calculateColumnAverageDouble("height", Person.class);

        assertEquals(2, Math.round(avgHeight)); //We round the number so that we don't have to work with precise numbers
        assertNotEquals(0, avgHeight);
    }

    /**
     * The test will return the min integer value from the person.json file.
     * The min integer is received from the age column.
     */
    @Test
    public void calculateMinAge() {
        int min;
        min = calculation.calculateColumnMinInt("age", Person.class);

        assertEquals(22, min);
        assertNotEquals(0, min);
    }

    /**
     * The test will return the max integer value from the person.json file.
     * The max integer is received from the age column
     */
    @Test
    public void calculateMaxAge() {
        int max;
        max = calculation.calculateColumnMaxInt("age", Person.class);

        assertNotEquals(0, max);
        assertEquals(39, max);
    }

    /**
     * The test will return the min double value from the person.json file.
     * The min double is received from the height column.
     */
    @Test
    public void calculateMinHeight() {
        double min;
        min = calculation.calculateColumnMinDouble("height", Person.class);

        assertNotEquals(0, min);
        assertEquals(1.3, min);
    }

    /**
     * The test will return the max double value from the person.json file.
     * The ,ax double is received from the height column.
     */
    @Test
    public void calculateMaxHeight() {
        double max;
        max = calculation.calculateColumnMaxDouble("height", Person.class);

        assertNotEquals(0, max);
        assertEquals(2.4, max);
    }

    /**
     * The test will return how many times an integer occurs in one column in the person.json file.
     */
    @Test
    public void countAge() {
        int countAge;
        countAge = calculation.countIntValue("age", Person.class, 28);

        assertNotEquals(0, countAge);
        assertEquals(2, countAge);
    }

    /**
     * The test will return how many times a string occurs in one column in the person.json file
     */
    @Test
    public void countName() {
        int countName = 0;
        countName = calculation.countStringValue("first_name", Person.class, "Shirley");

        assertEquals(2, countName);
        assertNotEquals(0, countName);
    }

    /**
     * The test will return how many times a double value occurs in one column in the person.json file.
     */
    @Test
    public void countHeight() {
        int countDouble;
        countDouble = calculation.countDoubleValue("height", Person.class, 2);

        assertNotEquals(0, countDouble);
        assertEquals(3, countDouble);
    }
}
