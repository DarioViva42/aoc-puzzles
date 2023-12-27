package tk.vivas.adventofcode.year2023.day23;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class HikingTrailMapTest {

    @Test
    void findLongestPath() {
        String input = AocUtils.readPuzzleInput();

        HikingTrailMap trailMap = new HikingTrailMap(input);

        assertThat(trailMap.findLongestPath()).isEqualTo(94);
    }

    @Test
    void findLongestPathIgnoringSlopes() {
        String input = AocUtils.readPuzzleInput();

        HikingTrailMap trailMap = new HikingTrailMap(input);

        int longestPath = trailMap.findLongestPathIgnoringSlopes();

        System.out.println(trailMap);

        assertThat(longestPath).isEqualTo(154);
    }
}