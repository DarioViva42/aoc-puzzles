package tk.vivas.adventofcode.year2023.day12;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class HotSpringsRecordsTest {

    @Test
    void countArrangements() {
        String input = AocUtils.readPuzzleInput();

        HotSpringsRecords records = new HotSpringsRecords(input);

        assertThat(records.countArrangements()).isEqualTo(21);
    }
}
