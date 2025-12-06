package tk.vivas.adventofcode.year2025.day06;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day06 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        CephalopodsHomework homework = new CephalopodsHomework(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = homework.solve();

        Instant betweenParts = Instant.now();

        // long partTwoAnswer =

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
