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

        OverlapFinder rucksackFixer = new OverlapFinder(input);

        int result1 = rucksackFixer.countFullOverlaps();
        int result2 = rucksackFixer.countPartialOverlaps();

        Instant finish = Instant.now();

        LOGGER.info(result1);
        LOGGER.info(result2);

        LOGGER.info(Duration.between(start, finish).toNanos());
    }
}
