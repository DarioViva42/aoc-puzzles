package tk.vivas.adventofcode.year2024.day01;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day01 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        ChiefsOffice office = new ChiefsOffice(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = office.totalDistance();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = office.similarityScore();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
