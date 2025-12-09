package tk.vivas.adventofcode.year2025.day08;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DecorationProjectTest {

    @Test
    void connectJunctionBoxes() {
        String input = AocUtils.readPuzzleInput();

        DecorationProject decorationProject = new DecorationProject(input);

        assertThat(decorationProject.connectJunctionBoxes(10)).isEqualTo(40);
    }
}
