package tk.vivas.adventofcode.year2023.day06;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

class Day06 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        RaceDocument raceDocument = new RaceDocument(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = raceDocument.marginOfError();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = raceDocument.realMarginOfError();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
