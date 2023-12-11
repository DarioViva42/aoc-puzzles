package tk.vivas.adventofcode.year2023.day11;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GalaxyObservationTest {

	@Test
	void sumUpDistances_1() {
		String input = AocUtils.readPuzzleInput();

		GalaxyObservation observation = new GalaxyObservation(input);

		assertThat(observation.sumUpDistances(2)).isEqualTo(374);
	}

	@Test
	void sumUpDistances_100() {
		String input = AocUtils.readPuzzleInput();

		GalaxyObservation observation = new GalaxyObservation(input);

		assertThat(observation.sumUpDistances(100)).isEqualTo(8410);
	}
}
