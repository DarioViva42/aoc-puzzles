package tk.vivas.adventofcode.year2023.day10;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PipePuzzleTest {

    @Test
    void findFurthestPointInLoop() {
        String input = AocUtils.readPuzzleInput();

        PipePuzzle puzzle = new PipePuzzle(input);

        assertThat(puzzle.findFurthestPointInLoop()).isEqualTo(8);
    }
}