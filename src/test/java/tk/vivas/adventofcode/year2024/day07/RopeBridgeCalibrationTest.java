package tk.vivas.adventofcode.year2024.day07;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class RopeBridgeCalibrationTest {

	@Test
	void totalCalibrationResult() {
		String input = AocUtils.readPuzzleInput();

		RopeBridgeCalibration calibration = new RopeBridgeCalibration(input);

		assertThat(calibration.totalCalibrationResult()).isEqualTo(3749);
	}
}