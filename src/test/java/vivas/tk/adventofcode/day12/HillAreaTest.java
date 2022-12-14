package vivas.tk.adventofcode.day12;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class HillAreaTest {

    @Test
    void findShortestPath() {
        String input = readPuzzleInput();
        HillArea hillArea = new HillArea(input);

        assertThat(hillArea.findShortestPathFromStart()).isEqualTo(31);
    }

    @Test
    void findRealShortestPath() {
        String input = readPuzzleInput();
        HillArea hillArea = new HillArea(input);

        assertThat(hillArea.findShortestPath()).isEqualTo(29);
    }
}