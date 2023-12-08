package tk.vivas.adventofcode.year2023.day07;

import java.util.List;

class CamelCardsGame {

	private final List<Hand> hands;

	CamelCardsGame(String input) {
		hands = input.lines()
				.map(Hand::new)
				.toList();
	}

	public long totalWinnings() {
		List<Hand> sortedHands = hands.stream()
				.sorted()
				.toList();

		int totalWinnings = 0;
		for (int i = 0; i < sortedHands.size(); i++) {
			int rank = i + 1;
			totalWinnings += sortedHands.get(i).getBidAmount() * rank;
		}
		return totalWinnings;
	}
}
