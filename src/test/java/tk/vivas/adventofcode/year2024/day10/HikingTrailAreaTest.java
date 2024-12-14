package tk.vivas.adventofcode.year2024.day10;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class HikingTrailAreaTest {

    @Test
    void trailHeadsScore() {
        String input = AocUtils.readPuzzleInput();

        HikingTrailArea trailArea = new HikingTrailArea(input);

        assertThat(trailArea.trailHeadsScore()).isEqualTo(36);
    }

    @Test
    void trailHeadsRating() {
        String input = AocUtils.readPuzzleInput();

        HikingTrailArea trailArea = new HikingTrailArea(input);

        assertThat(trailArea.trailHeadsRating()).isEqualTo(81);
    }
}