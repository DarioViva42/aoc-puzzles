package tk.vivas.adventofcode.year2023.day13;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class MirrorValleyTest {

    @Test
    void summarizePatterns() {
        String input = AocUtils.readPuzzleInput();

        MirrorValley valley = new MirrorValley(input);

        assertThat(valley.summarizePatterns()).isEqualTo(405);
    }

    @Test
    void summarizePatternsClearingSmudge() {
        String input = AocUtils.readPuzzleInput();

        MirrorValley valley = new MirrorValley(input);

        assertThat(valley.summarizePatternsClearingSmudge()).isEqualTo(400);
    }
}