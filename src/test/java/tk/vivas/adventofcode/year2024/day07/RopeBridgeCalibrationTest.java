package tk.vivas.adventofcode.year2024.day07;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class RopeBridgeCalibrationTest {

	@Test
	void limitedCalibrationResult() {
		String input = AocUtils.readPuzzleInput();

		RopeBridgeCalibration calibration = new RopeBridgeCalibration(input);

		assertThat(calibration.limitedCalibrationResult()).isEqualTo(3749);
	}

	@Test
	void extendedCalibrationResult() {
		String input = AocUtils.readPuzzleInput();

		RopeBridgeCalibration calibration = new RopeBridgeCalibration(input);

		assertThat(calibration.extendedCalibrationResult()).isEqualTo(11387);
	}
}