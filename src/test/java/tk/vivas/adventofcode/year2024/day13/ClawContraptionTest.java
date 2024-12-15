package tk.vivas.adventofcode.year2024.day13;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class ClawContraptionTest {

    @Test
    void fewestTokens() {
        String input = AocUtils.readPuzzleInput();

        ClawContraption clawContraption = new ClawContraption(input);

        assertThat(clawContraption.fewestTokens()).isEqualTo(480);
    }
}