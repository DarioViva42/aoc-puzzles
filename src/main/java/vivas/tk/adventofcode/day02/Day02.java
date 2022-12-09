package vivas.tk.adventofcode.day02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;
import static vivas.tk.adventofcode.GeneralUtils.sendPuzzleAnswer;

public class Day02 {

    private static final Logger LOGGER = LogManager.getLogger(Day02.class);

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        RockPaperScissorsCompetition rockPaperScissorsCompetition = new RockPaperScissorsCompetition(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = rockPaperScissorsCompetition.calculateScore();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = rockPaperScissorsCompetition.calculateCorrectScore();

        Instant end = Instant.now();

        System.out.println(sendPuzzleAnswer(1, partOneAnswer));
        System.out.println(sendPuzzleAnswer(2, partTwoAnswer));

        LOGGER.info("parsing: {}ms", Duration.between(start, parseEnd).toMillis());
        LOGGER.info("part 1: {}ms", Duration.between(parseEnd, betweenParts).toMillis());
        LOGGER.info("part 2: {}ms", Duration.between(betweenParts, end).toMillis());
        LOGGER.info("total: {}ms", Duration.between(start, end).toMillis());
    }
}
