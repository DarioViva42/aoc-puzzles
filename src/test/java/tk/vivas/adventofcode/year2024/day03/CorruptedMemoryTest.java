package tk.vivas.adventofcode.year2024.day03;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class CorruptedMemoryTest {

    @Test
    void runMulInstructions() {
        String input = AocUtils.readPuzzleInput(1);

        CorruptedMemory corruptedMemory = new CorruptedMemory(input);

        assertThat(corruptedMemory.runMulInstructions()).isEqualTo(161);
    }

    @Test
    void runAllInstructions() {
        String input = AocUtils.readPuzzleInput(2);

        CorruptedMemory corruptedMemory = new CorruptedMemory(input);

        assertThat(corruptedMemory.runAllInstructions()).isEqualTo(48);
    }
}