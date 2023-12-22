package tk.vivas.adventofcode.year2023.day21;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class WalkableGardenTest {

    @Test
    void walkSteps() {
        String input = AocUtils.readPuzzleInput();

        WalkableGarden garden = new WalkableGarden(input);

        assertThat(garden.walkSteps(6)).isEqualTo(16);
    }
}