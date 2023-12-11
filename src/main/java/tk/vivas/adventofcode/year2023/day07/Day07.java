package tk.vivas.adventofcode.year2023.day07;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.logDurations;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;
import static tk.vivas.adventofcode.AocUtils.sendPuzzleAnswer;

class Day07 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        CamelCardsGame cardsGame = new CamelCardsGame(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = cardsGame.totalWinnings();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = cardsGame.totalWinningsWithJoker();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
