package tk.vivas.adventofcode.year2024.day13;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day13 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        ClawContraption clawContraption = new ClawContraption(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = clawContraption.fewestTokens();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = clawContraption.fewestTokensWithConversionFix();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
