package tk.vivas.adventofcode.year2023.day01;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class CalibrationDocumentTest {

	@Test
	void recover() {
		String input = AocUtils.readPuzzleInput();

		CalibrationDocument calibrationValue = new CalibrationDocument(input);

		assertThat(calibrationValue.recover()).isEqualTo(142);
	}
}
