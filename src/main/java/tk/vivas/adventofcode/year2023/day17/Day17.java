package tk.vivas.adventofcode.year2023.day17;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day17 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        FactoryCityMap cityMap = new FactoryCityMap(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = cityMap.findMinimalHeatLoss();

        Instant betweenParts = Instant.now();

        //int partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
