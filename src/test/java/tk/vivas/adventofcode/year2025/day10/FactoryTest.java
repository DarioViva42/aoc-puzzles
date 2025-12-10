package tk.vivas.adventofcode.year2025.day10;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class FactoryTest {

    @Test
    void fewestButtonPresses() {
        String input = AocUtils.readPuzzleInput();

        Factory factory = new Factory(input);

        assertThat(factory.fewestButtonPresses()).isEqualTo(7);
    }
}
