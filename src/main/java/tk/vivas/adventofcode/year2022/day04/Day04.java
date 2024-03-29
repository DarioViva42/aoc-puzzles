package tk.vivas.adventofcode.year2022.day04;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day04 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        OverlapFinder overlapFinder = new OverlapFinder(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = overlapFinder.countFullOverlaps();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = overlapFinder.countPartialOverlaps();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
