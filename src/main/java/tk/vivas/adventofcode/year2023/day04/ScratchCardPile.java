package tk.vivas.adventofcode.year2023.day04;

import java.util.List;

public class ScratchCardPile {

	private final List<ScratchCard> scratchCardList;

	public ScratchCardPile(String input) {
		scratchCardList = input.lines()
				.map(ScratchCard::new)
				.toList();
	}

	public int score() {
		return scratchCardList.stream()
				.mapToInt(ScratchCard::score)
				.sum();
	}
}
