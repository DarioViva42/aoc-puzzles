package tk.vivas.adventofcode.year2023.day22;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day22 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        SandBrickTower tower = new SandBrickTower(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = tower.countDisintegrationCandidates();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = tower.sumOfFallingBricks();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
