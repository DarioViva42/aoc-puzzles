package tk.vivas.adventofcode.year2025.day11;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

public class Day11 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        Reactor reactor = new Reactor(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = reactor.numberOfPaths();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = reactor.numberOfValidPaths();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
