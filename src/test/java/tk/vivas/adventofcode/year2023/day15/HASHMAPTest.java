package tk.vivas.adventofcode.year2023.day15;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class HASHMAPTest {

    @Test
    void hashSum() {
        String input = AocUtils.readPuzzleInput();

        HASHMAP hashmap = new HASHMAP(input);

        assertThat(hashmap.prepare()).isEqualTo(1320);
    }

    @Test
    void runProcedure() {
        String input = AocUtils.readPuzzleInput();

        HASHMAP hashmap = new HASHMAP(input);

        assertThat(hashmap.runProcedure()).isEqualTo(145);
    }
}
