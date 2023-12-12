package tk.vivas.adventofcode.year2023.day05;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day05 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        IslandIslandAlmanac almanac = new IslandIslandAlmanac(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = almanac.findClosestLocation();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = almanac.findRealClosestLocation();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
