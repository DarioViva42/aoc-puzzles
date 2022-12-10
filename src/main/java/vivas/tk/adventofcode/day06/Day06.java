package vivas.tk.adventofcode.day06;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day06 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        BufferedDataStreamReader dataStreamReader = new BufferedDataStreamReader(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = dataStreamReader.findStartOfPacketMarker();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = dataStreamReader.findStartOfMessageMarker();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
