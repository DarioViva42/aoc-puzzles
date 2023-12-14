package tk.vivas.adventofcode.year2023.day13;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day13 {
    public static void main(String[] args){
        String input = readPuzzleInput();

        Instant start = Instant.now();

        MirrorValley valley = new MirrorValley(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = valley.summarizePatterns();

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
