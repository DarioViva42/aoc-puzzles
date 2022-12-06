package vivas.tk.adventofcode.day06;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;

import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class Day06 {

    private static final Logger LOGGER = LogManager.getLogger(Day06.class);

    public static void main(String[] args) {
        String input = readFile("/06.txt").get(0);

        Instant start = Instant.now();

        BufferedDataStreamReader dataStreamReader = new BufferedDataStreamReader(input);

        int result1 = dataStreamReader.findStartOfPacketMarker();

        int result2 = dataStreamReader.findStartOfMessageMarker();

        Instant finish = Instant.now();

        LOGGER.info(result1);
        LOGGER.info(result2);

        LOGGER.info(Duration.between(start, finish).toNanos());
    }
}
