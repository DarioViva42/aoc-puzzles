package vivas.tk.adventofcode.day11;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day11 {

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        KeepAwayGame keepAwayGame1 = new KeepAwayGame(input);
        KeepAwayGame keepAwayGame2 = new KeepAwayGame(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = keepAwayGame1.play(20);

        Instant betweenParts = Instant.now();

        long partTwoAnswer = keepAwayGame2.playWithoutRelief(10000);

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
