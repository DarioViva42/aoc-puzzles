package vivas.tk.adventofcode.day16;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day16 {

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        Volcano volcano = new Volcano(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = volcano.findBestPressureReleaseStrategy();

        Instant betweenParts = Instant.now();

        // long partTwoAnswer = volcano.findBestPressureReleaseStrategy();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
