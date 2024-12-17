package tk.vivas.adventofcode.year2024.day14;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class BathroomOperationTest {

    @Test
    void safetyFactor() {
        String input = AocUtils.readPuzzleInput();

        BathroomOperation clawContraption = new BathroomOperation(input);

        assertThat(clawContraption.safetyFactor(11, 7)).isEqualTo(12);
    }

    @Test
    void cycleLength() {
        String input = AocUtils.readPuzzleInput();

        BathroomOperation clawContraption = new BathroomOperation(input);

        assertThat(clawContraption.cycleLength(11, 7)).isEqualTo(11 * 7);
    }
}