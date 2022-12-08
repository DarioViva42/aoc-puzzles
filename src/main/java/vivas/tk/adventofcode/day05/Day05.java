package vivas.tk.adventofcode.day05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class Day05 {

    private static final Logger LOGGER = LogManager.getLogger(Day05.class);

    public static void main(String[] args) {
        List<String> input = readFile("/05.txt");

        Instant start = Instant.now();

        RearrangementProcedure rearrangementProcedure = new RearrangementProcedure(input);

        Instant parseEnd = Instant.now();

        CargoCrane crateMover9000 = new CrateMover9000();
        String partOneAnswer = rearrangementProcedure.operateCargoCrane(crateMover9000);

        Instant betweenParts = Instant.now();

        rearrangementProcedure.resetStacks();

        CargoCrane crateMover9001 = new CrateMover9001();
        String partTwoAnswer = rearrangementProcedure.operateCargoCrane(crateMover9001);

        Instant end = Instant.now();

        LOGGER.info("answer 1: {}", partOneAnswer);
        LOGGER.info("answer 2: {}", partTwoAnswer);

        LOGGER.info("parsing: {}ms", Duration.between(start, parseEnd).toMillis());
        LOGGER.info("part 1: {}ms", Duration.between(parseEnd, betweenParts).toMillis());
        LOGGER.info("part 2: {}ms", Duration.between(betweenParts, end).toMillis());
        LOGGER.info("total: {}ms", Duration.between(start, end).toMillis());
    }
}
