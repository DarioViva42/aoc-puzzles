package vivas.tk.adventofcode.day11;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day11 {

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        KeepAwayGame keepAwayGame = new KeepAwayGame(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = keepAwayGame.play(20);

        Instant betweenParts = Instant.now();

        // int partTwoAnswer = keepAwayGame.play(100);

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(1, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
