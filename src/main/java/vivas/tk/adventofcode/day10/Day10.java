package vivas.tk.adventofcode.day10;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day10 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        VideoSystem videoSystem = new VideoSystem(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = videoSystem.calculateSignalStrength();

        Instant betweenParts = Instant.now();

        // int partTwoAnswer = videoSystem.calculateSignalStrength();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
