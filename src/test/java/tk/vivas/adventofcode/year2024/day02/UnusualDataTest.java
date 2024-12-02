package tk.vivas.adventofcode.year2024.day02;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class UnusualDataTest {

	@Test
	void countSafeReports() {
		String input = AocUtils.readPuzzleInput();

		UnusualData unusualData = new UnusualData(input);

		assertThat(unusualData.countSafeReports()).isEqualTo(2);
	}

	@Test
	void countSafeReportsWithDampener() {
		String input = AocUtils.readPuzzleInput();

		UnusualData unusualData = new UnusualData(input);

		assertThat(unusualData.countSafeReportsWithDampener()).isEqualTo(4);
	}
}