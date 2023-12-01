package tk.vivas.adventofcode.year2023.day01;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class CalibrationDocumentTest {

    @Test
    void recover() {
        String input = AocUtils.readPuzzleInput(1);

        CalibrationDocument calibrationValue = new CalibrationDocument(input);

        int recoveredValue = calibrationValue.recover();

        assertThat(recoveredValue).isEqualTo(142);
    }

    @Test
    void fullyRecover() {
        String input = AocUtils.readPuzzleInput(2);

        CalibrationDocument calibrationValue = new CalibrationDocument(input);

        long fullyRecoveredValue = calibrationValue.fullyRecover();

        assertThat(fullyRecoveredValue).isEqualTo(281);
    }
}
