package tk.vivas.adventofcode.year2025.day05;

import java.util.ArrayList;
import java.util.List;

class IngredientDatabase {

    private final List<IdRange> ranges;
    private final List<Long> ingredients;

    IngredientDatabase(String input) {
        String[] split = input.split("\n\n");

        ranges = split[0].lines()
                .map(IdRange::new)
                .toList();

        ingredients = split[1].lines()
                .map(Long::parseLong)
                .toList();
    }

    long freshIngredientIds() {
        return ingredients.stream()
                .filter(this::isFresh)
                .count();
    }

    private boolean isFresh(Long ingredientId) {
        return ranges.stream()
                .anyMatch(range -> range.contains(ingredientId));
    }

    long totalFreshIngredients() {
        return mergedRanges().stream()
                .mapToLong(IdRange::totalIds)
                .sum();
    }

    private List<IdRange> mergedRanges() {
        List<IdRange> mergedRanges = new ArrayList<>(ranges);
        int leftIndex = 0;

        outerloop:
        while (leftIndex < mergedRanges.size()) {

            IdRange left = mergedRanges.get(leftIndex);

            int rightIndex = leftIndex + 1;
            while (rightIndex < mergedRanges.size()) {
                IdRange right = mergedRanges.get(rightIndex);

                if (left.mergeable(right)) {
                    mergedRanges.set(leftIndex, left.merge(right));
                    mergedRanges.remove(rightIndex);

                    leftIndex = 0;
                    continue outerloop;
                }

                rightIndex++;
            }

            leftIndex++;
        }

        return mergedRanges;
    }
}
