package vivas.tk.adventofcode.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

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