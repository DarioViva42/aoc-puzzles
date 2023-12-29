package tk.vivas.adventofcode.year2023.day24;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day24 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        HailStoneStorm storm = new HailStoneStorm(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = storm.countCollisions(200000000000000L, 400000000000000L);

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
