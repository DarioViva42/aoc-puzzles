package tk.vivas.adventofcode.year2023.day01;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day01 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        CalibrationDocument calibrationDocument = new CalibrationDocument(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = calibrationDocument.recover();

        // Instant betweenParts = Instant.now();

        // int partTwoAnswer = caloriesCalculator.calculateMaxCaloriesTopThree();

        // Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(2, partTwoAnswer);

        // logDurations(start, parseEnd, betweenParts, end);
    }
}
