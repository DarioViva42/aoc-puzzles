package tk.vivas.adventofcode.year2022.day09;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day09 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        RopeSimulation ropeSimulation = new RopeSimulation(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = ropeSimulation.runSimulation(1);

        Instant betweenParts = Instant.now();

        int partTwoAnswer = ropeSimulation.runSimulation(9);

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
