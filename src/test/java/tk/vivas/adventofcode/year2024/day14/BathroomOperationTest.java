package tk.vivas.adventofcode.year2024.day14;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class BathroomOperationTest {

    @Test
    void safetyFactor() {
        String input = AocUtils.readPuzzleInput();

        BathroomOperation bathroomOperation = new BathroomOperation(input, 11, 7);

        assertThat(bathroomOperation.safetyFactor()).isEqualTo(12);
    }

    @Test
    void cycleLength() {
        String input = AocUtils.readPuzzleInput();

        BathroomOperation bathroomOperation = new BathroomOperation(input, 11, 7);

        assertThat(bathroomOperation.cycleLength()).isEqualTo(11 * 7);
    }
}