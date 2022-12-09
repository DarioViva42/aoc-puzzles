package vivas.tk.adventofcode.day08;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

public class Day08 {

    private static final Logger LOGGER = LogManager.getLogger(Day08.class);

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        TreePlantation treePlantation = new TreePlantation(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = treePlantation.countVisibleTrees();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = treePlantation.findOptimalScenicScore();

        Instant end = Instant.now();

        LOGGER.info("answer 1: {}", partOneAnswer);
        LOGGER.info("answer 2: {}", partTwoAnswer);

        LOGGER.info("parsing: {}ms", Duration.between(start, parseEnd).toMillis());
        LOGGER.info("part 1: {}ms", Duration.between(parseEnd, betweenParts).toMillis());
        LOGGER.info("part 2: {}ms", Duration.between(betweenParts, end).toMillis());
        LOGGER.info("total: {}ms", Duration.between(start, end).toMillis());
    }
}
