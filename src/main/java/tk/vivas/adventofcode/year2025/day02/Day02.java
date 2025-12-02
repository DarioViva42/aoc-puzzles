package tk.vivas.adventofcode.year2025.day02;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day02 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        GiftShopDatabase database = new GiftShopDatabase(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = database.countInvalidIds();

        Instant betweenParts = Instant.now();

        // long partTwoAnswer = rotatingDial.countAllZeroPositions(50);

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        // sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
