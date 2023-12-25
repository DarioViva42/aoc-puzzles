package tk.vivas.adventofcode.year2023.day23;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HikingTrailMapTest {

    @Test
    void findLongestPath() {
        String input = AocUtils.readPuzzleInput();

        HikingTrailMap trailMap = new HikingTrailMap(input);

        assertThat(trailMap.findLongestPath()).isEqualTo(94);
    }
}