package vivas.tk.adventofcode.day12;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day12 {

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        HillArea hillArea = new HillArea(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = hillArea.findShortestPathFromStart();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = hillArea.findShortestPath();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
