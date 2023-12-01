package tk.vivas.adventofcode.year2022.day02;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day02 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        RockPaperScissorsCompetition rockPaperScissorsCompetition = new RockPaperScissorsCompetition(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = rockPaperScissorsCompetition.calculateScore();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = rockPaperScissorsCompetition.calculateCorrectScore();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
