package tk.vivas.adventofcode.year2025.day06;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class CephalopodsHomeworkTest {

    @Test
    void solve() {
        String input = AocUtils.readPuzzleInput();

        CephalopodsHomework homework = new CephalopodsHomework(input);

        assertThat(homework.solve()).isEqualTo(4277556);
    }
}