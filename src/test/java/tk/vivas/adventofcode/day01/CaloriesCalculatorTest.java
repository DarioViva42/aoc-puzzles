package tk.vivas.adventofcode.day01;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

class CaloriesCalculatorTest {

    @Test
    void calculateMaxCalories() {
        String input = readPuzzleInput();
        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(input);

        assertThat( caloriesCalculator.calculateMaxCalories() ).isEqualTo(24000);
    }

    @Test
    void calculateMaxCaloriesTopThree() {
        String input = readPuzzleInput();
        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(input);

        assertThat( caloriesCalculator.calculateMaxCaloriesTopThree() ).isEqualTo(45000);
    }
}