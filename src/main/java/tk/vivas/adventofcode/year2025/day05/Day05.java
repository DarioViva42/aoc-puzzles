package tk.vivas.adventofcode.year2025.day05;

import java.time.Instant;

import static tk.vivas.adventofcode.AocUtils.*;

public class Day05 {
    public static void main(String[] args) {
        String input = readPuzzleInput();

        Instant start = Instant.now();

        IngredientDatabase ingredientDatabase = new IngredientDatabase(input);

        Instant parseEnd = Instant.now();

        long partOneAnswer = ingredientDatabase.freshIngredientIds();

        Instant betweenParts = Instant.now();

         long partTwoAnswer = ingredientDatabase.totalFreshIngredients();

        Instant end = Instant.now();

        sendPuzzleAnswer(1, partOneAnswer);
        sendPuzzleAnswer(2, partTwoAnswer);

        logDurations(start, parseEnd, betweenParts, end);
    }
}
