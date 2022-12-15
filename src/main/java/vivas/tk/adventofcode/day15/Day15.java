package vivas.tk.adventofcode.day15;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day15 {

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        BeaconFinder beaconFinder = new BeaconFinder(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = beaconFinder.countEmptyPositionsOnLine(2_000_000);

        Instant betweenParts = Instant.now();

        // int partTwoAnswer = beaconFinder.findHiddenBeacon();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
