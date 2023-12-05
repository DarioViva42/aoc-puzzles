package tk.vivas.adventofcode.year2023.day05;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class IslandIslandAlmanacTest {

    @Test
    void findClosestLocation() {
        String input = AocUtils.readPuzzleInput();

        IslandIslandAlmanac almanac = new IslandIslandAlmanac(input);

        assertThat(almanac.findClosestLocation()).isEqualTo(35);
    }
}