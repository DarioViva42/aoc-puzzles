package tk.vivas.adventofcode.year2025.day04;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

public class Day04 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        PrintingDepartment printingDepartment = new PrintingDepartment(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = printingDepartment.accessibleRollsOfPaper();

        Instant betweenParts = Instant.now();

        //long partTwoAnswer =

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
