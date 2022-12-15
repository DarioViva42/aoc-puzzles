package vivas.tk.adventofcode.day14;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day14 {

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        SandSimulation sandSimulation = new SandSimulation(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = sandSimulation.fillCaveWithSand();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = sandSimulation.fillFlooredCaveWithSand();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
