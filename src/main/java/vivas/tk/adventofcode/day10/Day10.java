package vivas.tk.adventofcode.day10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day10 {

    private static final Logger LOGGER = LogManager.getLogger(Day10.class);

    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        VideoSystem videoSystem = new VideoSystem(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = videoSystem.calculateSignalStrength();

        Instant betweenParts = Instant.now();

        String partTwoOutput = videoSystem.renderAsciiArt();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        LOGGER.info(partTwoOutput);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
