package tk.vivas.adventofcode.year2023.day14;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

class Day14 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        ReflectorDish dish = new ReflectorDish(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = dish.totalLoadAfterTilt();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = dish.totalLoadAfterSpinCycle();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
