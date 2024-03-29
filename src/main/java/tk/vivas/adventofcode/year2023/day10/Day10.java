package tk.vivas.adventofcode.year2023.day10;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

class Day10 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        PipePuzzle puzzle = new PipePuzzle(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = puzzle.findFurthestPointInLoop();

        Instant betweenParts = Instant.now();

        long partTwoAnswer = puzzle.countEnclosedTiles();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);

        System.out.println(puzzle);
    }
}
