package vivas.tk.adventofcode.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class RucksackFixerTest {

    @Test
    void calculateErrorPrioritySum() {
        List<String> input = readPuzzleInput();
        RucksackFixer rucksackFixer = new RucksackFixer(input);
        assertThat(rucksackFixer.calculateErrorPrioritySum()).isEqualTo(157);
    }

    @Test
    void calculateGroupPrioritySum() {
        List<String> input = readPuzzleInput();
        RucksackFixer rucksackFixer = new RucksackFixer(input);
        assertThat(rucksackFixer.calculateGroupPrioritySum()).isEqualTo(70);
    }
}