package tk.vivas.adventofcode.year2025.day05;

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
}
