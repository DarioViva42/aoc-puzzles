package tk.vivas.adventofcode.year2023.day15;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AsciiStringHelperAlgorithmTest {

    @Test
    void hashSum() {
        String input = AocUtils.readPuzzleInput();

        AsciiStringHelperAlgorithm helperAlgorithm = new AsciiStringHelperAlgorithm(input);

        assertThat(helperAlgorithm.hashSum()).isEqualTo(1320);
    }
}
