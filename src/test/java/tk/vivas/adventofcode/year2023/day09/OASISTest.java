package tk.vivas.adventofcode.year2023.day09;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class OASISTest {

    @Test
    void sumUpExtrapolatedValues() {
        String input = AocUtils.readPuzzleInput();

        OASIS oasis = new OASIS(input);

        assertThat(oasis.sumUpExtrapolatedValues()).isEqualTo(114);
    }

    @Test
    void sumUpBackwardExtrapolatedValues() {
        String input = AocUtils.readPuzzleInput();

        OASIS oasis = new OASIS(input);

        assertThat(oasis.sumUpBackwardExtrapolatedValues()).isEqualTo(2);
    }
}