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

        int result1 = fileSystem.countSizeNaiveApproach();

        long result2 = fileSystem.findSmallestToDeleteFolder()
                .map(Folder::size)
                .orElseThrow();

        Instant finish = Instant.now();

        LOGGER.info(result1);
        LOGGER.info(result2);

        LOGGER.info(Duration.between(start, finish).toNanos());
    }
}
