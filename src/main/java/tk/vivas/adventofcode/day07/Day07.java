package tk.vivas.adventofcode.day07;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day07 {
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

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
