package vivas.tk.adventofcode.day07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.*;

public class Day07 {

    private static final Logger LOGGER = LogManager.getLogger(Day07.class);

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        FileSystem fileSystem = new FileSystem(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = fileSystem.countSizeNaiveApproach();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = fileSystem.findSmallestToDeleteFolder()
                .map(Folder::size)
                .orElseThrow();

        Instant end = Instant.now();

        System.out.println(sendPuzzleAnswer(1, partOneAnswer));
        System.out.println(sendPuzzleAnswer(2, partTwoAnswer));

        LOGGER.info("parsing: {}ms", Duration.between(start, parseEnd).toMillis());
        LOGGER.info("part 1: {}ms", Duration.between(parseEnd, betweenParts).toMillis());
        LOGGER.info("part 2: {}ms", Duration.between(betweenParts, end).toMillis());
        LOGGER.info("total: {}ms", Duration.between(start, end).toMillis());
    }
}
