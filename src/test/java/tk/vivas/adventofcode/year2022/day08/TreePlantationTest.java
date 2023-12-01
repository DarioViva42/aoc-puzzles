package tk.vivas.adventofcode.year2022.day08;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

class TreePlantationTest {

    @Test
    void countVisibleTrees() {
        String input = readPuzzleInput();
        TreePlantation treePlantation = new TreePlantation(input);

        assertThat(treePlantation.countVisibleTrees()).isEqualTo(21);
    }

    @Test
    void findOptimalScenicScore() {
        String input = readPuzzleInput();
        TreePlantation treePlantation = new TreePlantation(input);

        assertThat(treePlantation.findOptimalScenicScore()).isEqualTo(8);
    }

    @Test
    void getScenicScore() {
        String input = readPuzzleInput();
        TreePlantation plantation = new TreePlantation(input);

        Tree treeA = plantation.getTrees().get(1).get(2);
        Tree treeB = plantation.getTrees().get(3).get(2);

        assertThat(plantation.getScenicScore(treeA)).isEqualTo(4);
        assertThat(plantation.getScenicScore(treeB)).isEqualTo(8);
    }
}