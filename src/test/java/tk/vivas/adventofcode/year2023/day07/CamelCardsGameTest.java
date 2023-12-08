package tk.vivas.adventofcode.year2023.day07;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CamelCardsGameTest {

	@Test
	void totalWinnings() {
		String input = AocUtils.readPuzzleInput();

		CamelCardsGame cardsGame = new CamelCardsGame(input);

		assertThat(cardsGame.totalWinnings()).isEqualTo(6440);
	}
}
