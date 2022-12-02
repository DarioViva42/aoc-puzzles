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

        int result1 = rockPaperScissorsCompetition.calculateScore();
        int result2 = rockPaperScissorsCompetition.calculateCorrectScore();

        Instant finish = Instant.now();

        LOGGER.info(result1);
        LOGGER.info(result2);

        LOGGER.info(Duration.between(start, finish).toNanos());
    }
}
