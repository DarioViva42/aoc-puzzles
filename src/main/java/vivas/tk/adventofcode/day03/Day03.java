package vivas.tk.adventofcode.day03;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day03 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        RucksackFixer rucksackFixer = new RucksackFixer(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = rucksackFixer.calculateErrorPrioritySum();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = rucksackFixer.calculateGroupPrioritySum();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
