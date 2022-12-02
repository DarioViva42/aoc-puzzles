package vivas.tk.adventofcode.day01;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class CaloriesCalculatorTest extends TestCase {

    public void testCalculateMaxCalories() {
        List<String> input = readFile("/01.txt");
        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(input);

        assertThat( caloriesCalculator.calculateMaxCalories() ).isEqualTo(24000);
    }

    public void testCalculateMaxCaloriesTopThree() {
        List<String> input = readFile("/01.txt");
        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(input);

        assertThat( caloriesCalculator.calculateMaxCaloriesTopThree() ).isEqualTo(45000);
    }
}