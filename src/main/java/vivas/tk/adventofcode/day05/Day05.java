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

        CraneOperation craneOperation = new CraneOperation(input);

        CargoCrane crateMover9000 = new CrateMover9000();
        String result1 = craneOperation.operateCargoCrane(crateMover9000);

        craneOperation.resetStacks();

        CargoCrane crateMover9001 = new CrateMover9001();
        String result2 = craneOperation.operateCargoCrane(crateMover9001);

        Instant finish = Instant.now();

        LOGGER.info(result1);
        LOGGER.info(result2);

        LOGGER.info(Duration.between(start, finish).toNanos());
    }
}
