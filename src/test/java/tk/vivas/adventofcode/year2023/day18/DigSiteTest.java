package tk.vivas.adventofcode.year2023.day18;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class DigSiteTest {

    @Test
    void countSize() {
        String input = AocUtils.readPuzzleInput();

        DigSite digSite = new DigSite(input);

        assertThat(digSite.countSize()).isEqualTo(62);
    }

    @Test
    void countLargeSize() {
        String input = AocUtils.readPuzzleInput();

        DigSite digSite = new DigSite(input);

        assertThat(digSite.countLargeSize()).isEqualTo(952408144115L);
    }
}
