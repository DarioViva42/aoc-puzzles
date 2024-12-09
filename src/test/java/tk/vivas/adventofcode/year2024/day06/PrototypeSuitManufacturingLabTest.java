package tk.vivas.adventofcode.year2024.day06;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class PrototypeSuitManufacturingLabTest {

    @Test
    void countVisitedPositions() {
        String input = AocUtils.readPuzzleInput();

        var lab = new PrototypeSuitManufacturingLab(input);

        assertThat(lab.countVisitedPositions()).isEqualTo(41);
    }
}