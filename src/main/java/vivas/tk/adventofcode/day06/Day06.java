package vivas.tk.adventofcode.day06;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

public class Day06 {

    private static final Logger LOGGER = LogManager.getLogger(Day06.class);

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        BufferedDataStreamReader dataStreamReader = new BufferedDataStreamReader(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = dataStreamReader.findStartOfPacketMarker();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = dataStreamReader.findStartOfMessageMarker();

        Instant end = Instant.now();

        LOGGER.info("answer 1: {}", partOneAnswer);
        LOGGER.info("answer 2: {}", partTwoAnswer);

        LOGGER.info("parsing: {}ms", Duration.between(start, parseEnd).toMillis());
        LOGGER.info("part 1: {}ms", Duration.between(parseEnd, betweenParts).toMillis());
        LOGGER.info("part 2: {}ms", Duration.between(betweenParts, end).toMillis());
        LOGGER.info("total: {}ms", Duration.between(start, end).toMillis());
    }
}
