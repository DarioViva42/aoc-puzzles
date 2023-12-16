package tk.vivas.adventofcode.year2023.day16;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class FacilityContraptionTest {

    @Test
    void traceLight() {
        String input = AocUtils.readPuzzleInput();

        FacilityContraption contraption = new FacilityContraption(input);

        assertThat(contraption.traceLight()).isEqualTo(46);
    }
}