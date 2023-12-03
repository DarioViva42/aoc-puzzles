package tk.vivas.adventofcode.year2023.day03;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class EngineSchematicTest {

    @Test
    void calculatePartNumberSum() {
        String input = AocUtils.readPuzzleInput();

        EngineSchematic engineSchematic = new EngineSchematic(input);

        assertThat(engineSchematic.calculatePartNumberSum()).isEqualTo(4361);
    }

    @Test
    void calculateGearRatioSum() {
        String input = AocUtils.readPuzzleInput();

        EngineSchematic engineSchematic = new EngineSchematic(input);

        assertThat(engineSchematic.calculateGearRatioSum()).isEqualTo(467835);
    }
}