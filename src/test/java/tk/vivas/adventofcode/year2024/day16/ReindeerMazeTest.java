package tk.vivas.adventofcode.year2024.day16;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class ReindeerMazeTest {

    @Test
    void findLowestScore_small() {
        String input = AocUtils.readPuzzleInput(1);

        ReindeerMaze maze = new ReindeerMaze(input);

        assertThat(maze.findLowestScore()).isEqualTo(7036);
    }

    @Test
    void findLowestScore_large() {
        String input = AocUtils.readPuzzleInput(2);

        ReindeerMaze maze = new ReindeerMaze(input);

        assertThat(maze.findLowestScore()).isEqualTo(11048);
    }
}