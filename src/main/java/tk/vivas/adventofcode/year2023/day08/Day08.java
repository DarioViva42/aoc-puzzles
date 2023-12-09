package tk.vivas.adventofcode.year2023.day08;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day08 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        NetworkDocument networkDocument = new NetworkDocument(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = networkDocument.requiredSteps();

        Instant betweenParts = Instant.now();

        // long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
