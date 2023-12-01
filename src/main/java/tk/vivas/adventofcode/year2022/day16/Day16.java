package tk.vivas.adventofcode.year2022.day16;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day16 {

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        Volcano volcano = new Volcano(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = volcano.findBestPressureReleaseStrategy();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = volcano.findBestPressureReleaseStrategyWithElephant();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
