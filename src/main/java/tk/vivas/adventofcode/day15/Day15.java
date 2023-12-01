package tk.vivas.adventofcode.day15;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day15 {

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        BeaconFinder beaconFinder = new BeaconFinder(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = beaconFinder.countEmptyPositionsOnLine(2_000_000);

        Instant betweenParts = Instant.now();

        long partTwoAnswer = beaconFinder.findTuningFrequency(4_000_000);

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
