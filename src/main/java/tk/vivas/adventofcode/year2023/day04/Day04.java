package tk.vivas.adventofcode.year2023.day04;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

public class Day04 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

		ScratchCardPile scratchCardPile = new ScratchCardPile(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = scratchCardPile.score();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = scratchCardPile.countAllCards();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
