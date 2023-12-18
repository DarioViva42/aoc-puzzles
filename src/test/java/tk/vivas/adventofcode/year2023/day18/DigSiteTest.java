package tk.vivas.adventofcode.year2023.day18;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DigSiteTest {

    @Test
    void countSize() {
        String input = AocUtils.readPuzzleInput();

        DigSite digSite = new DigSite(input);

        assertThat(digSite.countSize()).isEqualTo(62);
    }
}
