package vivas.tk.adventofcode.day14;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class SandSimulationTest {

    @Test
    void fillCaveWithSand() {
        String input = readPuzzleInput();
        SandSimulation sandSimulation = new SandSimulation(input);

        assertThat(sandSimulation.fillCaveWithSand()).isEqualTo(24);
    }

    @Test
    void fillFlooredCaveWithSand() {
        String input = readPuzzleInput();
        SandSimulation sandSimulation = new SandSimulation(input);

        assertThat(sandSimulation.fillFlooredCaveWithSand()).isEqualTo(93);
    }
}