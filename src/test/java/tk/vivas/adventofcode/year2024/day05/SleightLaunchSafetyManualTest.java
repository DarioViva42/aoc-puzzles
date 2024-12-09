package tk.vivas.adventofcode.year2024.day05;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class SleightLaunchSafetyManualTest {

    @Test
    void correctPagesScore() {
        String input = AocUtils.readPuzzleInput();

        SleightLaunchSafetyManual safetyManual = new SleightLaunchSafetyManual(input);

        assertThat(safetyManual.correctPagesScore()).isEqualTo(143);
    }

    @Test
    void incorrectPagesScore() {
        String input = AocUtils.readPuzzleInput();

        SleightLaunchSafetyManual safetyManual = new SleightLaunchSafetyManual(input);

        assertThat(safetyManual.incorrectPagesScore()).isEqualTo(123);
    }
}