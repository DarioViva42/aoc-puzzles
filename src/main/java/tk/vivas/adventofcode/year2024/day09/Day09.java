package tk.vivas.adventofcode.year2024.day09;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day09 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        FileArranger fileArranger = new FileArranger(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = fileArranger.filesystemChecksumWithFragmenting();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = fileArranger.filesystemChecksumWithoutFragmenting();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
