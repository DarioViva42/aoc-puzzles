package tk.vivas.adventofcode.year2024.day12;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day12 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        GardenGroups gardenGroups = new GardenGroups(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = gardenGroups.totalFencingPrice();

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
