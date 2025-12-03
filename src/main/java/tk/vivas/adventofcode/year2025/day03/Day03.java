package tk.vivas.adventofcode.year2025.day03;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day03 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        EscalatorPowerSource powerSource = new EscalatorPowerSource(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = powerSource.totalJoltage();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = powerSource.improvedTotalJoltage();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
