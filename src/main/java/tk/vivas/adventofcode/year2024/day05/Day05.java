package tk.vivas.adventofcode.year2024.day05;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day05 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        SleightLaunchSafetyManual safetyManual = new SleightLaunchSafetyManual(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = safetyManual.correctPagesScore();

        Instant betweenParts = Instant.now();

        // long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
