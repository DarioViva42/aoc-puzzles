package tk.vivas.adventofcode.year2023.day11;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day11 {
    public static void main(String[] args){
        String input = readPuzzleInput();

        Instant start = Instant.now();

        GalaxyObservation observation = new GalaxyObservation(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = observation.sumUpDistances(1);

        Instant betweenParts = Instant.now();

        long partTwoAnswer = observation.sumUpDistances(1_000_000);

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
