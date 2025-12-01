package tk.vivas.adventofcode.year2025.day01;


import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day01 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        RotatingDial rotatingDial = new RotatingDial(input);

        Instant parseEnd = Instant.now();

        int partOneAnswer = rotatingDial.countZeroPositions(50);

        Instant betweenParts = Instant.now();

        // long partTwoAnswer = ;

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        //sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
