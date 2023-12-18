package tk.vivas.adventofcode.year2023.day18;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

class Day18 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        DigSite digSite = new DigSite(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = digSite.countSize();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = digSite.countLargeSize();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
