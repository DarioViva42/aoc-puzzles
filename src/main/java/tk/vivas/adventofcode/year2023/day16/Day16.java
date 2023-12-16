package tk.vivas.adventofcode.year2023.day16;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day16 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        FacilityContraption contraption = new FacilityContraption(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = contraption.traceLight();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = contraption.traceMaxLight();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
