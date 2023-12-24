package tk.vivas.adventofcode.year2023.day22;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class SandBrickTowerTest {

    @Test
    void countDisintegrationCandidates() {
        String input = AocUtils.readPuzzleInput();

        SandBrickTower tower = new SandBrickTower(input);

        assertThat(tower.countDisintegrationCandidates()).isEqualTo(5);
    }

    @Test
    void sumOfFallingBricks() {
        String input = AocUtils.readPuzzleInput();

        SandBrickTower tower = new SandBrickTower(input);

        tower.init();

        assertThat(tower.sumOfFallingBricks()).isEqualTo(7);
    }
}