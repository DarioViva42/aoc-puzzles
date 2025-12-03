package tk.vivas.adventofcode.year2025.day03;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EscalatorPowerSourceTest {

    @Test
    void totalJoltage() {
        String input = AocUtils.readPuzzleInput();

        EscalatorPowerSource powerSource = new EscalatorPowerSource(input);

        assertThat(powerSource.totalJoltage()).isEqualTo(357);
    }

    @Test
    void improvedTotalJoltage() {
        String input = AocUtils.readPuzzleInput();

        EscalatorPowerSource powerSource = new EscalatorPowerSource(input);

        assertThat(powerSource.improvedTotalJoltage()).isEqualTo(3121910778619L);
    }
}
