package tk.vivas.adventofcode.year2024.day11;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day11 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        PlutonianPebbles pebbles = new PlutonianPebbles(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = pebbles.countStonesAfterShortTime();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = pebbles.countStonesAfterLongTime();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
