package vivas.tk.adventofcode.day01;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day01 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        CaloriesCalculator caloriesCalculator = new CaloriesCalculator(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = caloriesCalculator.calculateMaxCalories();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = caloriesCalculator.calculateMaxCaloriesTopThree();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
