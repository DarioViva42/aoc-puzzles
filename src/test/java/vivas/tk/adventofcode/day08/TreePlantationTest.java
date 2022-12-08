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
}