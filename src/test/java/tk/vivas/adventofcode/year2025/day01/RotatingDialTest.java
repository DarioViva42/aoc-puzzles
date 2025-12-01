package tk.vivas.adventofcode.year2025.day01;

import org.junit.jupiter.api.*;
import tk.vivas.adventofcode.*;

import static org.assertj.core.api.Assertions.*;

class RotatingDialTest {

    @Test
    void countZeroPositions() {
        String input = AocUtils.readPuzzleInput();

        RotatingDial office = new RotatingDial(input);

        assertThat(office.countZeroPositions(50)).isEqualTo(3);
    }
}