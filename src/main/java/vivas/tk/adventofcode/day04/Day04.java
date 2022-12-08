package vivas.tk.adventofcode.day04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class Day04 {

    private static final Logger LOGGER = LogManager.getLogger(Day04.class);

    public static void main(String[] args) {
        List<String> input = readFile("/04.txt");

        Instant start = Instant.now();

        OverlapFinder overlapFinder = new OverlapFinder(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = overlapFinder.countFullOverlaps();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = overlapFinder.countPartialOverlaps();

        Instant end = Instant.now();

        LOGGER.info("answer 1: {}", partOneAnswer);
        LOGGER.info("answer 2: {}", partTwoAnswer);

        LOGGER.info("parsing: {}ms", Duration.between(start, parseEnd).toMillis());
        LOGGER.info("part 1: {}ms", Duration.between(parseEnd, betweenParts).toMillis());
        LOGGER.info("part 2: {}ms", Duration.between(betweenParts, end).toMillis());
        LOGGER.info("total: {}ms", Duration.between(start, end).toMillis());
    }
}
