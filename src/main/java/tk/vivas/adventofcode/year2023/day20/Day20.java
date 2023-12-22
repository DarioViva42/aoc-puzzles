package tk.vivas.adventofcode.year2023.day20;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

class Day20 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        ModuleNetwork network = new ModuleNetwork(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = network.simulate();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = network.countButtonPresses();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
