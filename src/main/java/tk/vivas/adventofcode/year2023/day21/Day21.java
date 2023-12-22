package tk.vivas.adventofcode.year2023.day21;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day21 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        WalkableGarden network = new WalkableGarden(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = network.walkSteps(64);

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
