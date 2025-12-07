package tk.vivas.adventofcode.year2025.day07;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day07 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        TachyonManifold manifold = new TachyonManifold(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = manifold.usedSplitters();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = manifold.timelines();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
