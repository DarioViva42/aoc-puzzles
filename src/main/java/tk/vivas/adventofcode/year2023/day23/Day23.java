package tk.vivas.adventofcode.year2023.day23;

import tk.vivas.adventofcode.year2023.day22.SandBrickTower;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day23 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        HikingTrailMap trailMap = new HikingTrailMap(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = trailMap.findLongestPath();

        Instant betweenParts = Instant.now();

        //long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
