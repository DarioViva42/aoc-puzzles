package vivas.tk.adventofcode.day08;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day08 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        TreePlantation treePlantation = new TreePlantation(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = treePlantation.countVisibleTrees();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = treePlantation.findOptimalScenicScore();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
