package vivas.tk.adventofcode.day03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class Day03 {

    private static final Logger LOGGER = LogManager.getLogger(Day03.class);

    public static void main(String[] args) {
        List<String> input = readFile("/03.txt");

        Instant start = Instant.now();

        RucksackFixer rucksackFixer = new RucksackFixer(input);

        int result1 = rucksackFixer.calculateErrorPrioritySum();
        int result2 = rucksackFixer.calculateGroupPrioritySum();

        Instant finish = Instant.now();

        LOGGER.info(result1);
        LOGGER.info(result2);

        LOGGER.info(Duration.between(start, finish).toNanos());
    }
}
