package vivas.tk.adventofcode.day13;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day13 {

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        DistressSignalFinder signalFinder = new DistressSignalFinder(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = signalFinder.evaluateCorrectlyOrderedPairs();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = signalFinder.findDecoderKey();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
