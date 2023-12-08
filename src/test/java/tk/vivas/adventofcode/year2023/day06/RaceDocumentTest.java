package tk.vivas.adventofcode.year2023.day06;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class RaceDocumentTest {

	@Test
	void marginOfError() {
		String input = AocUtils.readPuzzleInput();

		RaceDocument raceDocument = new RaceDocument(input);

		assertThat(raceDocument.marginOfError()).isEqualTo(288);
	}
}
