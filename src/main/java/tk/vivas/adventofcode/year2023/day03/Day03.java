package tk.vivas.adventofcode.year2023.day03;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day03 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        EngineSchematic engineSchematic = new EngineSchematic(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = engineSchematic.calculatePartNumberSum();

        Instant betweenParts = Instant.now();

        // long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
