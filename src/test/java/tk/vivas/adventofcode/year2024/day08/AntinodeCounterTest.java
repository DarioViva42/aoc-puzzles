package tk.vivas.adventofcode.year2024.day08;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class AntinodeCounterTest {

    @Test
    void countUniqueLocations() {
        String input = AocUtils.readPuzzleInput();

        AntinodeCounter antinodeCounter = new AntinodeCounter(input);

        assertThat(antinodeCounter.countUniqueLocations()).isEqualTo(14);
    }

    @Test
    void countAllUniqueLocations() {
        String input = AocUtils.readPuzzleInput();

        AntinodeCounter antinodeCounter = new AntinodeCounter(input);

        assertThat(antinodeCounter.countAllUniqueLocations()).isEqualTo(34);
    }
}