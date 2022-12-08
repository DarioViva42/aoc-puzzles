package vivas.tk.adventofcode.day01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

public class Day01 {

    private static final Logger LOGGER = LogManager.getLogger(Day01.class);

    public static void main(String[] args) {
        List<String> input = readPuzzleInput();

        Instant start = Instant.now();

        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = caloriesCalculator.calculateMaxCalories();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = caloriesCalculator.calculateMaxCaloriesTopThree();

        Instant end = Instant.now();

        LOGGER.info("answer 1: {}", partOneAnswer);
        LOGGER.info("answer 2: {}", partTwoAnswer);

        LOGGER.info("parsing: {}ms", Duration.between(start, parseEnd).toMillis());
        LOGGER.info("part 1: {}ms", Duration.between(parseEnd, betweenParts).toMillis());
        LOGGER.info("part 2: {}ms", Duration.between(betweenParts, end).toMillis());
        LOGGER.info("total: {}ms", Duration.between(start, end).toMillis());
    }
}
