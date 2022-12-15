package vivas.tk.adventofcode.day14;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class SandSimulationTest {

    @Test
    void fillWithSand() {
        String input = readPuzzleInput();
        SandSimulation sandSimulation = new SandSimulation(input);

        assertThat(sandSimulation.fillWithSand()).isEqualTo(24);
    }
}