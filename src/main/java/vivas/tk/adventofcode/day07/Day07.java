package vivas.tk.adventofcode.day07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class Day07 {

    private static final Logger LOGGER = LogManager.getLogger(Day07.class);

    public static void main(String[] args) {
        List<String> input = readFile("/07.txt");

        Instant start = Instant.now();

        FileSystem fileSystem = new FileSystem(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = fileSystem.countSizeNaiveApproach();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = fileSystem.findSmallestToDeleteFolder()
                .map(Folder::size)
                .orElseThrow();

        Instant end = Instant.now();

        LOGGER.info("answer 1: {}", partOneAnswer);
        LOGGER.info("answer 2: {}", partTwoAnswer);

        LOGGER.info("parsing: {}ms", Duration.between(start, parseEnd).toMillis());
        LOGGER.info("part 1: {}ms", Duration.between(parseEnd, betweenParts).toMillis());
        LOGGER.info("part 2: {}ms", Duration.between(betweenParts, end).toMillis());
        LOGGER.info("total: {}ms", Duration.between(start, end).toMillis());
    }
}
