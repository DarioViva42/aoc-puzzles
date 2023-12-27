package tk.vivas.adventofcode.year2023.day23;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day23 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        HikingTrailMap trailMap = new HikingTrailMap(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = trailMap.findLongestPath();

        Instant betweenParts = Instant.now();

        int partTwoAnswer = trailMap.findLongestPathIgnoringSlopes();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);

        System.out.println(trailMap);
    }
}
