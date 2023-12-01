package tk.vivas.adventofcode.day09;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

class RopeSimulationTest {

    @Test
    void runSimulation_1() {
        String input = readPuzzleInput(1);
        RopeSimulation ropeSimulation = new RopeSimulation(input);

        assertThat(ropeSimulation.runSimulation(1)).isEqualTo(13);
    }

    @Test
    void runSimulation_9() {
        String input = readPuzzleInput(2);
        RopeSimulation ropeSimulation = new RopeSimulation(input);

        assertThat(ropeSimulation.runSimulation(9)).isEqualTo(36);
    }
}