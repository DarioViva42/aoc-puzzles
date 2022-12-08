package vivas.tk.adventofcode.day02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class Day02 {

    private static final Logger LOGGER = LogManager.getLogger(Day02.class);

    public static void main(String[] args) {
        List<String> input = readFile("/02.txt");

        Instant start = Instant.now();

        RockPaperScissorsCompetition rockPaperScissorsCompetition = new RockPaperScissorsCompetition(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = rockPaperScissorsCompetition.calculateScore();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = rockPaperScissorsCompetition.calculateCorrectScore();

        Instant end = Instant.now();

        LOGGER.info("answer 1: {}", partOneAnswer);
        LOGGER.info("answer 2: {}", partTwoAnswer);

        LOGGER.info("parsing: {}ms", Duration.between(start, parseEnd).toMillis());
        LOGGER.info("part 1: {}ms", Duration.between(parseEnd, betweenParts).toMillis());
        LOGGER.info("part 2: {}ms", Duration.between(betweenParts, end).toMillis());
        LOGGER.info("total: {}ms", Duration.between(start, end).toMillis());
    }
}
