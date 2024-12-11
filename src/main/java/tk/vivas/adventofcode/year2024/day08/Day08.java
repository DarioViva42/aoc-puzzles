package tk.vivas.adventofcode.year2024.day08;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day08 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        AntinodeCounter antinodeCounter = new AntinodeCounter(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = antinodeCounter.countUniqueLocations();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = antinodeCounter.countAllUniqueLocations();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
