package tk.vivas.adventofcode.year2024.day11;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class PlutonianPebblesTest {

    @Test
    void countStonesAfterShortTime() {
        String input = AocUtils.readPuzzleInput();

        PlutonianPebbles pebbles = new PlutonianPebbles(input);

        assertThat(pebbles.countStonesAfterShortTime()).isEqualTo(55312);
    }
}