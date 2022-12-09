package vivas.tk.adventofcode.day09;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class RopeSimulationTest {

    @Test
    void runSimulation() {
        String input = readPuzzleInput();
        RopeSimulation ropeSimulation = new RopeSimulation(input);

        assertThat(ropeSimulation.runSimulation()).isEqualTo(13);
    }
}