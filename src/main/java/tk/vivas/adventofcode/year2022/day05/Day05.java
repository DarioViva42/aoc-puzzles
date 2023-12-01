package tk.vivas.adventofcode.year2022.day05;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day05 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        RearrangementProcedure rearrangementProcedure = new RearrangementProcedure(input);

        Instant parseEnd = Instant.now();

        CargoCrane crateMover9000 = new CrateMover9000();
        String partOneAnswer = rearrangementProcedure.operateCargoCrane(crateMover9000);

        Instant betweenParts = Instant.now();

        rearrangementProcedure.resetStacks();

        CargoCrane crateMover9001 = new CrateMover9001();
        String partTwoAnswer = rearrangementProcedure.operateCargoCrane(crateMover9001);

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
