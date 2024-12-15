package tk.vivas.adventofcode.year2024.day12;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class GardenGroupsTest {

    @Test
    void totalFencingPrice() {
        String input = AocUtils.readPuzzleInput();

        GardenGroups gardenGroups = new GardenGroups(input);

        assertThat(gardenGroups.totalFencingPrice()).isEqualTo(1930);
    }
}