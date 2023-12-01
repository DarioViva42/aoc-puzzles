package tk.vivas.adventofcode.year2022.day03;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

class RucksackFixerTest {

    @Test
    void calculateErrorPrioritySum() {
        String input = readPuzzleInput();
        RucksackFixer rucksackFixer = new RucksackFixer(input);

        assertThat(rucksackFixer.calculateErrorPrioritySum()).isEqualTo(157);
    }

    @Test
    void calculateGroupPrioritySum() {
        String input = readPuzzleInput();
        RucksackFixer rucksackFixer = new RucksackFixer(input);

        assertThat(rucksackFixer.calculateGroupPrioritySum()).isEqualTo(70);
    }
}