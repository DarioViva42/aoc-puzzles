package tk.vivas.adventofcode.year2025.day08;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

public class Day08 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        DecorationProject decorationProject = new DecorationProject(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = decorationProject.connectJunctionBoxes(1000);

        Instant betweenParts = Instant.now();

        long partTwoAnswer = decorationProject.connectAllJunctionBoxes();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
