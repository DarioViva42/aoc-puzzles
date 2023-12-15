package tk.vivas.adventofcode.year2023.day15;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

class Day15 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        AsciiStringHelperAlgorithm helperAlgorithm = new AsciiStringHelperAlgorithm(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = helperAlgorithm.hashSum();

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
