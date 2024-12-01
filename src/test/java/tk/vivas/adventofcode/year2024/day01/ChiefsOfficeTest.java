package tk.vivas.adventofcode.year2024.day01;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class ChiefsOfficeTest {

    @Test
    void totalDistance() {
        String input = AocUtils.readPuzzleInput();

        ChiefsOffice office = new ChiefsOffice(input);

        assertThat(office.totalDistance()).isEqualTo(11);
    }

    @Test
    void similarityScore() {
        String input = AocUtils.readPuzzleInput();

        ChiefsOffice office = new ChiefsOffice(input);

        assertThat(office.similarityScore()).isEqualTo(31);
    }
}