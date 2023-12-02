package tk.vivas.adventofcode.year2023.day02;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

public class Day02 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        SnowIslandGames game = new SnowIslandGames(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = game.sumOfPossibleGameIds();

        Instant betweenParts = Instant.now();

        // long partTwoAnswer =

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(2, partTwoAnswer);

        // logDurations(start, parseEnd, betweenParts, end);
    }
}
