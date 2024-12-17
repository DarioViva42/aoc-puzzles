package tk.vivas.adventofcode.year2024.day14;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day14 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        BathroomOperation bathroomOperation = new BathroomOperation(input, 101, 103);

        Instant parseEnd = Instant.now();

        long partOneAnswer = bathroomOperation.safetyFactor();

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
