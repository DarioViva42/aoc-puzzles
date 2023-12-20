package tk.vivas.adventofcode.year2023.day20;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ModuleNetworkTest {

	@ParameterizedTest
	@CsvSource({"1, 32000000", "2, 11687500"})
	void simulate(int part, long expected) {
		String input = AocUtils.readPuzzleInput(part);

		ModuleNetwork network = new ModuleNetwork(input);

		assertThat(network.simulate()).isEqualTo(expected);
	}
}
