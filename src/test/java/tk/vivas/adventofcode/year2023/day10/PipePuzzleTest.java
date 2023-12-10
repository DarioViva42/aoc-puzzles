package tk.vivas.adventofcode.year2023.day10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class PipePuzzleTest {

    @Test
    void findFurthestPointInLoop() {
        String input = AocUtils.readPuzzleInput(1);

        PipePuzzle puzzle = new PipePuzzle(input);

        assertThat(puzzle.findFurthestPointInLoop()).isEqualTo(8);
    }

    @ParameterizedTest
    @CsvSource({"2,4", "3,10"})
    void countEnclosedTiles(int level, int expected) {
        String input = AocUtils.readPuzzleInput(level);

        PipePuzzle puzzle = new PipePuzzle(input);

        assertThat(puzzle.countEnclosedTiles()).isEqualTo(expected);
    }
}