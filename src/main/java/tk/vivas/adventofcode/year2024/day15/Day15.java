package tk.vivas.adventofcode.year2024.day15;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day15 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        LanternfishWarehouse warehouse = new LanternfishWarehouse(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = warehouse.coordinatesSum();

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
