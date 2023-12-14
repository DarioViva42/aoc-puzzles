package tk.vivas.adventofcode.year2023.day14;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReflectorDishTest {

    @Test
    void totalLoadAfterTilt() {
        String input = AocUtils.readPuzzleInput();

        ReflectorDish dish = new ReflectorDish(input);

        assertThat(dish.totalLoadAfterTilt()).isEqualTo(136);
    }

    @Test
    void totalLoadAfterSpinCycle() {
        String input = AocUtils.readPuzzleInput();

        ReflectorDish dish = new ReflectorDish(input);

        assertThat(dish.totalLoadAfterSpinCycle()).isEqualTo(64);
    }
}
