package vivas.tk.adventofcode.day01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class Day01 {

    private static final Logger LOGGER = LogManager.getLogger(Day01.class);

    public static void main(String[] args) {
        List<String> input = readFile("/01.txt");

        Instant start = Instant.now();

        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(input);

        int result1 = caloriesCalculator.calculateMaxCalories();
        int result2 = caloriesCalculator.calculateMaxCaloriesTopThree();

        Instant finish = Instant.now();

        LOGGER.info(result1);
        LOGGER.info(result2);

        LOGGER.info(Duration.between(start, finish).toNanos());
    }
}
