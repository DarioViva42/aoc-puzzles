package tk.vivas.adventofcode.day16;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

class VolcanoTest {

    @Test
    void findBestPressureReleaseStrategy() {
        String input = readPuzzleInput();
        Volcano volcano = new Volcano(input);

        assertThat(volcano.findBestPressureReleaseStrategy()).isEqualTo(1651);
    }

    @Test
    void findBestPressureReleaseStrategyWithElephant() {
        String input = readPuzzleInput();
        Volcano volcano = new Volcano(input);

        assertThat(volcano.findBestPressureReleaseStrategyWithElephant()).isEqualTo(1707);
    }
}