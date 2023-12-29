package tk.vivas.adventofcode.year2023.day24;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class HailStoneStormTest {

    @Test
    void countCollisions() {
        String input = AocUtils.readPuzzleInput();

        HailStoneStorm storm = new HailStoneStorm(input);

        assertThat(storm.countCollisions(7, 27)).isEqualTo(2);
    }
}