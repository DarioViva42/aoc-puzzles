package tk.vivas.adventofcode.year2023.day04;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScratchCardPileTest {

	@Test
	void score() {
		String input = AocUtils.readPuzzleInput();

		ScratchCardPile scratchCardPile = new ScratchCardPile(input);

		assertThat(scratchCardPile.score()).isEqualTo(13);
	}
}
