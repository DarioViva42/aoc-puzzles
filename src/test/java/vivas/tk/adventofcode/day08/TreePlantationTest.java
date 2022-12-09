package vivas.tk.adventofcode.day08;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class TreePlantationTest {

    @Test
    void countVisibleTrees() {
        List<String> input = readPuzzleInput();

        TreePlantation treePlantation = new TreePlantation(input);

        assertThat(treePlantation.countVisibleTrees()).isEqualTo(21);
    }

    @Test
    void findOptimalScenicScore() {
        List<String> input = readPuzzleInput();

        TreePlantation treePlantation = new TreePlantation(input);

        assertThat(treePlantation.findOptimalScenicScore()).isEqualTo(8);
    }

    @Test
    void getScenicScore() {
        List<String> input = readPuzzleInput();

        TreePlantation plantation = new TreePlantation(input);

        Tree treeA = plantation.getTrees().get(1).get(2);
        Tree treeB = plantation.getTrees().get(3).get(2);

        assertThat(plantation.getScenicScore(treeA)).isEqualTo(4);
        assertThat(plantation.getScenicScore(treeB)).isEqualTo(8);
    }
}