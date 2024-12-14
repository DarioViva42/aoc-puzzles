package tk.vivas.adventofcode.year2024.day10;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day10 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        HikingTrailArea trailArea = new HikingTrailArea(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = trailArea.trailHeadsScore();

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
