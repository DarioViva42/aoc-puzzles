package tk.vivas.adventofcode.year2023.day09;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day09 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        OASIS oasis = new OASIS(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = oasis.sumUpExtrapolatedValues();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = oasis.sumUpBackwardExtrapolatedValues();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
