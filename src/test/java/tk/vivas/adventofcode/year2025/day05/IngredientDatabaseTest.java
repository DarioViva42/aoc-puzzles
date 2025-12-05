package tk.vivas.adventofcode.year2025.day05;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class IngredientDatabaseTest {

    @Test
    void freshIngredientIds() {
        String input = AocUtils.readPuzzleInput();

        IngredientDatabase ingredientDatabase = new IngredientDatabase(input);

        assertThat(ingredientDatabase.freshIngredientIds()).isEqualTo(3);
    }

    @Test
    void totalFreshIngredients() {
        String input = AocUtils.readPuzzleInput();

        IngredientDatabase ingredientDatabase = new IngredientDatabase(input);

        assertThat(ingredientDatabase.totalFreshIngredients()).isEqualTo(14);
    }
}
